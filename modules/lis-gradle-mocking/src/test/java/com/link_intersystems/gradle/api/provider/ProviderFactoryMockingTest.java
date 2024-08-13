package com.link_intersystems.gradle.api.provider;

import org.gradle.api.provider.Provider;
import org.gradle.api.provider.ProviderFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProviderFactoryMockingTest {

    private ProviderFactoryMocking providerFactoryMocking;

    @BeforeEach
    void setUp() {
        providerFactoryMocking = new ProviderFactoryMocking();
    }

    @Test
    void getProviderFactory() {
        assertNotNull(providerFactoryMocking.getProviderFactory());
    }

    @Test
    void setGradleProperty() {
        ProviderFactory providerFactory = providerFactoryMocking.getProviderFactory();
        Provider<String> propertyA = providerFactory.gradleProperty("propertyA");

        assertFalse(propertyA.isPresent());

        providerFactoryMocking.setGradleProperty("propertyA", "valueA");

        assertTrue(propertyA.isPresent());
        assertEquals("valueA", propertyA.get());

        assertSame(propertyA, providerFactory.gradleProperty("propertyA"));
    }

    @Test
    void setSystemProperty() {
        ProviderFactory providerFactory = providerFactoryMocking.getProviderFactory();
        Provider<String> propertyA = providerFactory.systemProperty("propertyA");

        assertFalse(propertyA.isPresent());

        providerFactoryMocking.setSystemProperty("propertyA", "valueA");

        assertTrue(propertyA.isPresent());
        assertEquals("valueA", propertyA.get());

        assertSame(propertyA, providerFactory.systemProperty("propertyA"));
    }

    @Test
    void setEnvironmentVariable() {
        ProviderFactory providerFactory = providerFactoryMocking.getProviderFactory();
        Provider<String> envA = providerFactory.environmentVariable("envA");

        assertFalse(envA.isPresent());

        providerFactoryMocking.setEnvironmentVariable("envA", "valueA");

        assertTrue(envA.isPresent());
        assertEquals("valueA", envA.get());

        assertSame(envA, providerFactory.environmentVariable("envA"));
    }
}