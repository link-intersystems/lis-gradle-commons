package com.link_intersystems.gradle.api;

import org.gradle.api.provider.Property;
import org.gradle.api.provider.Provider;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class PropertyMock<T> extends ProviderMock<T> implements Property<T> {

    private class ValueSupplier implements Supplier<T> {

        private T value;
        private Provider<? extends T> provider;

        public void setValue(T value) {
            this.value = value;
        }

        public void setProvider(Provider<? extends T> provider) {
            this.provider = provider;
        }

        @Override
        public T get() {
            if (provider != null && provider.isPresent()) {
                return provider.get();
            }
            return value;
        }
    }

    private ValueSupplier valueSupplier = new ValueSupplier();

    private PropertyMock<T> conventionValueProvider;

    public PropertyMock() {
        setValueSupplier(valueSupplier);
    }

    @Override
    public void set(@Nullable T value) {
        valueSupplier.setValue(value);
    }

    @Override
    public void set(Provider<? extends T> provider) {
        valueSupplier.setProvider(provider);
    }

    @Override
    public Property<T> value(@Nullable T value) {
        set(value);
        return this;
    }

    @Override
    public Property<T> value(Provider<? extends T> provider) {
        set(provider);
        return this;
    }

    @Override
    public Property<T> unset() {
        return value((T) null);
    }

    public PropertyMock<T> getConventionValueProvider() {
        if (conventionValueProvider == null) {
            conventionValueProvider = new PropertyMock<>();
        }
        return conventionValueProvider;
    }

    @Override
    public Property<T> convention(@Nullable T value) {
        getConventionValueProvider().set(value);
        return this;
    }

    @Override
    public Property<T> convention(Provider<? extends T> provider) {
        getConventionValueProvider().set(provider);
        return this;
    }

    @Override
    public Property<T> unsetConvention() {
        return convention((T) null);
    }

    @Override
    public void finalizeValue() {
        throw new UnsupportedOperationException("Mock does not support finalizeValue");
    }

    @Override
    public void finalizeValueOnRead() {
        throw new UnsupportedOperationException("Mock does not support finalizeValueOnRead");
    }

    @Override
    public void disallowChanges() {
        throw new UnsupportedOperationException("Mock does not support disallowChanges");

    }

    @Override
    public void disallowUnsafeRead() {
        throw new UnsupportedOperationException("Mock does not support disallowUnsafeRead");
    }

    @Override
    public T get() {
        T orNull = getOrNull();
        if (orNull == null) {
            throw new IllegalStateException("there is no value present");
        }
        return orNull;
    }

    @Nullable
    @Override
    public T getOrNull() {
        T value = super.getOrNull();
        if (value == null && conventionValueProvider != null) {
            value = conventionValueProvider.getOrNull();
        }
        return value;
    }

}
