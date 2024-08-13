package com.link_intersystems.gradle.api;

import org.gradle.api.plugins.ExtensionContainer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExtensionContainerMocking {

    public static interface OnCreate<T> {

        void returnValue(T value);
    }

    private ExtensionContainer extensionContainer;
    private ProviderFactoryMocking providerFactoryMocking;

    public ExtensionContainerMocking() {

        this.extensionContainer = mock(ExtensionContainer.class);
    }

    public ExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    @SuppressWarnings("unchecked")
    public <T, I extends T> OnCreate<I> onCreate(String extensionName, Class<T> type) {
        return value -> {
            Class<I> instanceType = (Class<I>) value.getClass();
            when(extensionContainer.create(type, extensionName, instanceType)).thenReturn(value);
        };
    }
}
