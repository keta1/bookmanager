import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "icu.ketal.bookmanager"
version = "1.0-SNAPSHOT"
project.setProperty("mainClassName", "icu.ketal.bookmanager.Main")

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

    // todo add test code
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.withType<ShadowJar> {
    manifest {
        attributes["Main-Class"] = "icu.ketal.bookmanager.Main"
    }
}

tasks.register<Exec>("runJar") {
    group = "run"
    tasks.getByName("shadowJar").also { task ->
        dependsOn(task)
        commandLine = listOf("java", "-Djdk.gtk.version=2", "-jar",
            task.outputs.files.joinToString(" ") { it.absolutePath })
    }

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
