package com.link_intersystems.gradle.project.builder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Objects.requireNonNull;

public class AbstractProjectBuilder {
    private final Path projectRootDir;
    private final ScriptLanguage scriptLanguage;
    private final Path buildFile;

    public AbstractProjectBuilder(Path projectRootDir, ScriptLanguage scriptLanguage) throws IOException {
        this.projectRootDir = requireNonNull(projectRootDir);
        this.scriptLanguage = requireNonNull(scriptLanguage);

        buildFile = this.projectRootDir.resolve(scriptLanguage.buildFileName());

        if (!Files.isDirectory(projectRootDir)) {
            Files.createDirectories(this.projectRootDir);
        }

        Files.createFile(buildFile);
    }

    protected Path getProjectRootDir() {
        return projectRootDir;
    }

    public ScriptLanguage getScriptLanguage() {
        return scriptLanguage;
    }

    public FileContent buildFile() {
        return new FileContent(buildFile);
    }
}
