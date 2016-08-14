package com.exsoloscript.challonge.handler.error;

import com.exsoloscript.challonge.model.error.ApiError;
import org.apache.commons.lang3.Validate;

public class ErrorHandler {

    private static ErrorHandlingStrategy strategy;

    public static void handleError(ApiError error) {
        Validate.notNull(strategy, "No error handler was set, can't handle errors.");
        strategy.handleError(error);
    }

    public static void setStrategy(ErrorHandlingStrategy strategy) {
        ErrorHandler.strategy = strategy;
    }
}
