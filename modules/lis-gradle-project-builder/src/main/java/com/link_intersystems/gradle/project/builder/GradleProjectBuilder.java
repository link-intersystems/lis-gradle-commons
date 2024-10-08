package com.link_intersystems.gradle.project.builder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Objects.requireNonNull;

public class GradleProjectBuilder extends AbstractProjectBuilder {

    private final FileBuilder settingsFile;

    private final PropertiesFileBuilder propertiesFile;

    public static GradleProjectBuilder rootProject(Path projectRoot) throws IOException {
        return rootProject(projectRoot, ScriptLanguage.KTS);
    }

    public static GradleProjectBuilder rootProject(Path projectRoot, ScriptLanguage scriptLanguage) throws IOException {

        return new GradleProjectBuilder(projectRoot, scriptLanguage);
    }

    private final Path projectRoot;

    public GradleProjectBuilder(Path projectRoot, ScriptLanguage scriptLanguage) throws IOException {
        super(projectRoot, scriptLanguage);
        settingsFile = file(scriptLanguage.settingsFileName());
        propertiesFile = file("gradle.properties", PropertiesFileBuilder::new);

        this.projectRoot = requireNonNull(projectRoot);
    }

    public GradleProjectBuilder createCompositeBuild(String path) throws IOException {
        Path compositeBuildRootPath = projectRoot.resolve(path);

        Files.createDirectories(compositeBuildRootPath);

        return rootProject(compositeBuildRootPath);
    }

    public GradleSubprojectBuilder createSubproject(String path) throws IOException {
        return createSubproject(path, getScriptLanguage());
    }

    public GradleSubprojectBuilder createSubproject(String path, ScriptLanguage scriptLanguage) throws IOException {

        return new GradleSubprojectBuilder(getProjectRootDir().resolve(path), scriptLanguage);
    }

    public FileBuilder settingsFile() {
        return settingsFile;
    }

    public PropertiesFileBuilder gradleProperties() {
        return propertiesFile;
    }

}
