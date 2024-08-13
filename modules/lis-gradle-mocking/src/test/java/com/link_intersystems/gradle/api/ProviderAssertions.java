package com.link_intersystems.gradle.api;

import org.gradle.api.provider.Provider;

import java.util.UUID;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class ProviderAssertions<T> {

    public static ProviderAssertions<String> string(Provider<String> provider) {
        return new ProviderAssertions<>(provider, v -> UUID.randomUUID().toString());
    }

    private Provider<T> provider;
    private final Function<T, T> elseValueGenerator;

    public ProviderAssertions(Provider<T> provider, Function<T, T> elseValueGenerator) {
        this.provider = provider;
        this.elseValueGenerator = elseValueGenerator;
    }

    public void assertValue(T expectedValue) {
        assertValue(provider, expectedValue);
    }

    public void assertValue(Provider<T> provider, T expectedValue) {
        assertTrue(provider.isPresent());
        assertEquals(expectedValue, provider.getOrNull());
        assertEquals(expectedValue, provider.get());

        T elseValue = elseValueGenerator.apply(expectedValue);
        assertEquals(expectedValue, provider.getOrElse(elseValue));
    }


    public void assertEmpty() {
        assertFalse(provider.isPresent());
        assertNull(provider.getOrNull());
        assertThrows(IllegalStateException.class, () -> provider.get());
        T elseValue = elseValueGenerator.apply(null);
        assertEquals(elseValue, provider.getOrElse(elseValue));
    }
}
