package com.exsoloscript.challonge.model.query;

import com.google.gson.annotations.SerializedName;

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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getChallongeUsername() {
        return challongeUsername;
    }

    public Integer getSeed() {
        return seed;
    }

    public String getMisc() {
        return misc;
    }

    public String getInviteNameOrEmail() {
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
            return new ParticipantQuery(name, email, challongeUsername, seed, misc, inviteNameOrEmail);
        }
    }
}
