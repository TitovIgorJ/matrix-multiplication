group = "net.nlt.matrix.multiplication"
version = "1.0-SNAPSHOT"

plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j", "slf4j-api", "1.2.3")
    implementation("ch.qos.logback", "logback-core", "1.2.3")
    implementation("ch.qos.logback", "logback-classic", "1.2.3")

    testImplementation("junit", "junit", "4.12")
    testImplementation("org.assertj", "assertj-core", "3.8.0")
}
