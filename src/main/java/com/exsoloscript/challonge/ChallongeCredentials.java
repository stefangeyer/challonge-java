package com.exsoloscript.challonge;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;

/**
 * Challonge credentials containing username and api-key.
 *
 * @author EXSolo
 * @version 20160822.1
 */
@Data
@AllArgsConstructor
public class ChallongeCredentials {

    private String username;
    private String apiKey;

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
