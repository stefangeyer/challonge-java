package com.exsoloscript.challonge;

import lombok.Data;
import org.apache.commons.codec.binary.Base64;

/**
 * Challonge credentials containing username and api-key.
 *
 * @author EXSolo
 * @version 20160822.1
 */
@Data
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
}
