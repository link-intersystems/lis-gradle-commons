package com.link_intersystems.gradle.project.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class SubProjectBuilderTest {

    private SubProjectBuilder subProjectBuilder;
    private File tempDir;

    @BeforeEach
    void setUp(@TempDir File tempDir) throws IOException {
        subProjectBuilder = new SubProjectBuilder(new File(tempDir, "test"), ScriptLanguage.KTS);
        this.tempDir = tempDir;
    }

    @Test
    void buildFile() {
        subProjectBuilder.buildFile().append("plugins {}");

        assertThat(new File(tempDir, "test/build.gradle.kts")).hasContent("plugins {}");
    }

}