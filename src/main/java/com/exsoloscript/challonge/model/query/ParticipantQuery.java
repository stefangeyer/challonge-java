package com.exsoloscript.challonge.model.query;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.Validate;

/**
 * Query for creating or updating a participant. This class can be accessed using it's builder.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class ParticipantQuery {

    private String name;

    private String email;

    @SerializedName("challonge_username")
    private String challongeUsername;

    private Integer seed;

    private String misc;

    @SerializedName("invite_name_or_email")
    private String inviteNameOrEmail;

    private ParticipantQuery(String name, String email, String challongeUsername, Integer seed, String misc, String inviteNameOrEmail) {
        this.name = name;
        this.email = email;
        this.challongeUsername = challongeUsername;
        this.seed = seed;
        this.misc = misc;
        this.inviteNameOrEmail = inviteNameOrEmail;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The name displayed in the bracket/schedule -
     * not required if email or challonge_username is provided.
     * Must be unique per tournament.
     */
    public String name() {
        return name;
    }

    /**
     * SINGLE ADD / UPDATE ONLY -- Seems to broken -> API is ignoring this argument
     * <p>
     * Providing this will first search for a matching Challonge account.
     * If one is found, this will have the same effect as the "challonge_username" attribute.
     * If one is not found, the "new-user-email" attribute will be set,
     * and the user will be invited via email to create an account.
     */
    public String email() {
        return email;
    }

    /**
     * SINGLE ADD / UPDATE ONLY  -- Seems to broken -> API is ignoring this argument
     * <p>
     * Provide this if the participant has a Challonge account.
     * He or she will be invited to the tournament.
     */
    public String challongeUsername() {
        return challongeUsername;
    }

    /**
     * The participant's new seed.
     * Must be between 1 and the current number of participants (including the new record).
     * Overwriting an existing seed will automatically bump other participants as you
     * would expect.
     */
    public Integer seed() {
        return seed;
    }

    /**
     * Max: 255 characters.
     * Multi-purpose field that is only visible via the API and handy for site integration
     * (e.g. key to your users table)
     */
    public String misc() {
        return misc;
    }

    /**
     * BULK ADD ONLY
     * <p>
     * Username can be provided if the participant has a Challonge account.
     * Providing email will first search for a matching Challonge account.
     * If one is found, the user will be invited. If not, the "new-user-email"
     * attribute will be set, and the user will be invited via email to create an account.
     */
    public String inviteNameOrEmail() {
        return inviteNameOrEmail;
    }

    public static class Builder {
        private String name;
        private String email;
        private String challongeUsername;
        private Integer seed;
        private String misc;
        private String inviteNameOrEmail;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setChallongeUsername(String challongeUsername) {
            this.challongeUsername = challongeUsername;
            return this;
        }

        public Builder setSeed(Integer seed) {
            this.seed = seed;
            return this;
        }

        public Builder setMisc(String misc) {
            this.misc = misc;
            return this;
        }

        public Builder setInviteNameOrEmail(String inviteNameOrEmail) {
            this.inviteNameOrEmail = inviteNameOrEmail;
            return this;
        }

        public ParticipantQuery build() {
            if (misc != null)
                Validate.isTrue(misc.length() <= 255, "Misc string can only contain 255 characters");
            return new ParticipantQuery(name, email, challongeUsername, seed, misc, inviteNameOrEmail);
        }
    }
}
