package com.link_intersystems.gradle.project.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class FileContentTest {

    @Test
    void append(@TempDir Path tempPath) {
        Path testFile = tempPath.resolve("test.txt");
        FileContent fileContent = new FileContent(testFile);

        fileContent.append("Hello");
        fileContent.append(" World");

        assertThat(testFile).hasContent("Hello World");
    }

    @Test
    void appendWithAppendable(@TempDir Path tempPath) {

        Path testFile = tempPath.resolve("test.txt");
        FileContent fileContent = new FileContent(testFile);

        fileContent.append(appendable -> {
            appendable.append("Hello World", 0, 5);
        });

        assertThat(testFile).hasContent("Hello");
    }

    @Test
    void appendFromResource(@TempDir Path tempPath) {

        Path testFile = tempPath.resolve("test.txt");
        FileContent fileContent = new FileContent(testFile);

        fileContent.append(getClass().getResource("test.txt"));

        assertThat(testFile).hasContent("Hello World");
    }

    @Test
    void print(@TempDir Path tempPath) {

        Path testFile = tempPath.resolve("test.txt");
        FileContent fileContent = new FileContent(testFile);

        fileContent.append(printWriter -> {
            printWriter.println("plugins {");
            printWriter.println("\t`java-library`");
            printWriter.println("}");
        });

        String lineSeparator = System.lineSeparator();
        assertThat(testFile).hasContent("plugins {" + lineSeparator + "\t`java-library`" + lineSeparator + "}");
    }
}