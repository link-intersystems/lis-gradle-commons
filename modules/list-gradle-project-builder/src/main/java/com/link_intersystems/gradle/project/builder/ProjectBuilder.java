package com.link_intersystems.gradle.project.builder;

import java.io.File;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class ProjectBuilder extends AbstractProjectBuilder {

    private final File settingsFile;

    public static ProjectBuilder rootProject(File projectRoot) throws IOException {
        return rootProject(projectRoot, ScriptLanguage.KTS);
    }

    public static ProjectBuilder rootProject(File projectRoot, ScriptLanguage scriptLanguage) throws IOException {

        return new ProjectBuilder(projectRoot, scriptLanguage);
    }

    private final File projectRoot;

    public ProjectBuilder(File projectRoot, ScriptLanguage scriptLanguage) throws IOException {
        super(projectRoot, scriptLanguage);
        settingsFile = new File(projectRoot, scriptLanguage.settingsFileName());
        if (!settingsFile.createNewFile()) {
            throw new IOException(settingsFile.getAbsolutePath() + " already exists");
        }
        this.projectRoot = requireNonNull(projectRoot);
    }

    public ProjectBuilder withCompositeBuild(String path) throws IOException {
        File compositeBuildRoot = new File(projectRoot, path);
        if (!compositeBuildRoot.mkdirs()) {
            throw new IOException("Failed to create directory " + compositeBuildRoot.getAbsolutePath());
        }
        return rootProject(compositeBuildRoot);
    }

    public SubProjectBuilder withModule(String path) throws IOException {
        return withModule(path, getScriptLanguage());
    }

    public SubProjectBuilder withModule(String path, ScriptLanguage scriptLanguage) throws IOException {

        return new SubProjectBuilder(new File(getProjectRootDir(), path), scriptLanguage);
    }

    public FileContentBuilder settingsFile() {
        return new FileContentBuilder(settingsFile);
    }

}
