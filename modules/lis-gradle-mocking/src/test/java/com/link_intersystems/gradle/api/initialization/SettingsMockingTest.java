package com.link_intersystems.gradle.api.initialization;

import com.link_intersystems.gradle.api.plugins.ExtensionContainerMocking;
import com.link_intersystems.gradle.api.provider.ProviderFactoryMocking;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SettingsMockingTest {

    @Test
    void getExtensionContainerMocking() {
        SettingsMocking settingsMocking = new SettingsMocking();

        ExtensionContainerMocking extensionContainerMocking = settingsMocking.getExtensionContainerMocking();
        assertNotNull(extensionContainerMocking);

        assertNotNull(settingsMocking.getSettings().getExtensions());
    }

    @Test
    void getProvidersMocking() {
        SettingsMocking settingsMocking = new SettingsMocking();

        ProviderFactoryMocking providerFactoryMocking = settingsMocking.getProvidersMocking();
        assertNotNull(providerFactoryMocking);

        assertNotNull(settingsMocking.getSettings().getProviders());
    }

    @Test
    void getGradleMocking() {
        SettingsMocking settingsMocking = new SettingsMocking();

        assertNotNull(settingsMocking.getGradleMocking());

        assertNotNull(settingsMocking.getSettings().getGradle());
    }
}