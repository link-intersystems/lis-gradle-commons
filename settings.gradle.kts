rootProject.name = "lis-gradle-commons"


pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

plugins {
    id("com.link-intersystems.gradle.multi-module") version "+"
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}