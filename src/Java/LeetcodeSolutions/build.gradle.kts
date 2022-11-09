import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    //id("com.github.johnrengelman.shadow") version "7.0.0"
    kotlin("jvm") version "1.7.20-Beta"
}

group = "leetcode.solutions"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}