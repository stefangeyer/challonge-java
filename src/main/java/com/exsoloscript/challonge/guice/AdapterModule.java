package com.exsoloscript.challonge.guice;

import com.exsoloscript.challonge.gson.OffsetDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.time.OffsetDateTime;

public class AdapterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Gson.class)
                .annotatedWith(Names.named("offsetdatetime"))
                .toInstance(
                        new GsonBuilder()
                                .serializeNulls()
                                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                                .create()
                );
    }
}
