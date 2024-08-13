package com.link_intersystems.gradle.api.provider;

import org.gradle.api.provider.Property;
import org.gradle.api.provider.ProviderFactory;
import org.jetbrains.annotations.NotNull;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProviderFactoryMocking {

    private static class PropertyAnswer implements Answer<Property<String>> {

        private Map<String, PropertyMock<String>> properties = new HashMap<>();


        @Override
        public Property<String> answer(InvocationOnMock invocationOnMock) throws Throwable {
            String propertyName = invocationOnMock.getArgument(0, String.class);
            return getProperty(propertyName);
        }

        private @NotNull PropertyMock<String> getProperty(String propertyName) {
            return properties.computeIfAbsent(propertyName, name -> new PropertyMock<>());
        }

        public void setProperty(String name, String value) {
            getProperty(name).set(value);
        }
    }

    private PropertyAnswer gradlePropertyAnswer = new PropertyAnswer();
    private PropertyAnswer environmentVariableAnswer = new PropertyAnswer();
    private PropertyAnswer systemPropertyAnswer = new PropertyAnswer();
    private ProviderFactory providerFactory;

    @SuppressWarnings("unchecked")
    public ProviderFactoryMocking() {
        this.providerFactory = Mockito.mock(ProviderFactory.class);


        when(providerFactory.gradleProperty(anyString())).thenAnswer(gradlePropertyAnswer);
        when(providerFactory.environmentVariable(anyString())).thenAnswer(environmentVariableAnswer);
        when(providerFactory.systemProperty(anyString())).thenAnswer(systemPropertyAnswer);
    }

    public ProviderFactory getProviderFactory() {
        return providerFactory;
    }

    @SuppressWarnings("unchecked")
    public void setGradleProperty(String name, String value) {
        gradlePropertyAnswer.setProperty(name, value);
    }

    @SuppressWarnings("unchecked")
    public void setSystemProperty(String name, String value) {
        systemPropertyAnswer.setProperty(name, value);
    }

    @SuppressWarnings("unchecked")
    public void setEnvironmentVariable(String name, String value) {
        environmentVariableAnswer.setProperty(name, value);
    }
}
