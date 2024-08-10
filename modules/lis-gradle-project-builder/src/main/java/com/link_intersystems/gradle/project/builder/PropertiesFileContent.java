package com.link_intersystems.gradle.project.builder;

import java.nio.file.Path;
import java.util.Properties;

public class PropertiesFileContent extends FileContent {

    public PropertiesFileContent(Path filepath) {
        super(filepath);
    }

    public void append(Properties properties) {
        append(printWriter -> {
            properties.store(printWriter, "");
        });
    }
}
