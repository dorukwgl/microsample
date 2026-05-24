# File Upload & Deletion HOWTO

## Context
Document the two file workflows: upload (with NATS event-based image processing) and delete (on update or standalone).
All file paths relative to `src/main/java/com/doruk/`.

---

## 1. File Upload Flow

### Architecture Layers
```
Controller (presentation/users/controller/)
  → FileService (application/files/)
  → ObjectStorage interface (application/interfaces/)
    → LocalObjectStorage / S3ObjectStorage (infrastructure/fileio/)
  → FileRepository (infrastructure/persistence/files/)
  → MediaStore entity (infrastructure/persistence/entity/)
  → NATS event → ImageUploadEventHandler (infrastructure/messaging/handler/)
  → Serving: PrivateFilesController (presentation/files/)
```

### Step 1: Controller receives upload
**File:** `presentation/users/controller/UserController.java`
```java
@Put("/profile/icon")
public Map<String, String> updateProfileIcon(
        Authentication auth,
        @Part("profile-icon") StreamingFileUpload file) {
    var uploaded = fileService.imageUploadPublic(file);
    return service.updateProfileIcon(auth.getName(), uploaded);
}
```
`StreamingFileUpload` is Micronaut's built-in multipart file upload abstraction. The controller delegates storage to `FileService` and then passes the result to the domain service.

### Step 2: FileService stores the file
**File:** `application/files/FileService.java`
```java
@Singleton
public class FileService {
    private final ObjectStorage storage;
    private final FileRepository fileRepo;
    private final AppConfig config;

    public UploadedFile imageUploadPublic(StreamingFileUpload upload) {
        var obj = storage.store(
            new StreamingUploadSource(upload),
            FileType.IMAGE,
            ObjectVisibility.PUBLIC,
            config.imageMaxSize()
        );
        var id = fileRepo.save(obj);
        return new UploadedFile(id, obj);
    }
}
```
**Key points:**
- `StreamingUploadSource` wraps `StreamingFileUpload` into the `UploadSource` interface
- `FileType.IMAGE` validates MIME type (jpeg, png, webp, gif)
- `ObjectVisibility.PUBLIC` — see Section 3
- `config.imageMaxSize()` enforces size limit

### Step 3: ObjectStorage.store() writes bytes + generates key
**Interface:** `application/interfaces/ObjectStorage.java`
```java
public interface ObjectStorage {
    StoredObject store(UploadSource source, FileType type, ObjectVisibility visibility, long maxSize);
    InputStream open(String objectKey);
    void put(String objectKey, InputStream data, long size, String mimeType);
    void delete(String objectKey);
    String resolveUrl(StoredObject storedObject);    // PUBLIC only, throws for PRIVATE
    String signUrl(StoredObject storedObject, Duration ttl);  // PRIVATE -> signed/temporary URL
}
```

**Key generation** (in both `LocalObjectStorage` and `S3ObjectStorage`):
```
{prefix}/{shard1}/{shard2}/{uuid}.{ext}
Example: public/ab/cd/abc123def.webp
```
- `prefix` = `config.publicPathPrefix()` (for PUBLIC) or `config.privatePathPrefix()` (for PRIVATE)
- `shard1/shard2` = first 4 chars of a UUID, split into 2 levels

### Step 4: FileRepository persists MediaStore row
**File:** `infrastructure/persistence/files/FileRepository.java`
```java
@Singleton
public class FileRepository {
    private final JSqlClient client;

    public long save(StoredObject fileObject) {
        var draft = MediaStoreDraft.$.produce(m -> m
            .setSize(fileObject.size())
            .setMimeType(fileObject.mimeType())
            .setObjectKey(fileObject.objectKey())
            .setVisibility(fileObject.visibility())
        );
        return client.saveCommand(draft).execute().getModifiedEntity().id();
    }
}
```

