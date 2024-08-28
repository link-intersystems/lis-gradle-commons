package com.link_intersystems.gradle.project.builder;

import java.io.IOException;
import java.nio.file.Path;

public interface FileBuilderFactory<T extends FileBuilder> {

    public T create(Path path) throws IOException;
}
