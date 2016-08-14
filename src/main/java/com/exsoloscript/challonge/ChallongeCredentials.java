package com.exsoloscript.challonge;

import org.apache.commons.codec.binary.Base64;

public class ChallongeCredentials {

    private String username;
    private String apiKey;

    public ChallongeCredentials(String username, String apiKey) {
        this.username = username;
        this.apiKey = apiKey;
    }

    public String toHttpAuthString() {
        String credentials = this.username + ":" + this.apiKey;
        return "Basic " + Base64.encodeBase64String(credentials.getBytes());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