**MediaStore entity** (Jimmer):
```java
@Entity
@Table(name = "media_store")
public interface MediaStore {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id();
    @Key String objectKey();       // the partial key (e.g. "public/ab/cd/uuid.webp")
    ObjectVisibility visibility();
    String mimeType();
    long size();
    OffsetDateTime createdAt();
    @Nullable @LogicalDeleted("now") OffsetDateTime deletedAt();  // soft delete
}
```
DB stores only the `objectKey` partial path — **not** a full URL. The full URL is reconstructed at serve-time.

### Step 5: Domain service publishes NATS event
**File:** `application/app/users/service/UserService.java`
```java
public Map<String, String> updateProfileIcon(String userId, UploadedFile icon) {
    var previous = userRepo.updateProfileIconReturningOld(userId, icon);
    var stored = icon.storedObject();

    event.publish(new ProfileImageUploadEvent(
        stored.objectKey(),
        stored.mimeType(),
        previous   // Optional<String> — old object key for cleanup
    ));

    return Map.of("newProfilePicture", storage.resolveUrl(stored));
}
```

**Event DTO:**
```java
@Serdeable
public record ProfileImageUploadEvent(
    String objectKey,
    String mimeType,
    Optional<String> oldObjectKey
) implements EventDto {
    @Override
    public String eventSubject() { return "profile.image.upload.event"; }
}
```
The event carries the new object key + optional old key for cleanup. Subject is determined by `eventSubject()`.

### Step 6: ImageUploadEventHandler processes variants
**File:** `infrastructure/messaging/handler/ImageUploadEventHandler.java`
```java
@Singleton
@NatsListener
public class ImageUploadEventHandler {
    private final ObjectStorage storage;
    private final AppExecutors executors;

    @Subject(value = "profile.image.upload.event", queue = "profile-image-upload-queue")
    public void handle(ProfileImageUploadEvent event) {
        CompletableFuture.runAsync(() -> {
            // Generate scaled variants
            for (ImageVariant variant : ImageVariant.values()) {
                try (InputStream stream = storage.open(event.objectKey())) {
                    var data = CompletableFuture
                        .supplyAsync(() -> scaleAndCompress(stream, variant), executors.CPU())
                        .join();
                    String variantKey = ImageVariantKey.of(event.objectKey(), variant);
                    storage.put(variantKey, data.getValue(), data.getKey(), event.mimeType());
                }
            }
            // Delete old files if any
            if (event.oldObjectKey().isPresent()) {
                for (ImageVariant variant : ImageVariant.values()) {
                    storage.delete(ImageVariantKey.of(event.oldObjectKey().get(), variant));
                }
            }
        }, executors.VIRTUAL()).join();
    }
}
```
Generates 4 variants per image (ORIGINAL, PICO 64px, SMALL 320px, MEDIUM 640px).

### ImageVariant Key Naming
**File:** `infrastructure/util/ImageVariantKey.java`
```
Original key:  public/ab/cd/uuid123.jpg
Variants:
  ORIGINAL → public/ab/cd/uuid123.jpg       (unchanged)
  PICO     → public/ab/cd/uuid123@pc.jpg    (64px)
  SMALL    → public/ab/cd/uuid123@sm.jpg    (320px)
  MEDIUM   → public/ab/cd/uuid123@md.jpg    (640px)
```
Pattern: inserts `@{suffix}` before the file extension. The DB only stores the base `objectKey`; variants are derived by the serving helper.

### Step 7: Serving files
**File:** `presentation/files/PrivateFilesController.java`
```java
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/res")
public class PrivateFilesController {

    @Get("/{filename:.*}")
    public HttpResponse<?> stream(String filename, @Nullable Authentication auth) {
        // 1. Look up media_store by filename part of objectKey
        // 2. If PRIVATE and no auth → 401
        // 3. If PRIVATE (S3 env) → redirect to signUrl(obj, 5min)
        // 4. If PRIVATE (local env) → X-Accel-Redirect header (nginx internal)
        // 5. If PUBLIC → redirect to resolveUrl(obj)
    }
}
```
`resolveUrl()` builds the full URL: `{appUrl}/res/{objectKey}` (local) or `{publicBaseUrl}/{objectKey}` (S3).

