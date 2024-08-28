package com.link_intersystems.gradle.project.builder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Objects.requireNonNull;

public class AbstractProjectBuilder extends DirectoryBuilder {
    private final ScriptLanguage scriptLanguage;
    private final FileBuilder buildFile;

    public AbstractProjectBuilder(Path projectRootDir, ScriptLanguage scriptLanguage) throws IOException {
        super(projectRootDir);
        this.scriptLanguage = requireNonNull(scriptLanguage);

        buildFile = file(scriptLanguage.buildFileName());
    }

    protected Path getProjectRootDir() {
        return getPath();
    }

    public ScriptLanguage getScriptLanguage() {
        return scriptLanguage;
    }

    public FileBuilder buildFile() {
        return buildFile;
    }
}
