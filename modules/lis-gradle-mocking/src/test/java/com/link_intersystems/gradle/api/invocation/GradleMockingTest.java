package com.link_intersystems.gradle.api.invocation;

import org.gradle.api.Action;
import org.gradle.api.initialization.Settings;
import org.gradle.api.invocation.Gradle;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


class GradleMockingTest {

    @Test
    @SuppressWarnings("unchecked")
    void execSettingsEvaluated() {
        GradleMocking gradleMocking = new GradleMocking();
        Gradle gradle = gradleMocking.getGradle();
        Action<Settings> action = (Action<Settings>) mock(Action.class);

        gradle.settingsEvaluated(action);

        verifyNoInteractions(action);
        gradleMocking.execSettingsEvaluated();
        verify(action, times(1)).execute(gradleMocking.getSettingMocking().getSettings());
    }
}