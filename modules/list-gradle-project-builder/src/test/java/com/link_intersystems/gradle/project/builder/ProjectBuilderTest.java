package com.link_intersystems.gradle.project.builder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectBuilderTest {


    private ProjectBuilder projectBuilder;
    private File tempDir;

    @BeforeEach
    void setUp(@TempDir File tempDir) throws IOException {
        projectBuilder = ProjectBuilder.rootProject(tempDir);
        this.tempDir = tempDir;
    }

    @Test
    void withCompositeBuild() throws IOException {
        ProjectBuilder includedBuildA = projectBuilder.withCompositeBuild("includedBuildA");

        assertThat(includedBuildA).isNotNull();
        assertThat(includedBuildA.getProjectRootDir()).isEqualTo(new File(tempDir, "includedBuildA"));
        assertThat(new File(tempDir, "includedBuildA/build.gradle.kts")).exists();
        assertThat(new File(tempDir, "includedBuildA/settings.gradle.kts")).exists();
    }

    @Test
    void withModule() throws IOException {
        projectBuilder.withModule("moduleA");

        assertThat(new File(tempDir, "moduleA/build.gradle.kts")).exists();

    }

    @Test
    void withModuleOtherScriptLanguage() throws IOException {
        projectBuilder.withModule("moduleA", ScriptLanguage.GROOVY);

        assertThat(new File(tempDir, "moduleA/build.gradle")).exists();
    }

    @Test
    void settingsFile() {
        projectBuilder.settingsFile().append("rootProject.name = \"test\"\n");

        Assertions.assertThat(new File(tempDir, "settings.gradle.kts")).hasContent("rootProject.name = \"test\"\n");
    }
}