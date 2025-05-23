plugins {
    application

    kotlin("jvm") version "2.1.10" // Kotlin JVM plugin
    id("io.ktor.plugin") version "2.3.8" // Ktor plugin
    kotlin("plugin.serialization") version "2.1.10" // For kotlinx.serialization
    kotlin("kapt") version "2.1.10" // For annotation processing
    kotlin("plugin.allopen") version "2.1.10" // Makes classes open for frameworks like Ktor
}


group = "org.sonusid"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.4")
    implementation("io.ktor:ktor-server-netty:2.3.4")
    implementation("io.ktor:ktor-server-auth:2.3.4")
    implementation("io.ktor:ktor-server-auth-jwt:2.3.4")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
    implementation("org.mindrot:jbcrypt:0.4") // For password hashing
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}