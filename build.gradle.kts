import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "icu.ketal.bookmanager"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    targetCompatibility = JavaVersion.VERSION_17
    sourceCompatibility = JavaVersion.VERSION_17
}

javafx {
    version = "14"
    modules = listOf("javafx.controls", "javafx.fxml")
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.36.0.3")
    implementation("org.mybatis:mybatis:3.5.10")
    implementation("com.jfoenix:jfoenix:9.0.10")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.withType<ShadowJar> {
    manifest {
        attributes["Main-Class"] = "icu.ketal.bookmanager.Main"
    }
    tasks.getByName("build").dependsOn(this)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