---

## 2. File Deletion Flow

### Deletion on Update (profile icon replacement)
```
UserService.updateProfileIcon()
  → UserRepository.updateProfileIconReturningOld()
    → hard-deletes old MediaStore row
    → returns Optional<String> oldObjectKey
  → publishes ProfileImageUploadEvent(newKey, mime, oldKey)
  → ImageUploadEventHandler.deleteOldVariantFiles()
    → for each ImageVariant: storage.delete(variantKey)
```

The old files are cleaned up **asynchronously** via the NATS event handler. The DB row is deleted synchronously.

### Standalone File Deletion
```java
// In FileRepository:
public String deleteReturningObjectKey(long id) {
    var t = MediaStoreTable.$;
    var objectId = client.createQuery(t)
        .select(t.objectKey())
        .where(t.id().eq(id))
        .execute();
    client.deleteById(MediaStore.class, id);
    return objectId.getFirst();
}

// Then publish an event or call storage.delete() directly:
storage.delete(objectKey);
// Plus delete all variants:
for (ImageVariant variant : ImageVariant.values()) {
    storage.delete(ImageVariantKey.of(objectKey, variant));
}
```

**Important:** DB stores only partial `objectKey`. Full deletion requires both:
1. Delete the `media_store` row (DB)
2. Delete all variant files from storage (S3/local FS)

---

## 3. Public vs Private Files

| Aspect | PUBLIC | PRIVATE |
|--------|--------|---------|
| Key prefix | `config.publicPathPrefix()` (e.g. "public") | `config.privatePathPrefix()` (e.g. "private") |
| URL via `resolveUrl()` | Returns direct URL | **Throws** — use `signUrl()` instead |
| URL via `signUrl()` | Returns `resolveUrl()` (local) or signed URL (S3) | Returns signed URL with TTL (S3) or internal redirect header (local) |
| Serving (local) | Redirect to direct URL | `X-Accel-Redirect` header (nginx internal, auth-gated) |
| Serving (S3) | Redirect to `publicBaseUrl/{key}` | Redirect to S3 pre-signed URL (5 min TTL) |
| Access control | Anyone with URL | Requires authentication |

Set visibility at upload time:
```java
// Public file
storage.store(source, FileType.IMAGE, ObjectVisibility.PUBLIC, maxSize);

// Private file
storage.store(source, FileType.DOCUMENT, ObjectVisibility.PRIVATE, maxSize);
```

### Private Upload Example (Controller)
```java
@Put("/profile/icon/private")
public Map<String, String> uploadPrivateIcon(
        Authentication auth,
        @Part("file") StreamingFileUpload file) {
    var uploaded = fileService.imageUploadPrivate(file);
    // Return signed URL (not resolveUrl — that throws for PRIVATE)
    return Map.of("url", storage.signUrl(uploaded.storedObject(), Duration.ofMinutes(5)));
}
```
`imageUploadPrivate()` is identical to `imageUploadPublic()` except it passes `ObjectVisibility.PRIVATE`. The response must use `signUrl()` for temporary access — `resolveUrl()` throws for private objects.

---

## 4. Skeletal Code Examples

### Example: Upload a new file

```java
// --- Controller ---
@Put("/avatar")
public Map<String, String> uploadAvatar(
        Authentication auth,
        @Part("file") StreamingFileUpload file) {
    var uploaded = fileService.imageUploadPublic(file);
    // Associate with user, publish event, return URL
    var previousObjectKey = userRepo.updateAvatar(auth.getName(), uploaded.id());
    event.publish(new ProfileImageUploadEvent(
        uploaded.storedObject().objectKey(),
        uploaded.storedObject().mimeType(),
        previousObjectKey
    ));
    return Map.of("url", storage.resolveUrl(uploaded.storedObject()));
}

// --- FileService (reusable) ---
@Singleton
public class FileService {
    private final ObjectStorage storage;
    private final FileRepository fileRepo;

    public UploadedFile imageUploadPublic(StreamingFileUpload upload) {
        var obj = storage.store(
            new StreamingUploadSource(upload), FileType.IMAGE, ObjectVisibility.PUBLIC, maxSize);
        return new UploadedFile(fileRepo.save(obj), obj);
    }

    public UploadedFile documentUploadPrivate(StreamingFileUpload upload) {
        var obj = storage.store(
            new StreamingUploadSource(upload), FileType.DOCUMENT, ObjectVisibility.PRIVATE, maxSize);
        return new UploadedFile(fileRepo.save(obj), obj);
    }
}
```

