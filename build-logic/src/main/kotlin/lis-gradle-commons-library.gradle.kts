plugins {
    id("lis-gradle-commons-maven-central-artifact")
    id("com.link-intersystems.gradle.maven-central-library")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
}