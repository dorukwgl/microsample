import org.jooq.meta.jaxb.ForcedType

val awssdkVersion = "2.44.8"
val postgresVersion = "42.7.11"
val flywayPostgresVersion = "12.6.1"
val jimmerVersion = "0.10.7"
val jooqVersion = "3.21.4"
val lombokVersion = "1.18.46"

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        // Defines the JDBC driver and Flyway extension for the Gradle build tool itself
        classpath("org.postgresql:postgresql:42.7.11")
        classpath("org.flywaydb:flyway-database-postgresql:12.6.1")
    }
}

plugins {
    id("io.micronaut.application") version "4.6.2"
    id("com.gradleup.shadow") version "9.4.1"
    id("io.micronaut.test-resources") version "4.6.2"
    id("io.micronaut.aot") version "4.6.2"
    id("org.flywaydb.flyway") version "12.6.1"
    id("nu.studer.jooq") version "10.2.1"
}

version = "1.0.0"
group = "com.doruk"

repositories {
    mavenCentral()
}

dependencies {
    // lombok
    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    annotationProcessor("io.micronaut:micronaut-inject-java")
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.openapi:micronaut-openapi")
    annotationProcessor("io.micronaut.security:micronaut-security-annotations")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    annotationProcessor("io.micronaut.validation:micronaut-validation-processor")

    // for jimmer and graalvm
    annotationProcessor("org.babyfish.jimmer:jimmer-apt:$jimmerVersion")
    annotationProcessor("io.micronaut:micronaut-graal")

    // --- BOMs (Dependency Management) ---
    implementation(platform("software.amazon.awssdk:bom:$awssdkVersion"))

    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-http-server-netty")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut.flyway:micronaut-flyway")
    implementation("io.micronaut.nats:micronaut-nats")
    implementation("io.micronaut.redis:micronaut-redis-lettuce")
    implementation("io.micronaut.security:micronaut-security-jwt")
    implementation("io.micronaut.security:micronaut-security-oauth2")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.validation:micronaut-validation")

    // third party deps
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("io.micrometer:context-propagation")

    // Jimmer
    implementation("org.babyfish.jimmer:jimmer-sql:$jimmerVersion")
    // JOOQ
    implementation("io.micronaut.sql:micronaut-jooq")
    implementation("org.jooq:jooq:$jooqVersion")
    jooqGenerator("org.jooq:jooq-codegen:$jooqVersion")
    jooqGenerator("org.postgresql:postgresql:$postgresVersion")

    // Utilities
    implementation("nl.basjes.parse.useragent:yauaa:8.1.1")
    implementation("net.coobird:thumbnailator:0.4.21")
    implementation("software.amazon.awssdk:s3")
    implementation("de.mkammerer:argon2-jvm:2.12")
    // compression
    implementation("com.aayushatharva.brotli4j:brotli4j:1.23.0")
    runtimeOnly("com.aayushatharva.brotli4j:native-linux-x86_64:1.23.0")

    // logging
    runtimeOnly("ch.qos.logback:logback-classic")
    // Log4j 2 Bridge (Required for Yauaa to log via Logback)
    implementation("org.apache.logging.log4j:log4j-to-slf4j:2.26.0")
    implementation("org.apache.logging.log4j:log4j-api:2.26.0")

    // --- Compile Only / Provided ---
    compileOnly("io.micronaut.openapi:micronaut-openapi-annotations")

    compileOnly("io.micronaut.validation:micronaut-validation-processor")

    // --- Runtime Only ---
    runtimeOnly("org.flywaydb:flyway-database-postgresql:$flywayPostgresVersion")
    runtimeOnly("org.postgresql:postgresql:$postgresVersion")
    runtimeOnly("org.yaml:snakeyaml")
    // Native transport
    runtimeOnly("io.netty:netty-transport-native-io_uring::linux-x86_64")
    runtimeOnly("io.netty:netty-transport-native-epoll::linux-x86_64")

    developmentOnly("io.micronaut.controlpanel:micronaut-control-panel-management")
    developmentOnly("io.micronaut.controlpanel:micronaut-control-panel-ui")

    aotPlugins(platform("io.micronaut.platform:micronaut-platform:4.10.14"))
    aotPlugins("io.micronaut.security:micronaut-security-aot")

    // Testcontainers
    testImplementation("org.testcontainers:testcontainers:2.0.5")
    testImplementation("org.testcontainers:testcontainers-junit-jupiter:2.0.5")
    testImplementation("org.testcontainers:testcontainers-postgresql:2.0.5")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


