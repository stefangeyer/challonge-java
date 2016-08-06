package com.exsoloscript.challonge;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class Challonge {

    private Retrofit retrofit;

    Challonge(String username, String apiKey) {
        String baseUrl = "https://" + username + ":" + apiKey + "@api.challonge.com/v1";

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
