package com.link_intersystems.gradle.project.builder;

public enum ScriptLanguage {

    KTS() {
        @Override
        public String buildFileName() {
            return "build.gradle.kts";
        }

        @Override
        public String settingsFileName() {
            return "settings.gradle.kts";
        }
    },
    GROOVY() {
        @Override
        public String buildFileName() {
            return "build.gradle";
        }

        @Override
        public String settingsFileName() {
            return "settings.gradle";
        }
    };

    public abstract String buildFileName();

    public abstract String settingsFileName();
}
