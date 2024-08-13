package com.link_intersystems.gradle.api.provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertyMockTest extends ProviderMockTest {

    private PropertyMock<String> propertyMock;

    @BeforeEach
    void setUp() {
        propertyMock = new PropertyMock<>();
    }

    @Override
    protected ProviderMock<String> provider() {
        return propertyMock;
    }

    @Override
    protected void setValue(String value) {
        propertyMock.set(value);
    }

    @Test
    void conventionSetButNoValue(){
        propertyMock.convention("convention");

        assertTrue(propertyMock.isPresent());
        assertEquals("convention", propertyMock.getOrNull());
        assertEquals("convention", propertyMock.get());
    }

    @Test
    void valueOverridesConvention(){
        propertyMock.set("value");
        propertyMock.convention("convention");

        assertTrue(propertyMock.isPresent());
        assertEquals("value", propertyMock.getOrNull());
        assertEquals("value", propertyMock.get());
    }

    @Test
    void conventionWinsIfValueIsSetToNull(){
        propertyMock.set("value");
        propertyMock.convention("convention");
        propertyMock.set((String) null);

        assertTrue(propertyMock.isPresent());
        assertEquals("convention", propertyMock.getOrNull());
        assertEquals("convention", propertyMock.get());
    }


}