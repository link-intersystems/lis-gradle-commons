package com.link_intersystems.gradle.project.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class GradleSubprojectBuilderTest {

    private GradleSubprojectBuilder gradleSubProjectBuilder;
    private File tempDir;

    @BeforeEach
    void setUp(@TempDir File tempDir) throws IOException {
        gradleSubProjectBuilder = new GradleSubprojectBuilder(tempDir.toPath().resolve("test"), ScriptLanguage.KTS);
        this.tempDir = tempDir;
    }

    @Test
    void buildFile() {
        gradleSubProjectBuilder.buildFile().append("plugins {}");

        assertThat(new File(tempDir, "test/build.gradle.kts")).hasContent("plugins {}");
    }

}