### Example: Delete a file

```java
// --- Controller ---
@Delete("/file/{id}")
public HttpResponse<?> deleteFile(@PathVariable long id) {
    var objectKey = fileRepo.deleteReturningObjectKey(id);
    // Delete original + all variants
    storage.delete(objectKey);
    for (ImageVariant variant : ImageVariant.values()) {
        storage.delete(ImageVariantKey.of(objectKey, variant));
    }
    return HttpResponse.noContent();
}
```

### Example: Serve a file

```java
// --- Controller ---
@Get("/files/{id}")
public HttpResponse<?> getFile(@PathVariable long id, @Nullable Authentication auth) {
    var media = fileRepo.findById(id);
    if (media == null) return HttpResponse.notFound();

    if (media.visibility() == ObjectVisibility.PRIVATE) {
        if (auth == null) return HttpResponse.unauthorized();
        return HttpResponse.redirect(storage.signUrl(
            new StoredObject(media.objectKey(), null, media.size(), media.visibility(), media.mimeType()),
            Duration.ofMinutes(5)
        ));
    }
    return HttpResponse.redirect(storage.resolveUrl(
        new StoredObject(media.objectKey(), null, media.size(), media.visibility(), media.mimeType())
    ));
}
```

---

## 5. Key Files Reference

| Layer | File |
|-------|------|
| Storage interface | `application/interfaces/ObjectStorage.java` |
| Upload abstraction | `application/interfaces/UploadSource.java` |
| Streaming adapter | `infrastructure/fileio/StreamingUploadSource.java` |
| Local storage | `infrastructure/fileio/LocalObjectStorage.java` |
| S3 storage | `infrastructure/fileio/S3ObjectStorage.java` |
| File service | `application/files/FileService.java` |
| File repository | `infrastructure/persistence/files/FileRepository.java` |
| MediaStore entity | `infrastructure/persistence/entity/MediaStore.java` |
| Upload event | `application/events/ProfileImageUploadEvent.java` |
| Event handler | `infrastructure/messaging/handler/ImageUploadEventHandler.java` |
| Variant key helper | `infrastructure/util/ImageVariantKey.java` |
| File serving controller | `presentation/files/PrivateFilesController.java` |
| Enums | `application/enums/FileType.java`, `ObjectVisibility.java`, `ImageVariant.java` |
| DTOs | `application/dto/StoredObject.java`, `UploadedFile.java` |
| Config | `infrastructure/config/AppConfig.java`, `S3Config.java` |

---

## 6. Multi-File Upload Flow

### Architecture
Uses the same single-file pipeline but in parallel. A batch event replaces the single-file event.

```
Controller (@Part("files") List<StreamingFileUpload>)
  → FileService.imageUploadPublicBatch(files)
    → maps each file through imageUploadPublic() (store + save)
    → returns List<UploadedFile>
  → publish MultiImageUploadEvent(List<FilePayload>)
  → NATS → ImageUploadEventHandler.handleMulti(event)
    → allOf() all files in parallel on CPU executor
    → each file: handleScalingFor() → all 4 variants
```

### Event DTO
```java
@Serdeable
public record MultiImageUploadEvent(
    List<FilePayload> files
) implements EventDto {
    @Override
    public String eventSubject() { return "file.image.upload.multi"; }

    @Serdeable
    public record FilePayload(String objectKey, String mimeType) {}
}
```

