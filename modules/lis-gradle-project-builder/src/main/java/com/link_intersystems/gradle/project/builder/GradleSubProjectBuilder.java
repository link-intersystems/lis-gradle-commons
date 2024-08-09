package com.link_intersystems.gradle.project.builder;

import java.io.IOException;
import java.nio.file.Path;

public class GradleSubProjectBuilder extends AbstractProjectBuilder {

    protected GradleSubProjectBuilder(Path moduleRootPath, ScriptLanguage scriptLanguage) throws IOException {
        super(moduleRootPath, scriptLanguage);
    }

}
