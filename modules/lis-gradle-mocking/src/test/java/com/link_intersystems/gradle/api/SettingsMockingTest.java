package com.link_intersystems.gradle.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class SettingsMockingTest {

    private SettingsMocking settingsMocking;

    @BeforeEach
    void setUp() {
        settingsMocking = new SettingsMocking();
    }

    @Test
    void settingsGradleReturnsSameSettings() {
        SettingsMocking gradleSettingsMocking = settingsMocking.getGradleMocking().getSettingsMocking();

        assertSame(settingsMocking, gradleSettingsMocking);
    }

    @Test
    void getExtensionContainerMocking() {
        ExtensionContainerMocking extensionContainerMocking = settingsMocking.getExtensionContainerMocking();
        assertNotNull(extensionContainerMocking);

        assertNotNull(settingsMocking.getSettings().getExtensions());
    }

    @Test
    void getProvidersMocking() {
        ProviderFactoryMocking providerFactoryMocking = settingsMocking.getProvidersMocking();
        assertNotNull(providerFactoryMocking);

        assertNotNull(settingsMocking.getSettings().getProviders());
    }

    @Test
    void getGradleMocking() {
        assertNotNull(settingsMocking.getGradleMocking());

        assertNotNull(settingsMocking.getSettings().getGradle());
    }
}