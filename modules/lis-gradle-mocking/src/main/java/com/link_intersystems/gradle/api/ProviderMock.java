package com.link_intersystems.gradle.api;

import org.gradle.api.Transformer;
import org.gradle.api.provider.Provider;
import org.gradle.api.specs.Spec;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ProviderMock<T> implements Provider<T> {

    private Supplier<T> valueSupplier = () -> null;

    public ProviderMock() {
    }

    ProviderMock(Supplier<T> valueSupplier) {
        this.valueSupplier = valueSupplier;
    }

    protected void setValueSupplier(Supplier<T> valueSupplier) {
        this.valueSupplier = valueSupplier;
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
        return valueSupplier.get();
    }

    @Override
    public T getOrElse(T defaultValue) {
        T orNull = getOrNull();
        if (orNull == null) {
            return defaultValue;
        }
        return orNull;
    }

    @Override
    public <S> Provider<S> map(Transformer<? extends S, ? super T> transformer) {
        return new ProviderMock<>(() -> {
            T orNull = getOrNull();
            if (orNull != null) {
                return transformer.transform(orNull);
            }
            return null;
        });
    }

    @Override
    public Provider<T> filter(Spec<? super T> spec) {
        return new ProviderMock<>(() -> {
            T orNull = getOrNull();
            if (spec.isSatisfiedBy(orNull)) {
                return orNull;
            }
            return null;
        });
    }

    @Override
    public <S> Provider<S> flatMap(Transformer<? extends @Nullable Provider<? extends S>, ? super T> transformer) {
        return new ProviderMock<S>(() -> {
            Provider<? extends S> transformed = transformer.transform(getOrNull());
            return transformed.getOrNull();
        });
    }

    @Override
    public boolean isPresent() {
        return getOrNull() != null;
    }

    @Override
    public Provider<T> orElse(T value) {
        return new ProviderMock<>(() -> getOrElse(value));
    }

    @Override
    public Provider<T> orElse(Provider<? extends T> provider) {
        return new ProviderMock<>(() -> {
            T orNull = getOrNull();
            if (orNull == null) {
                return provider.getOrNull();
            }
            return orNull;
        });
    }

    @Override
    public Provider<T> forUseAtConfigurationTime() {
        return this;
    }

    @Override
    public <U, R> Provider<R> zip(Provider<U> right, BiFunction<? super T, ? super U, ? extends R> combiner) {

        return new ProviderMock<>(() -> {
            T thisValue = get();
            U otherValue = right.get();
            return combiner.apply(thisValue, otherValue);
        });
    }
}
