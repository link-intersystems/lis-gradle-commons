package com.link_intersystems.gradle.project.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.util.Objects.requireNonNull;

public class FileBuilder {

    private final Path filepath;

    public FileBuilder(Path filepath) throws IOException {
        if (!Files.exists(filepath)) {
            Files.createFile(filepath);
        }
        this.filepath = requireNonNull(filepath);
    }

    public Path getPath() {
        return filepath;
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

    public void append(URL resource) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.openStream()))) {
            append(appender -> {
                char[] buffer = new char[1024];
                int read = -1;
                while ((read = reader.read(buffer)) != -1) {
                    appender.write(buffer, 0, read);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
