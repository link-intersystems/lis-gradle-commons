package com.link_intersystems.gradle.project.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScriptLanguageTest {

    @Test
    void kotlinBuildFileName() {
        assertEquals("build.gradle.kts", ScriptLanguage.KTS.buildFileName());
    }

    @Test
    void kotlinSettingsFileName() {
        assertEquals("settings.gradle.kts", ScriptLanguage.KTS.settingsFileName());
    }


    @Test
    void groovyBuildFileName() {
        assertEquals("build.gradle", ScriptLanguage.GROOVY.buildFileName());
    }

    @Test
    void groovySettingsFileName() {
        assertEquals("settings.gradle", ScriptLanguage.GROOVY.settingsFileName());
    }
}