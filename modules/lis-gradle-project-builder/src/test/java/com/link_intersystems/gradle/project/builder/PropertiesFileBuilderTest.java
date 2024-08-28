package com.link_intersystems.gradle.project.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertiesFileBuilderTest {

    @Test
    void writeProperties(@TempDir Path tempDir) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("foo", "bar");
        properties.setProperty("bar", "foo");

        Path propertiesFilepath = tempDir.resolve("test.properties");
        PropertiesFileBuilder propertiesContent = new PropertiesFileBuilder(propertiesFilepath);
        propertiesContent.append(properties);

        Properties readProperties = new Properties();
        try (FileReader reader = new FileReader(propertiesFilepath.toFile())) {
            readProperties.load(reader);
        }

        assertEquals(properties, readProperties);
    }

}