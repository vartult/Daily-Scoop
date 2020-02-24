// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath(Dependencies.gradle)
        classpath (Dependencies.kotlin_plugin)
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
