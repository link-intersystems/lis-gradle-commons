package com.link_intersystems.gradle.project.builder;

import java.io.IOException;

public interface IOConsumer<T> {
    void accept(T element) throws IOException;
}
