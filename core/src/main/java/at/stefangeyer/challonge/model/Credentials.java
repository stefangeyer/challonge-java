package at.stefangeyer.challonge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Base64;

/**
 * Challonge credentials containing username and api-key.
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Credentials {

    private String username;
    private String key;

    public String toHttpAuthString() {
        String credentials = this.username + ":" + this.key;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}
