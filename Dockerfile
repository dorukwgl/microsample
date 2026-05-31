FROM bellsoft/liberica-runtime-container:jdk-26-cds-glibc AS builder
WORKDIR /app

# Cache Gradle dependencies
COPY gradlew settings.gradle.kts build.gradle.kts gradle.properties ./
COPY gradle/ gradle/
RUN ./gradlew --no-daemon dependencies 2>/dev/null || true

# Build the application
COPY src/ src/
RUN --mount=type=cache,target=~/.gradle ./gradlew assemble -x test -x generateJooq --no-daemon

# ─────────────────────────────────────────────────────
FROM bellsoft/liberica-runtime-container:jre-26-cds-glibc
WORKDIR /app
COPY --from=builder /app/build/libs/microsample-1.0.0-all.jar app.jar
COPY microsampleServiceAccount.json ./

RUN mkdir -p /var/log/microsample
RUN mkdir -p /var/www/microsample

EXPOSE 9096

CMD ["java", \
     "--enable-native-access=ALL-UNNAMED", \
     "--add-opens", "java.base/sun.nio.ch=ALL-UNNAMED", \
     "--add-opens", "java.base/java.nio=ALL-UNNAMED", \
     "--add-opens", "java.base/jdk.internal.misc=ALL-UNNAMED", \
     "--add-opens", "java.base/java.lang.reflect=ALL-UNNAMED", \
     "--add-opens", "java.base/java.util=ALL-UNNAMED", \
     "-XX:+UseZGC", \
     "-jar", "app.jar"]