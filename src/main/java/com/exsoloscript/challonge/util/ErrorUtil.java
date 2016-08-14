package com.exsoloscript.challonge.util;

import com.exsoloscript.challonge.ChallongeApi;
import com.exsoloscript.challonge.model.error.ApiError;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;

@Singleton
public class ErrorUtil {

    private Retrofit retrofit;

    @Inject
    public ErrorUtil(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public ApiError parseError(Response<?> response) {
        Converter<ResponseBody, ApiError> converter = this.retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return error;
    }

}
