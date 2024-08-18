plugins {
    id("lis-gradle-commons-platform")
}

dependencies {
    constraints {
        api(project(":modules:lis-gradle-mocking"))
        api(project(":modules:lis-gradle-project-builder"))
    }
}

afterEvaluate {
    publishing.publications.withType<MavenPublication> {
        pom {
            name.set("Lis Gradle Commons Platform")
            description.set("Dependency management for the lis-gradle-commons libraries.")
        }
    }
}