application {
    mainClass = "com.doruk.Application"
}
java {
    sourceCompatibility = JavaVersion.toVersion("26")
    targetCompatibility = JavaVersion.toVersion("26")
}

sourceSets {
    main {
        java {
            srcDir("src/main/generated-jooq")
        }
    }
}

// Logic to replicate Maven Profiles:
// By default, don't generate docs. If `-Pdocs` is passed to gradle, enable them.
val generateDocs = project.hasProperty("docs")

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(listOf(
        "-Amicronaut.processing.group=com.doruk",
        "-Amicronaut.processing.module=microsample"
    ))

    if (generateDocs) {
        options.compilerArgs.add("-Amicronaut.openapi.views.spec=swagger-ui.enabled=true,swagger-ui.theme=material")
    }
}

// --- Flyway Configuration
flyway {
    url = "jdbc:postgresql://localhost:5432/microsample"
    user = "doruk"
    password = "dorukdb"
    schemas = arrayOf("sample")
    locations = arrayOf("filesystem:src/main/resources/db/migration")
}

jooq {
    version.set(jooqVersion)

    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(true)

            jooqConfiguration.apply {
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://localhost:5432/microsample"
                    user = "doruk"
                    password = "dorukdb"
                }

                generator.apply {
                    name = "org.jooq.codegen.JavaGenerator"

                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "sample"

                        forcedTypes = listOf(
                            ForcedType()
                                .withUserType(" com.doruk.domain.shared.enums.MultiAuthType")
                                .withEnumConverter(true)
                                .withIncludeTypes("multi_auth_type"),

                            ForcedType()
                                .withUserType("com.doruk.domain.shared.enums.UserAccountStatus")
                                .withEnumConverter(true)
                                .withIncludeTypes("user_status"),

                            ForcedType()
                                .withUserType("com.doruk.application.enums.AttachmentType")
                                .withEnumConverter(true)
                                .withIncludeTypes("attachment_type"),
                        )
                    }

                    generate.apply {
                        isImmutablePojos = true
                        isFluentSetters = true
                        isJavaTimeTypes = true
                        isJpaAnnotations = true
                    }

                    target.apply {
                        packageName = "com.doruk.jooq"
                        directory = "src/main/generated-jooq"
                    }
                }
            }
        }
    }
}

graalvmNative.toolchainDetection = false

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.doruk.*")
    }
    testResources {
        enabled = false
        additionalModules.add("jdbc-postgresql")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = false
        configurationProperties.put("micronaut.security.jwks.enabled","false")
    }
}

tasks.shadowJar {
    // Output filename (no "shadow" suffix)
    archiveClassifier.set("")
    archiveFileName.set("${project.name}-${project.version}-all.jar")

    // Merge META-INF/services, spring.factories, etc.
    mergeServiceFiles()

    // Keep the entry point
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}

// Disable plain jar task (we only want the shadow/obfuscated one)
tasks.named<Jar>("jar") {
    enabled = true
}

// Make assemble depend on shadowJar
tasks.assemble {
    dependsOn(tasks.shadowJar)
}

tasks.matching { it.name == "generateJooq" }.configureEach {
    dependsOn("flywayMigrate")
}

tasks.named<JavaExec>("run") {
    jvmArgs(
        // Disable NioIoHandler selector instrumentation (fails on Java 26)
        "-Dio.netty.noIoHandler=true",
        // Allow Netty to access native transport and JDK internals
        "--add-opens", "java.base/sun.nio.ch=ALL-UNNAMED",
        "--add-opens", "java.base/java.nio=ALL-UNNAMED",
        "--add-opens", "java.base/jdk.internal.misc=ALL-UNNAMED",
        "--add-opens", "java.base/java.lang.reflect=ALL-UNNAMED",
        "--add-opens", "java.base/java.util=ALL-UNNAMED",
        "--add-opens", "java.base/sun.nio.ch=ALL-UNNAMED",
        "--enable-native-access=ALL-UNNAMED"
    )
}


