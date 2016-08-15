package com.exsoloscript.challonge.handler.error;

import com.exsoloscript.challonge.model.exception.ApiError;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.lang3.Validate;

@Singleton
public class ErrorHandler {

    private ErrorHandlingStrategy strategy;

    public void handleError(ApiError error) {
        Validate.notNull(strategy, "No exception handler was set, can't handle errors.");
        strategy.handleError(error);
    }

    @Inject
    public void setStrategy(ErrorHandlingStrategy strategy) {
        this.strategy = strategy;
    }
}
