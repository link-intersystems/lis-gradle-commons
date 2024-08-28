package com.link_intersystems.gradle.project.builder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

public class DirectoryBuilder {

    private Path directory;

    public DirectoryBuilder(Path directory) throws IOException {
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
        this.directory = requireNonNull(directory);
    }

    public Path getPath() {
        return directory;
    }

    public DirectoryBuilder directory(String relativePath) throws IOException {
        return directory(Paths.get(relativePath), DirectoryBuilder::new);
    }


    public <T extends DirectoryBuilder> T directory(String relativePath, DirectoryBuilderFactory<T> directoryBuilderFactory) throws IOException {
        return directory(Paths.get(relativePath), directoryBuilderFactory);
    }

    public DirectoryBuilder directory(Path relativePath) throws IOException {
        return directory(relativePath, DirectoryBuilder::new);
    }

    public <T extends DirectoryBuilder> T directory(Path relativePath, DirectoryBuilderFactory<T> directoryBuilderFactory) throws IOException {
        Path nextDirectoryPath = relativePath.subpath(0, 1);
        return directoryBuilderFactory.create(directory.resolve(nextDirectoryPath));
    }

    public FileBuilder file(String relativeFilePath) throws IOException {
        return file(relativeFilePath, FileBuilder::new);
    }

    public <T extends FileBuilder> T file(String relativeFilePath, FileBuilderFactory<T> fileBuilderFactory) throws IOException {
        return file(Paths.get(relativeFilePath), fileBuilderFactory);
    }

    public FileBuilder file(Path relativeFilePath) throws IOException {
        return file(relativeFilePath, FileBuilder::new);
    }

    public <T extends FileBuilder> T file(Path relativeFilePath, FileBuilderFactory<T> fileBuilderFactory) throws IOException {
        int nameCount = relativeFilePath.getNameCount();
        if (nameCount == 1) {
            return fileBuilderFactory.create(directory.resolve(relativeFilePath));
        }

        DirectoryBuilder parentDirectory = directory(relativeFilePath.getParent());
        return parentDirectory.file(relativeFilePath.subpath(1, relativeFilePath.getNameCount()), fileBuilderFactory);
    }

}
