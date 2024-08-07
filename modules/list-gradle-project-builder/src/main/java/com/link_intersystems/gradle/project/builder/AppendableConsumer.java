package com.link_intersystems.gradle.project.builder;

import java.io.IOException;

public interface AppendableConsumer {
    void accept(Appendable appendable) throws IOException;
}
