plugins {
    `java-library`
    `maven-publish`
    `ivy-publish`
    id("com.link-intersystems.gradle.maven-central-library")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

signing {
    val signingKey = providers.environmentVariable("GPG_SIGNING_KEY")
    val signingPassphrase = providers.environmentVariable("GPG_SIGNING_PASSPHRASE")
    if (signingKey.isPresent && signingPassphrase.isPresent) {
        useInMemoryPgpKeys(signingKey.get(), signingPassphrase.get())
        sign(publishing.publications)
        logger.lifecycle("Signing publications")
    }
}

afterEvaluate {
    publishing {
        publications.withType<MavenPublication> {
            pom {
                url.set("https://github.com/link-intersystems/lis-gradle-commons")
                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("rene.link")
                        name.set("René Link")
                        email.set("rene.link@link-intersystems.com")
                        organization.set("Link Intersystems GmbH")
                        organizationUrl.set("https://www.link-intersystems.com")
                        url.set("https://stackoverflow.com/users/974186/ren%C3%A9-link")
                        roles.set(listOf("developer"))
                    }
                }
                scm {
                    url.set("https://github.com/link-intersystems/lis-gradle-commons")
                    connection.set("scm:git:https://github.com/link-intersystems/lis-gradle-commons.git")
                    developerConnection.set("scm:git:https://github.com/link-intersystems/lis-gradle-commons.git")
                }
            }
        }
    }
}









