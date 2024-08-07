package com.link_intersystems.gradle.project.builder;

import java.io.File;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class AbstractProjectBuilder {
    private final File projectRootDir;
    private final ScriptLanguage scriptLanguage;
    private final File buildFile;

    public AbstractProjectBuilder(File projectRootDir, ScriptLanguage scriptLanguage) throws IOException {
        this.projectRootDir = requireNonNull(projectRootDir);
        this.scriptLanguage = requireNonNull(scriptLanguage);

        buildFile = new File(this.projectRootDir, scriptLanguage.buildFileName());

        if (!projectRootDir.isDirectory()) {
            if (!this.projectRootDir.mkdirs()) {
                throw new IllegalStateException("Failed to create directory " + projectRootDir.getAbsolutePath());
            }
        }

        if (!buildFile.createNewFile()) {
            throw new IllegalStateException("Failed to create new file " + buildFile.getAbsolutePath());
        }
    }

    protected File getProjectRootDir() {
        return projectRootDir;
    }

    public ScriptLanguage getScriptLanguage() {
        return scriptLanguage;
    }

    public FileContentBuilder buildFile() {
        return new FileContentBuilder(buildFile);
    }
}
