import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    kotlin("jvm")

    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")

    // Apply the application plugin to add support for building a CLI application.
    application
}

fun prop(key : String) : String {
    return gradle.rootProject.extra[key]?.toString() ?: return "NULL"
}

group = prop("project-group")
version = prop("project-version")
val projectName = prop("project-name")
val projectMainClass = prop("project-main-class")
val jvmTarget = prop("jvm-target")
println("Kotlin Gradle Build :: $group :: $version :: $projectName :: $projectMainClass :: Targeting JVM $jvmTarget")

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    /* == MAIN == */
    compile(kotlin("stdlib"))
    compile(kotlin("reflect"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    /* == DISKORD == */
    implementation("com.jessecorbett:diskord:${prop("diskord-version")}")

    /* == SPRING == */
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
    implementation("org.apache.logging.log4j:log4j-api:${prop("log4j-version")}")
    implementation("org.apache.logging.log4j:log4j-core:${prop("log4j-version")}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

application {
    // Define the main class for the application.
    mainClassName = projectMainClass
}

java {
    val javaVersion = JavaVersion.toVersion(jvmTarget)
    targetCompatibility = javaVersion
    sourceCompatibility = javaVersion
}

val startAppNotifyTask = tasks.register("startAppNotify") {
    println("Application is starting...")
}!!


tasks {
    withType<BootJar> {
        archiveName = projectName
        mainClassName = projectMainClass
        dependsOn(startAppNotifyTask)
    }

    withType<BootRun> {
        main = projectMainClass
        args("--spring.profiles.active=local")
        dependsOn(startAppNotifyTask)
    }

    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = prop("jvm-target")
        }
    }
}