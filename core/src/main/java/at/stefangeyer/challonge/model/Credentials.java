package at.stefangeyer.challonge.model;

import java.util.Base64;

/**
 * Challonge credentials containing username and api-key.
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public class Credentials {

    private String username;
    private String key;

    public Credentials(String username, String key) {
        this.username = username;
        this.key = key;
    }

    public String toHttpAuthString() {
        String credentials = this.username + ":" + this.key;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}
