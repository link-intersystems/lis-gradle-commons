plugins {
    id("lis-gradle-commons-library")
}

dependencies {
    implementation(dependencies.gradleApi())
    implementation("org.mockito:mockito-core:5.11.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}

publishing {
    afterEvaluate {
        publications.withType<MavenPublication> {
            pom {
                name.set("Lis Gradle Mocking")
                description.set("Mocking extension for Gradle plugin tests.")
            }
        }
    }
}