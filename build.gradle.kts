import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "net.nlt.matrix.multiplication"
version = "1.0-SNAPSHOT"

plugins {
    java
    kotlin("jvm") version "1.3.70"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.3.3")

    implementation("org.slf4j", "slf4j-api", "1.2.3")
    implementation("ch.qos.logback", "logback-core", "1.2.3")
    implementation("ch.qos.logback", "logback-classic", "1.2.3")

    testImplementation("org.junit.jupiter", "junit-jupiter", "5.6.0")
    testImplementation("org.assertj", "assertj-core", "3.15.0")
    implementation(kotlin("stdlib"))
}

tasks.test {
    useJUnitPlatform()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "11"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "11"
}
