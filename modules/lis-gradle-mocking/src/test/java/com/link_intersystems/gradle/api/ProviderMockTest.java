package com.link_intersystems.gradle.api;

import org.gradle.api.provider.Provider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ProviderMockTest {

    private ProviderMock<String> provider;
    private String value;
    private ProviderAssertions<String> assertions;

    @BeforeEach
    void setUp() {
        provider = new ProviderMock<>();
        provider.setValueSupplier(() -> value);

        assertions = ProviderAssertions.string(provider);
    }

    protected ProviderMock<String> provider() {
        return provider;
    }

    protected ProviderAssertions<String> assertions() {
        return ProviderAssertions.string(provider());
    }

    @AfterEach
    void tearDown() {
        value = null;
    }

    protected void setValue(String value) {
        this.value = value;
    }

    @Test
    void empty() {
        assertions().assertEmpty();

    }

    @Test
    void valueSet() {
        setValue("test");

        assertions().assertValue("test");
    }


    @Test
    void mapEmpty() {

        Provider<String> mappedValue = provider().map(v -> v.substring(0, 2));

        assertFalse(mappedValue.isPresent());
    }

    @Test
    void map() {

        setValue("Hello");

        Provider<String> mappedValue = provider().map(v -> v.substring(0, 2));

        ProviderAssertions.string(mappedValue).assertValue("He");
    }

    @Test
    void flatMap() {

        setValue("Hello");

        Provider<String> flatMapped = provider().flatMap(value -> new ProviderMock<>(() -> value.substring(0, 2)));

        ProviderAssertions.string(flatMapped).assertValue("He");
    }

    @Test
    void filter() {
        setValue("test");

        Provider<String> filtered = provider().filter(v -> v.startsWith("te"));

        ProviderAssertions.string(filtered).assertValue("test");
    }

    @Test
    void filterNoMatch() {
        setValue("test");

        Provider<String> filtered = provider().filter(v -> v.startsWith("st"));

        assertFalse(filtered.isPresent());
    }
}