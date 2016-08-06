package com.exsoloscript.challonge;

public class ChallongeApi {

    public static Challonge getFor(String username, String apiKey) {
        return new Challonge(username, apiKey);
    }
}
