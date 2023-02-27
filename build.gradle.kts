plugins {
    val kotlinVersion = "1.8.10"

    id("org.springframework.boot") version "3.0.3"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

testing {
    suites {
        withType(JvmTestSuite::class).configureEach {

            useJUnitJupiter()

            dependencies {
                implementation(project())

                implementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
            }
        }

        val test by getting(JvmTestSuite::class) {
            dependencies {}
        }

        val integration by registering(JvmTestSuite::class) {
            dependencies {
                implementation("org.springframework.boot:spring-boot-starter-test")
            }
        }

        val archTests by registering(JvmTestSuite::class) {
            dependencies {
                implementation("com.tngtech.archunit:archunit-junit5:1.0.1")
            }
        }
    }
}

tasks.named("check") {
    dependsOn(testing.suites.named("integration"))
    dependsOn(testing.suites.named("archTests"))
}
