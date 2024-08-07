package com.link_intersystems.gradle.project.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class FileContentBuilderTest {

    @Test
    void append(@TempDir File tempDir) {
        File testFile = new File(tempDir, "test.txt");
        FileContentBuilder fileContentBuilder = new FileContentBuilder(testFile);

        fileContentBuilder.append("Hello");
        fileContentBuilder.append(" World");

        assertThat(testFile).hasContent("Hello World");
    }

    @Test
    void appendWithAppendable(@TempDir File tempDir) {

        File testFile = new File(tempDir, "test.txt");
        FileContentBuilder fileContentBuilder = new FileContentBuilder(testFile);

        fileContentBuilder.append(appendable -> {
            appendable.append("Hello World", 0, 5);
        });

        assertThat(testFile).hasContent("Hello");
    }
}