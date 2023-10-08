buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
    }
}


plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
    kotlin("jvm") version "1.9.0"
}