### FileService Batch Method
```java
public List<UploadedFile> imageUploadPublicBatch(List<StreamingFileUpload> uploads) {
    return uploads.stream().map(this::imageUploadPublic).toList();
}
```
Reuses the existing single-file `imageUploadPublic()` for each file. No new storage or repository logic.

### Event Handler
```java
@Subject(value = "file.image.upload.multi", queue = "image-upload-multi-queue")
public void handleMulti(MultiImageUploadEvent event) {
    CompletableFuture.runAsync(() -> {
        var tasks = event.files().stream()
            .map(f -> CompletableFuture.runAsync(() -> handleScalingFor(f), executors.CPU()))
            .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(tasks).join();
    }, executors.VIRTUAL()).join();
}

private void handleScalingFor(MultiImageUploadEvent.FilePayload file) {
    for (ImageVariant variant : ImageVariant.values()) {
        try (InputStream stream = storage.open(file.objectKey())) {
            var data = scaleAndCompress(stream, variant);
            String variantKey = ImageVariantKey.of(file.objectKey(), variant);
            storage.put(variantKey, data.getValue(), data.getKey(), file.mimeType());
        } catch (IOException e) {
            throw new RuntimeException("Variant generation failed", e);
        }
    }
}
```
All files processed in parallel via `CompletableFuture.allOf().join()`. Each file generates all 4 variants (PICO, SMALL, MEDIUM, ORIGINAL).

### Controller Example
```java
@Post("/upload/images")
public List<Map<String, String>> uploadMultipleImages(
        Authentication auth,
        @Part("files") List<StreamingFileUpload> files) {
    var results = fileService.imageUploadPublicBatch(files);
    event.publish(new MultiImageUploadEvent(
        results.stream()
            .map(r -> new FilePayload(r.storedObject().objectKey(), r.storedObject().mimeType()))
            .toList()
    ));
    return results.stream()
        .map(r -> Map.of("url", storage.resolveUrl(r.storedObject())))
        .toList();
}
```

### Key Differences: Single vs Multi

| Aspect | Single | Multi |
|--------|--------|-------|
| Controller param | `@Part("profile-icon") StreamingFileUpload file` | `@Part("files") List<StreamingFileUpload> files` |
| Event | `ProfileImageUploadEvent` | `MultiImageUploadEvent` |
| Event subject | `profile.image.upload.event` | `file.image.upload.multi` |
| Old file cleanup | Yes (via `oldObjectKey`) | No (net-new files only) |
| Parallel processing | Per-variant within one file | Per-file, then per-variant within each file |
| NATS queue | `profile-image-upload-queue` | `image-upload-multi-queue` |

---

## 7. Cheat Sheet: What To Do Per Layer

### Upload (Single or Batch)

| Layer | Action |
|-------|--------|
| **Controller** | `@Part("file") StreamingFileUpload` for single, `@Part("files") List<StreamingFileUpload>` for batch |
| **FileService** | `imageUploadPublic(file)` or `imageUploadPrivate(file)` — calls `storage.store()` + `fileRepo.save()` |
| **ObjectStorage** | `.store(source, FileType.IMAGE, visibility, maxSize)` — handles key gen, sharding, bytes write |
| **FileRepository** | `.save(StoredObject)` — inserts `media_store` row, returns id |
| **Event Publish** | Single: `ProfileImageUploadEvent(newKey, mime, Optional<oldKey>)`. Batch: `MultiImageUploadEvent(List<FilePayload>)` |
| **Event Handler** | `ImageUploadEventHandler` — `handle()` for single (scales 4 variants + deletes old), `handleMulti()` for batch (parallel per-file) |
| **Response** | PUBLIC → `storage.resolveUrl(obj)`. PRIVATE → `storage.signUrl(obj, Duration.ofMinutes(5))` |

### Delete

