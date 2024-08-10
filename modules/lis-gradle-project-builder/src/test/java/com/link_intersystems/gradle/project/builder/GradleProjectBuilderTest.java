package com.link_intersystems.gradle.project.builder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class GradleProjectBuilderTest {


    private GradleProjectBuilder gradleProjectBuilder;
    private Path tempDir;

    @BeforeEach
    void setUp(@TempDir Path tempPath) throws IOException {
        gradleProjectBuilder = GradleProjectBuilder.rootProject(tempPath);
        this.tempDir = tempPath;
    }

    @Test
    void createCompositeBuild() throws IOException {
        GradleProjectBuilder includedBuildA = gradleProjectBuilder.createCompositeBuild("includedBuildA");

        assertThat(includedBuildA).isNotNull();
        assertThat(includedBuildA.getProjectRootDir()).isEqualTo(tempDir.resolve("includedBuildA"));
        assertThat(tempDir.resolve("includedBuildA/build.gradle.kts")).exists();
        assertThat(tempDir.resolve("includedBuildA/settings.gradle.kts")).exists();
    }

    @Test
    void createSubproject() throws IOException {
        gradleProjectBuilder.createSubproject("moduleA");

        assertThat(tempDir.resolve("moduleA/build.gradle.kts")).exists();

    }

    @Test
    void createSubprojectOtherScriptLanguage() throws IOException {
        gradleProjectBuilder.createSubproject("moduleA", ScriptLanguage.GROOVY);

        assertThat(tempDir.resolve("moduleA/build.gradle")).exists();
    }

    @Test
    void settingsFile() {
        gradleProjectBuilder.settingsFile().append("rootProject.name = \"test\"\n");

        Assertions.assertThat(tempDir.resolve("settings.gradle.kts")).hasContent("rootProject.name = \"test\"\n");
    }

    @Test
    void gradleProperties() {
        gradleProjectBuilder.gradleProperties().append("version = \"1.0.0\"\n");

        Assertions.assertThat(tempDir.resolve("gradle.properties")).hasContent("version = \"1.0.0\"\n");
    }
}