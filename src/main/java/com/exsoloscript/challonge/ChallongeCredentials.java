package com.exsoloscript.challonge;

import org.apache.commons.codec.binary.Base64;

/**
 * Challonge credentials containing username and api-key.
 *
 * @author EXSolo
 * @version 20160822.1
 */
public class ChallongeCredentials {

    private String username;
    private String apiKey;

    public ChallongeCredentials(String username, String apiKey) {
        this.username = username;
        this.apiKey = apiKey;
    }

    /**
     * Creates a HTTP basic auth String from the given credentials
     *
     * @return HTTP basic auth String
     */
    public String toHttpAuthString() {
        String credentials = this.username + ":" + this.apiKey;
        return "Basic " + Base64.encodeBase64String(credentials.getBytes());
    }

    public String username() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String apiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
