package com.exsoloscript.challonge.handler.error;

import com.exsoloscript.challonge.model.error.ApiError;

public interface ErrorHandlingStrategy {

    void handleError(ApiError error);
}
