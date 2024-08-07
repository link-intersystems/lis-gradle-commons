plugins {
    id("lis-gradle-commons-library")
}


dependencies {
    implementation("org.junit.jupiter:junit-jupiter-api:5.10.2")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.assertj:assertj-core:3.20.2")
}

publishing {
    afterEvaluate {
        publications.withType<MavenPublication> {
            pom {
                name.set("Lis Gradle Project Builder")
                description.set("Classes and utilities useful for Gradle development.")
            }
        }
    }
}