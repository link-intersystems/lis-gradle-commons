package com.link_intersystems.gradle.project.builder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesFileBuilder extends FileBuilder {

    public PropertiesFileBuilder(Path filepath) throws IOException {
        super(filepath);
    }

    public void append(Properties properties) {
        append(printWriter -> {
            properties.store(printWriter, "");
        });
    }
}
