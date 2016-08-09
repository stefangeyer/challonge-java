package com.exsoloscript.challonge;

public class Challonge {

    public static ChallongeApi getFor(String username, String apiKey) {
        return new ChallongeApi(username, apiKey);
    }
}
