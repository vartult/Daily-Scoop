// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath(Dependencies.gradle)
        classpath (Dependencies.kotlin_plugin)
        classpath ("com.google.gms:google-services:4.3.3")
        classpath ("com.google.firebase:firebase-crashlytics-gradle:2.0.0-beta02")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

tasks.register("clean").configure{
    delete(rootProject.buildDir)
}
