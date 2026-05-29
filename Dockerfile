FROM bellsoft/hardened-liberica-runtime-container:jre-26-cds-slim-glibc AS builder
WORKDIR /app

# Cache Gradle dependencies
COPY gradlew settings.gradle.kts build.gradle.kts ./
COPY gradle/ gradle/
RUN ./gradlew --no-daemon dependencies 2>/dev/null || true

# Build the application
COPY src/ src/
RUN ./gradlew assemble -x test --no-daemon

# ─────────────────────────────────────────────────────
FROM bellsoft/liberica-runtime-container:jre-26-cds-slim-glibc
WORKDIR /app
COPY --from=builder /app/build/libs/microsample-1.0.0-all.jar app.jar

EXPOSE 9096

CMD ["java", \
     "--enable-native-access=ALL-UNNAMED", \
     "--add-opens", "java.base/sun.nio.ch=ALL-UNNAMED", \
     "--add-opens", "java.base/java.nio=ALL-UNNAMED", \
     "-XX:+UseZGC", \
     "-XX:+ZGenerational", \
     "-jar", "app.jar"]