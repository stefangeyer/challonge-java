package com.exsoloscript.challonge.guice;

import com.exsoloscript.challonge.ChallongeApi;
import com.exsoloscript.challonge.ChallongeTestSuite;
import com.google.inject.AbstractModule;

public class ChallongeTestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ChallongeApi.class).toProvider(ChallongeTestSuite.class);
    }
}
