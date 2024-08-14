import org.gradle.tooling.GradleConnector

plugins {
    id("com.link-intersystems.gradle.maven-central-project") version "0.0.5" apply false
    id("net.researchgate.release") version "3.0.2"

}

// Work around for Cannot include build 'build-logic' in build '???'. This is not supported yet.
// See https://github.com/researchgate/gradle-release/issues/304
configure(listOf(tasks.release, tasks.runBuildTasks)) {
    configure {
        actions.clear()
        doLast {
            GradleConnector
                .newConnector()
                .forProjectDirectory(layout.projectDirectory.asFile)
                .connect()
                .use { projectConnection ->
                    val buildLauncher = projectConnection
                        .newBuild()
                        .forTasks(*tasks.toTypedArray())
                        .setStandardInput(System.`in`)
                        .setStandardOutput(System.out)
                        .setStandardError(System.err)
                    gradle.startParameter.excludedTaskNames.forEach {
                        buildLauncher.addArguments("-x", it)
                    }
                    gradle.startParameter.projectProperties.forEach { t, u ->
                        buildLauncher.addArguments("-P$t=$u")
                    }

                    buildLauncher.run()
                }
        }
    }
}

release {
    tagTemplate = "v\${version}"

    git {
        pushToRemote = null
    }
}