| Layer | Action |
|-------|--------|
| **Controller** | `@Delete("/file/{id}")` → repo returns objectKey → storage deletes files |
| **FileRepository** | Delete `media_store` row by id, return `objectKey` before deletion |
| **ObjectStorage** | `.delete(objectKey)` for original + `.delete(ImageVariantKey.of(key, variant))` for all 4 variants |
| **Event (optional)** | Publish event with old objectKey so handler cleans up variant files async |

---

## 8. Push Notification Sending

### Context
Two approaches for sending push notifications: **immediate** (blocking, direct) and **background** (NATS event, async). Both support rich notifications with title, message, icon, and attachments (image, audio, video, document).

### Architecture
```
sendInBackground → NotificationEventPublisher → NATS → NotificationEventHandler
                                                          ├─ repository.saveAll()
                                                          ├─ storage.resolveUrl() (icon, attachment)
                                                          └─ firebasePushService.sendBulk()

sendImmediate → NotificationService
                  ├─ repository.saveAll()
                  ├─ storage.resolveUrl() (icon, attachment)
                  └─ firebasePushService.sendBulk() (blocks until all sent)
```

### Rich Notification Structure
**DTO:** `application/dto/NotificationDto.java`
```java
@Serdeable
public record NotificationDto(
        UUID userId,           // target user
        String title,          // notification title
        String message,        // notification body
        @Nullable String icon,           // object key of icon (stored in media_store)
        @Nullable String attachment,      // object key of attachment file
        @Nullable AttachmentType attachmentType  // IMAGE, AUDIO, VIDEO, DOCUMENT
) {}
```

**FCM payload delivered to device:**
```json
{
  "message": {
    "token": "...",
    "notification": { "title": "...", "body": "...", "image": "..." },
    "data": {
      "attachmentType": "IMAGE",
      "attachment": "https://..."
    },
    "android": { "notification": { "icon": "...", "image": "..." } },
    "apns": { "payload": { "aps": { "mutable-content": 1 } }, "fcm_options": { "image": "..." } }
  }
}
```
- `notification.image` / `android.notification.image` — set only when `attachmentType == IMAGE`
- `data.attachmentType` — enum name so Android can differentiate IMAGE vs DOCUMENT vs AUDIO vs VIDEO
- `data.attachment` — resolved URL of the attachment file
- `android.notification.icon` — small notification tray icon

### Send Immediately (Blocking)

Resolves storage URLs, saves to DB, fetches device tokens, and pushes directly. Blocks until all device sends complete.

```java
// --- In NotificationService (already implemented) ---
@Singleton
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;
    private final FirebasePushService firebasePushService;
    private final NotificationEventPublisher eventPublisher;
    private final ObjectStorage storage;

    public void sendImmediate(NotificationDto dto) {
        sendImmediateBulk(List.of(dto));
    }

    public void sendImmediateBulk(List<NotificationDto> dtos) {
        repository.saveAll(dtos);
        for (var dto : dtos) {
            var tokens = repository.findDeviceTokens(dto.userId());
            if (tokens.isEmpty()) continue;

            String iconUrl = dto.icon() != null
                    ? storage.resolveUrl(storedObjectFromKey(dto.icon())) : null;

            String attachmentUrl = dto.attachment() != null
                    ? storage.resolveUrl(storedObjectFromKey(dto.attachment())) : null;

            boolean isImageAttachment = dto.attachmentType() == AttachmentType.IMAGE;
            String imageUrl = isImageAttachment ? attachmentUrl : null;
            String attachmentType = dto.attachmentType() != null
                    ? dto.attachmentType().name() : null;

            firebasePushService.sendBulk(tokens, dto.title(), dto.message(),
                    iconUrl, imageUrl, attachmentType, attachmentUrl);
        }
    }
}
```

```java
// --- Controller usage ---
@Post("/send")
public HttpResponse<?> sendNotification(NotificationDto dto) {
    notificationService.sendImmediate(dto);
    return HttpResponse.ok();
}
```

