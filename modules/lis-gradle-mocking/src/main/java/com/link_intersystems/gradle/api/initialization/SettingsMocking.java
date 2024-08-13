package com.link_intersystems.gradle.api.initialization;

import com.link_intersystems.gradle.api.invocation.GradleMocking;
import com.link_intersystems.gradle.api.plugins.ExtensionContainerMocking;
import com.link_intersystems.gradle.api.provider.ProviderFactoryMocking;
import org.gradle.api.initialization.Settings;
import org.gradle.api.provider.ProviderFactory;

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

    /**
     * Creates a {@link SettingsMocking} with a new {@link GradleMocking}.
     */
    public SettingsMocking() {
        this(new GradleMocking());
    }

    public SettingsMocking(GradleMocking gradleMocking) {
        this.settings = mock(Settings.class);
        this.gradleMocking = requireNonNull(gradleMocking);

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
