package com.exsoloscript.challonge.util;

import com.exsoloscript.challonge.model.exception.ChallongeException;
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

    public void parseException(Response<?> response) throws IOException, ChallongeException {
        Converter<ResponseBody, ChallongeException> converter = this.retrofit.responseBodyConverter(ChallongeException.class, new Annotation[0]);
        throw converter.convert(response.errorBody());
    }

}
