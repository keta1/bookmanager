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
    version = "17"
    modules = listOf("javafx.controls", "javafx.fxml")
}

tasks.withType<ShadowJar> {
    manifest {
        attributes["Main-Class"] = "icu.ketal.bookmanager.Main"
    }
}

val arg = listOf(
    // support java 17+
    "--add-exports=javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED",
    "--add-exports=javafx.controls/com.sun.javafx.scene.control.behavior=ALL-UNNAMED",
    "--add-exports=javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED",
    "--add-opens=javafx.controls/javafx.scene.control.skin=com.jfoenix",
    "--add-exports=javafx.controls/com.sun.javafx.scene.control=com.jfoenix",
    "--add-exports=javafx.base/com.sun.javafx.binding=com.jfoenix",
    // the next line changes everything.
    "--add-opens=java.base/java.lang.reflect=com.jfoenix",

    "--add-exports=javafx.controls/javafx.scene.control.skin=com.jfoenix",
    "--add-exports=java.base/java.lang.reflect=ALL-UNNAMED",
    "--add-exports=java.base/java.lang.reflect=com.jfoenix",
    "--add-exports=javafx.controls/com.Sun.javafx.scene.control.behavior=com.jfoenix",
    "--add-exports=javafx.graphics/com.sun.javafx.stage=com.jfoenix",
    "--add-exports=javafx.controls/com.sun.javafx.scene.control.behavior=com.jfoenix",

    "--add-exports=javafx.base/com.sun.javafx.event=ALL-UNNAMED",

    "--add-exports=javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED",
    "--add-exports=javafx.controls/com.sun.javafx.scene.control.behavior" +
            "=ALL-UNNAMED",
    "--add-exports=javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED",
    "--add-exports=javafx.controls/com.sun.javafx.scene.control.behavior" +
            "=com.jfoenix",
    "--add-exports=javafx.controls/com.sun.javafx.binding=com.jfoenix",
    "--add-opens=javafx.controls/javafx.scene.control.skin=com.jfoenix",
    "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED",
    "--add-exports=javafx.controls/com.sun.javafx.scene.control.behavior" +
            "=com.jfoenix",
    "--add-exports=javafx.controls/com.sun.javafx.scene.control=com.jfoenix",
    "--add-exports=javafx.base/com.sun.javafx.binding=com.jfoenix",
    "--add-exports=javafx.graphics/com.sun.javafx.stage=com.jfoenix",
    "--add-exports=javafx.base/com.sun.javafx.event=com.jfoenix",

    "--add-exports=javafx.controls/com.sun.javafx.scene.control.behavior=com.jfoenix",
    "--add-exports=javafx.controls/com.sun.javafx.scene.control=com.jfoenix",
    "--add-exports=javafx.base/com.sun.javafx.binding=com.jfoenix",
    "--add-exports=javafx.graphics/com.sun.javafx.stage=com.jfoenix",
    "--add-exports=javafx.base/com.sun.javafx.event=com.jfoenix",
    // fix fcitx5 input
    "-Djdk.gtk.version=2"
)

tasks.withType<JavaExec> {
    jvmArgs = arg
}

tasks.register<Exec>("runJar") {
    group = "run"
    tasks.getByName("shadowJar").also { task ->
        dependsOn(task)
        val cmd = "java ${arg.joinToString(" ")} " +
                "-jar ${task.outputs.files.joinToString(" ") { it.absolutePath }}"
        commandLine = cmd.split(" ")
    }
}

dependencies {
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.xerial:sqlite-jdbc:3.36.0.3")
    implementation("org.mybatis:mybatis:3.5.10")

    implementation("com.jfoenix:jfoenix:9.0.10")
    implementation("io.datafx:datafx:8.0.1")
    implementation("io.datafx:flow:8.0.1")
    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    implementation("org.kordamp.ikonli:ikonli-fontawesome5-pack:12.3.1")

    // todo add test code
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
