package com.link_intersystems.gradle.project.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class FileBuilderTest {

    @Test
    void append(@TempDir Path tempPath) throws IOException {
        Path testFile = tempPath.resolve("test.txt");
        FileBuilder fileBuilder = new FileBuilder(testFile);

        fileBuilder.append("Hello");
        fileBuilder.append(" World");

        assertThat(testFile).hasContent("Hello World");
    }

    @Test
    void appendWithAppendable(@TempDir Path tempPath) throws IOException {

        Path testFile = tempPath.resolve("test.txt");
        FileBuilder fileBuilder = new FileBuilder(testFile);

        fileBuilder.append(appendable -> {
            appendable.append("Hello World", 0, 5);
        });

        assertThat(testFile).hasContent("Hello");
    }

    @Test
    void appendFromResource(@TempDir Path tempPath) throws IOException {

        Path testFile = tempPath.resolve("test.txt");
        FileBuilder fileBuilder = new FileBuilder(testFile);

        fileBuilder.append(getClass().getResource("test.txt"));

        assertThat(testFile).hasContent("Hello World");
    }

    @Test
    void print(@TempDir Path tempPath) throws IOException {

        Path testFile = tempPath.resolve("test.txt");
        FileBuilder fileBuilder = new FileBuilder(testFile);

        fileBuilder.append(printWriter -> {
            printWriter.println("plugins {");
            printWriter.println("\t`java-library`");
            printWriter.println("}");
        });

        String lineSeparator = System.lineSeparator();
        assertThat(testFile).hasContent("plugins {" + lineSeparator + "\t`java-library`" + lineSeparator + "}");
    }
}