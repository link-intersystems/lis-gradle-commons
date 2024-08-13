package com.link_intersystems.gradle.api.invocation;

import com.link_intersystems.gradle.api.initialization.SettingsMocking;
import org.gradle.api.Action;
import org.gradle.api.initialization.Settings;
import org.gradle.api.invocation.Gradle;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GradleMocking {

    private Gradle gradle;
    private SettingsMocking settingsMocking;

    public GradleMocking() {
        this.gradle = mock(Gradle.class);
    }

    public Gradle getGradle() {
        return gradle;
    }

    public SettingsMocking getSettingMocking() {
        if (settingsMocking == null) {
            settingsMocking = new SettingsMocking(this);
        }
        return settingsMocking;
    }

    /**
     * Executes the {@link Action}s previously registered with {@link Gradle#settingsEvaluated(Action)}.
     */
    public void settingsEvaluatedAction() {
        List<Action<Settings>> settingsEvaluatedAction = getSettingsEvaluatedActions();

        Settings settings = getSettingMocking().getSettings();

        for (Action<Settings> settingsAction : settingsEvaluatedAction) {
            settingsAction.execute(settings);
        }
    }

    @SuppressWarnings("unchecked")
    protected List<Action<Settings>> getSettingsEvaluatedActions() {
        ArgumentCaptor<Action<Settings>> captor = ArgumentCaptor.forClass(Action.class);
        verify(gradle).settingsEvaluated(captor.capture());

        return captor.getAllValues();
    }
}
