package com.link_intersystems.gradle.project.builder;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.util.Objects.requireNonNull;

public class FileContent {

    private final Path filepath;

    public FileContent(Path filepath) {
        this.filepath = requireNonNull(filepath);
    }

    public void append(String contentToAppend) {
        append(appender -> {
            appender.append(contentToAppend);
        });
    }

    public void append(IOConsumer<PrintWriter> contentPrinter) {
        try (PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(filepath, CREATE, APPEND))) {
            contentPrinter.accept(printWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
