package com.link_intersystems.gradle.api.plugins;

import org.gradle.api.plugins.ExtensionContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExtensionContainerMockingTest {

    private ExtensionContainerMocking extensionContainerMocking;

    @BeforeEach
    void setUp() {
        extensionContainerMocking = new ExtensionContainerMocking();
    }

    @Test
    void getExtensionContainer() {
        assertNotNull(extensionContainerMocking.getExtensionContainer());
    }

    @Test
    void onCreate() {
        ArrayList<String> arrayList = new ArrayList<>();
        extensionContainerMocking.onCreate("list", List.class).returnValue(arrayList);

        ExtensionContainer extensionContainer = extensionContainerMocking.getExtensionContainer();
        List list = extensionContainer.create(List.class, "list", ArrayList.class);

        assertEquals(arrayList, list);
    }
}