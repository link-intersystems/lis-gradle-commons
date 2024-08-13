package com.link_intersystems.gradle.api;

import org.gradle.api.initialization.Settings;

import static java.util.Objects.requireNonNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Provides mocking support for a {@link Settings} instance.
 */
public class SettingsMocking {

    private final GradleMocking gradleMocking;
    private Settings settings;
    private ProviderFactoryMocking providerFactoryMocking;
    private ExtensionContainerMocking extensionContainerMocking;

    public SettingsMocking() {
        this(new GradleMocking());
    }

    SettingsMocking(GradleMocking gradleMocking) {
        this.settings = mock(Settings.class);
        this.gradleMocking = requireNonNull(gradleMocking);
        gradleMocking.setSettingsMocking(this);

        when(settings.getGradle()).thenReturn(gradleMocking.getGradle());

        extensionContainerMocking = new ExtensionContainerMocking();
        when(settings.getExtensions()).thenReturn(extensionContainerMocking.getExtensionContainer());

        providerFactoryMocking = new ProviderFactoryMocking();
        when(settings.getProviders()).thenReturn(providerFactoryMocking.getProviderFactory());
    }

    public ExtensionContainerMocking getExtensionContainerMocking() {
        return extensionContainerMocking;
    }

    public GradleMocking getGradleMocking() {
        return gradleMocking;
    }

    public Settings getSettings() {
        return settings;
    }

    /**
     * @return the {@link ProviderFactoryMocking} that one can use to set Gradle properties.
     */
    public ProviderFactoryMocking getProvidersMocking() {
        return providerFactoryMocking;
    }

}
