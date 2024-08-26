rootProject.name = "lis-gradle-commons"


pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.link-intersystems.gradle.multi-module") version "0.5.3"
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}