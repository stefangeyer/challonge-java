package com.exsoloscript.challonge.handler.error;

import com.exsoloscript.challonge.model.error.ApiError;

public class PrintErrorHandlingStrategy implements ErrorHandlingStrategy {

    @Override
    public void handleError(ApiError error) {
        System.out.println(error);
    }
}
