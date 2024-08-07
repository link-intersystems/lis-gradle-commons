package com.link_intersystems.gradle.project.builder;

import java.io.File;
import java.io.IOException;

public class SubProjectBuilder extends AbstractProjectBuilder {

    protected SubProjectBuilder(File moduleRootDir, ScriptLanguage scriptLanguage) throws IOException {
        super(moduleRootDir, scriptLanguage);
    }

}
