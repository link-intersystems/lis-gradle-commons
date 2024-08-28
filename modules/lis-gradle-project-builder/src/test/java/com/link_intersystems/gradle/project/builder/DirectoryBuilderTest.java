package com.link_intersystems.gradle.project.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class DirectoryBuilderTest {

    @Test
    void build(@TempDir Path tempDir) throws IOException {
        DirectoryBuilder directoryBuilder = new DirectoryBuilder(tempDir);

        FileBuilder fileBuilder = directoryBuilder.file("rootFolder/parentFolder/file.txt");
        fileBuilder.append("Hello");

        assertThat(tempDir.resolve("rootFolder/parentFolder/file.txt")).hasContent("Hello");
    }
}