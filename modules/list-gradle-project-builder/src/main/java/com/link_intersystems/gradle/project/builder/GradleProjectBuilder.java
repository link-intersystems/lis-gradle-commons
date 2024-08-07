package com.link_intersystems.gradle.project.builder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Objects.requireNonNull;

public class GradleProjectBuilder extends AbstractProjectBuilder {

    private final Path settingsFile;

    public static GradleProjectBuilder rootProject(Path projectRoot) throws IOException {
        return rootProject(projectRoot, ScriptLanguage.KTS);
    }

    public static GradleProjectBuilder rootProject(Path projectRoot, ScriptLanguage scriptLanguage) throws IOException {

        return new GradleProjectBuilder(projectRoot, scriptLanguage);
    }

    private final Path projectRoot;

    public GradleProjectBuilder(Path projectRoot, ScriptLanguage scriptLanguage) throws IOException {
        super(projectRoot, scriptLanguage);
        settingsFile = projectRoot.resolve(scriptLanguage.settingsFileName());
        Files.createFile(settingsFile);

        this.projectRoot = requireNonNull(projectRoot);
    }

    public GradleProjectBuilder withCompositeBuild(String path) throws IOException {
        Path compositeBuildRootPath = projectRoot.resolve(path);

        Files.createDirectories(compositeBuildRootPath);

        return rootProject(compositeBuildRootPath);
    }

    public GradleSubProjectBuilder withModule(String path) throws IOException {
        return withModule(path, getScriptLanguage());
    }

    public GradleSubProjectBuilder withModule(String path, ScriptLanguage scriptLanguage) throws IOException {

        return new GradleSubProjectBuilder(getProjectRootDir().resolve(path), scriptLanguage);
    }

    public FileContent settingsFile() {
        return new FileContent(settingsFile);
    }

}
