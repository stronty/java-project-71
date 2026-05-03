plugins {
    kotlin("jvm") version "2.3.21"
    application
    id("com.github.ben-manes.versions") version "0.53.0"
    id("org.sonarqube") version "7.2.3.7755"

}
group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass.set("hexlet.code.App")
}

sonar {
    properties {
        property("sonar.projectKey", "stronty_java-project-61")
        property("sonar.organization", "stronty")
    }
}

tasks.test {
    useJUnitPlatform()
}