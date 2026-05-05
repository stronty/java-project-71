plugins {
    kotlin("jvm") version "2.3.21"
    application
    id("com.github.ben-manes.versions") version "0.53.0"
    id("org.sonarqube") version "7.3.0.8198"
    jacoco
    checkstyle

}
group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
sonar {
    properties {
        property("sonar.projectKey", "stronty_java-project-71")
        property("sonar.organization", "stronty")
    }
}
dependencies {
    implementation(kotlin("stdlib"))
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass.set("hexlet.code.App")
}

tasks.withType<Checkstyle> {

    configFile = file("config/checkstyle/checkstyle.xml")
    outputs.upToDateWhen { false }
    ignoreFailures = false
    maxWarnings = 0
}

tasks.test {
    useJUnitPlatform()

}