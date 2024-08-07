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
            try {
                appender.append(contentToAppend);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void append(AppendableConsumer contentAppender) {

        try (PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(filepath, CREATE, APPEND))) {
            contentAppender.accept(printWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
