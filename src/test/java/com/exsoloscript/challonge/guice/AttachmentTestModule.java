package com.exsoloscript.challonge.guice;

import com.google.common.io.Resources;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.File;
import java.net.URISyntaxException;

public class AttachmentTestModule extends AbstractModule {
    @Override
    protected void configure() {
        try {
            bind(File.class).annotatedWith(Names.named("attachment")).toInstance(new File(Resources.getResource("testfile1.txt").toURI()));
        } catch (URISyntaxException ignored) {
        }
    }
}
