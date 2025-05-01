plugins {
    java
    `maven-publish`
    id("com.gradleup.shadow") version "8.3.0"
}

group = "club.frozed.tablist"
version = "1.0-SNAPSHOT-R0-1.8"

repositories {
    mavenCentral()
    maven("https://repo.codemc.io/repository/maven-releases/") {
        name = "codemc-releases"
    }
    maven("https://repo.codemc.io/repository/maven-snapshots/") {
        name = "codemc-snapshots"
    }
}

dependencies {
    compileOnly(files("libs/PaperSpigot-1.8.8-R0.1-SNAPSHOT.jar"))

    compileOnly("org.projectlombok:lombok:1.18.38")
    compileOnly("com.github.retrooper:packetevents-spigot:2.7.0")

    annotationProcessor("org.projectlombok:lombok:1.18.38")
}

val targetJavaVersion = 8
java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    }
}

tasks.compileJava {
    options.encoding = "UTF-8"

    if (targetJavaVersion >= 8 || JavaVersion.current().isJava8Compatible) {
        options.release.set(targetJavaVersion)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            artifact(tasks["jar"])

            pom {
                name.set(project.name)
                description.set("Frozed Tablist Library")
                url.set("https://frozed.club/")
            }
        }
    }

    repositories {
        mavenLocal()
    }
}
