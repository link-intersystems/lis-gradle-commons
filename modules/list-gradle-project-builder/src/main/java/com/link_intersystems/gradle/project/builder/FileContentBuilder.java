package com.link_intersystems.gradle.project.builder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static java.util.Objects.requireNonNull;

public class FileContentBuilder {

    public interface AppendableConsumer {
        void accept(Appendable appendable) throws IOException;
    }

    private final File file;

    public FileContentBuilder(File file) {
        this.file = requireNonNull(file);
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
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true))) {
            contentAppender.accept(printWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