**Characteristics:**
- Blocks until all FCM HTTP calls complete (all device tokens processed)
- 490 devices in parallel via virtual threads + HTTP/2 multiplexing
- Caller gets direct feedback — failures are logged individually per token
- Good for: admin-triggered sends, one-off notifications, debugging

### Send in Background (NATS Event)

Publishes to NATS; handler processes asynchronously. Non-blocking — returns immediately.

```java
// --- In NotificationService (already implemented) ---
public void sendInBackground(NotificationDto dto) {
    eventPublisher.publishSingle(dto);
}

public void sendInBackgroundBulk(List<NotificationDto> dtos) {
    eventPublisher.publishBulk(dtos);
}
```

```java
// --- Controller usage ---
@Post("/send-async")
public HttpResponse<?> sendNotificationAsync(NotificationDto dto) {
    notificationService.sendInBackground(dto);
    return HttpResponse.accepted();
}
```

**Event handler** (`infrastructure/messaging/handler/NotificationEventHandler.java`):
```java
@Subject(value = "event.notification.send", queue = "event-notification-workers")
public void handle(NotificationEvent event) {
    repository.saveAll(event.notifications());
    for (var dto : event.notifications()) {
        var tokens = repository.findDeviceTokens(dto.userId());
        if (tokens.isEmpty()) continue;

        // ... resolve URLs, determine image vs attachment ...

        firebasePushService.sendBulk(tokens, dto.title(), dto.message(),
                iconUrl, imageUrl, attachmentType, attachmentUrl);
    }
}
```

**Characteristics:**
- Returns immediately — caller doesn't wait for FCM delivery
- NATS queue group (`event-notification-workers`) distributes load across instances
- Good for: bulk/automated sends, event-driven flows, fire-and-forget

### Immediate vs Background: When to Use

| Criterion | sendImmediate | sendInBackground |
|-----------|--------------|------------------|
| Latency | Blocking (wait for all FCM calls) | Non-blocking (returns instantly) |
| Failure feedback | Logged per-token in calling thread | Logged in handler, caller unaware |
| Load distribution | Runs on caller's thread | NATS queue group distributes |
| Suitable for | Admin UI, one-off, debugging | Bulk, automated, event-driven |

### Android Client Integration Points

When the Android device receives a push, read the data payload to determine how to display the attachment:

```
// In FirebaseMessagingService.onMessageReceived():
String attachmentType = remoteMessage.getData().get("attachmentType");
String attachmentUrl = remoteMessage.getData().get("attachment");

switch (attachmentType) {
    case "IMAGE"   → show in image viewer
    case "DOCUMENT" → open in document viewer / download
    case "AUDIO"   → play inline or open audio player
    case "VIDEO"   → play inline or open video player
    case null      → simple text notification (no attachment)
}
```

### Key Files Reference

| Layer | File |
|-------|------|
| Notification DTO | `application/dto/NotificationDto.java` |
| NotificationEvent | `application/events/NotificationEvent.java` |
| AttachmentType enum | `application/enums/AttachmentType.java` |
| NotificationService | `application/app/notifications/service/NotificationService.java` |
| NotificationResponse | `application/app/notifications/dto/NotificationResponse.java` |
| NotificationEventPublisher | `infrastructure/messaging/publisher/NotificationEventPublisher.java` |
| NotificationEventHandler | `infrastructure/messaging/handler/NotificationEventHandler.java` |
| FirebasePushService | `infrastructure/apiclient/FirebasePushService.java` |
| FirebaseClient | `infrastructure/apiclient/FirebaseClient.java` |
| FirebaseConfig | `infrastructure/config/FirebaseConfig.java` |
| FirebasePushRequest | `infrastructure/apiclient/dto/FirebasePushRequest.java` |
| Notification entity | `infrastructure/persistence/entity/Notification.java` |
| NotificationRepository | `infrastructure/persistence/notification/NotificationRepository.java` |
| NotificationController | `presentation/notifications/controller/NotificationController.java` |
| Flyway migration | `resources/db/migration/V2__create_notifications.sql` |
