package com.exsoloscript.challonge.model.query;

import com.exsoloscript.challonge.model.enumerations.RankedBy;
import com.exsoloscript.challonge.model.enumerations.TournamentType;
import com.exsoloscript.challonge.model.enumerations.query.GrandFinalsModifier;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class TournamentQuery {
    private String name;
    @SerializedName("tournament_type")
    private TournamentType tournamentType;
    private String url;
    private String subdomain;
    private String description;
    @SerializedName("open_signup")
    private boolean openSignup;
    @SerializedName("hold_third_place_match")
    private boolean holdThirdPlaceMatch;
    @SerializedName("pts_for_match_win")
    private float ptsForMatchWin;
    @SerializedName("pts_for_match_tie")
    private float ptsForMatchTie;
    @SerializedName("pts_for_game_win")
    private float ptsForGameWin;
    @SerializedName("pts_for_game_tie")
    private float ptsForGameTie;
    @SerializedName("pts_for_bye")
    private float ptsForBye;
    @SerializedName("swiss_rounds")
    private int swissRounds;
    @SerializedName("ranked_by")
    private RankedBy rankedBy;
    @SerializedName("rr_pts_for_game_win")
    private float rrPtsForGameWin;
    @SerializedName("rr_pts_for_game_tie")
    private float rrPtsForGameTie;
    @SerializedName("rr_pts_for_match_win")
    private float rrPtsForMatchWin;
    @SerializedName("rr_pts_for_match_tie")
    private float rrPtsForMatchTie;
    @SerializedName("accept_attachments")
    private boolean acceptAttachments;
    @SerializedName("hide_forum")
    private boolean hideForum;
    @SerializedName("show_rounds")
    private boolean showRounds;
    @SerializedName("private")
    private boolean _private;
    @SerializedName("notify_users_when_the_tournament_ends")
    private boolean notifyUsersWhenTheTournamentEnds;
    @SerializedName("sequential_pairings")
    private boolean sequentialPairings;
    @SerializedName("signup_cap")
    private int signupCap;
    @SerializedName("start_at")
    private Calendar startAt;
    @SerializedName("check_in_duration")
    private int checkInDuration;
    @SerializedName("grand_finals_modifier")
    private GrandFinalsModifier grandFinalsModifier;

    private TournamentQuery(String name, TournamentType tournamentType, String url, String subdomain, String description, boolean openSignup, boolean holdThirdPlaceMatch, float ptsForMatchWin, float ptsForMatchTie, float ptsForGameWin, float ptsForGameTie, float ptsForBye, int swissRounds, RankedBy rankedBy, float rrPtsForGameWin, float rrPtsForGameTie, float rrPtsForMatchWin, float rrPtsForMatchTie, boolean acceptAttachments, boolean hideForum, boolean showRounds, boolean _private, boolean notifyUsersWhenTheTournamentEnds, boolean sequentialPairings, int signupCap, Calendar startAt, int checkInDuration, GrandFinalsModifier grandFinalsModifier) {
        this.name = name;
        this.tournamentType = tournamentType;
        this.url = url;
        this.subdomain = subdomain;
        this.description = description;
        this.openSignup = openSignup;
        this.holdThirdPlaceMatch = holdThirdPlaceMatch;
        this.ptsForMatchWin = ptsForMatchWin;
        this.ptsForMatchTie = ptsForMatchTie;
        this.ptsForGameWin = ptsForGameWin;
        this.ptsForGameTie = ptsForGameTie;
        this.ptsForBye = ptsForBye;
        this.swissRounds = swissRounds;
        this.rankedBy = rankedBy;
        this.rrPtsForGameWin = rrPtsForGameWin;
        this.rrPtsForGameTie = rrPtsForGameTie;
        this.rrPtsForMatchWin = rrPtsForMatchWin;
        this.rrPtsForMatchTie = rrPtsForMatchTie;
        this.acceptAttachments = acceptAttachments;
        this.hideForum = hideForum;
        this.showRounds = showRounds;
        this._private = _private;
        this.notifyUsersWhenTheTournamentEnds = notifyUsersWhenTheTournamentEnds;
        this.sequentialPairings = sequentialPairings;
        this.signupCap = signupCap;
        this.startAt = startAt;
        this.checkInDuration = checkInDuration;
        this.grandFinalsModifier = grandFinalsModifier;
    }

    public String getName() {
        return name;
    }

    public TournamentType getTournamentType() {
        return tournamentType;
    }

    public String getUrl() {
        return url;
    }

    public String getSubdomain() {
        return subdomain;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOpenSignup() {
        return openSignup;
    }

    public boolean isHoldThirdPlaceMatch() {
        return holdThirdPlaceMatch;
    }

    public float getPtsForMatchWin() {
        return ptsForMatchWin;
    }

    public float getPtsForMatchTie() {
        return ptsForMatchTie;
    }

    public float getPtsForGameWin() {
        return ptsForGameWin;
    }

    public float getPtsForGameTie() {
        return ptsForGameTie;
    }

    public float getPtsForBye() {
        return ptsForBye;
    }

    public int getSwissRounds() {
        return swissRounds;
    }

    public RankedBy getRankedBy() {
        return rankedBy;
    }

    public float getRrPtsForGameWin() {
        return rrPtsForGameWin;
    }

    public float getRrPtsForGameTie() {
        return rrPtsForGameTie;
    }

    public float getRrPtsForMatchWin() {
        return rrPtsForMatchWin;
    }

    public float getRrPtsForMatchTie() {
        return rrPtsForMatchTie;
    }

    public boolean isAcceptAttachments() {
        return acceptAttachments;
    }

    public boolean isHideForum() {
        return hideForum;
    }

    public boolean isShowRounds() {
        return showRounds;
    }

    public boolean is_private() {
        return _private;
    }

    public boolean isNotifyUsersWhenTheTournamentEnds() {
        return notifyUsersWhenTheTournamentEnds;
    }

    public boolean isSequentialPairings() {
        return sequentialPairings;
    }

    public int getSignupCap() {
        return signupCap;
    }

    public Calendar getStartAt() {
        return startAt;
    }

    public int getCheckInDuration() {
        return checkInDuration;
    }

    public GrandFinalsModifier getGrandFinalsModifier() {
        return grandFinalsModifier;
    }

    public static class Builder {
        private String name;
        private TournamentType tournamentType;
        private String url;
        private String subdomain;
        private String description;
        private boolean openSignup;
        private boolean holdThirdPlaceMatch;
        private float ptsForMatchWin;
        private float ptsForMatchTie;
        private float ptsForGameWin;
        private float ptsForGameTie;
        private float ptsForBye;
        private int swissRounds;
        private RankedBy rankedBy;
        private float rrPtsForGameWin;
        private float rrPtsForGameTie;
        private float rrPtsForMatchWin;
        private float rrPtsForMatchTie;
        private boolean acceptAttachments;
        private boolean hideForum;
        private boolean showRounds;
        private boolean aPrivate;
        private boolean notifyUsersWhenTheTournamentEnds;
        private boolean sequentialPairings;
        private int signupCap;
        private Calendar startAt;
        private int checkInDuration;
        private GrandFinalsModifier grandFinalsModifier;

        public Builder() {}

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setTournamentType(TournamentType tournamentType) {
            this.tournamentType = tournamentType;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setSubdomain(String subdomain) {
            this.subdomain = subdomain;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setOpenSignup(boolean openSignup) {
            this.openSignup = openSignup;
            return this;
        }

        public Builder setHoldThirdPlaceMatch(boolean holdThirdPlaceMatch) {
            this.holdThirdPlaceMatch = holdThirdPlaceMatch;
            return this;
        }

        public Builder setPtsForMatchWin(float ptsForMatchWin) {
            this.ptsForMatchWin = ptsForMatchWin;
            return this;
        }

        public Builder setPtsForMatchTie(float ptsForMatchTie) {
            this.ptsForMatchTie = ptsForMatchTie;
            return this;
        }

        public Builder setPtsForGameWin(float ptsForGameWin) {
            this.ptsForGameWin = ptsForGameWin;
            return this;
        }

        public Builder setPtsForGameTie(float ptsForGameTie) {
            this.ptsForGameTie = ptsForGameTie;
            return this;
        }

        public Builder setPtsForBye(float ptsForBye) {
            this.ptsForBye = ptsForBye;
            return this;
        }

        public Builder setSwissRounds(int swissRounds) {
            this.swissRounds = swissRounds;
            return this;
        }

        public Builder setRankedBy(RankedBy rankedBy) {
            this.rankedBy = rankedBy;
            return this;
        }

        public Builder setRrPtsForGameWin(float rrPtsForGameWin) {
            this.rrPtsForGameWin = rrPtsForGameWin;
            return this;
        }

        public Builder setRrPtsForGameTie(float rrPtsForGameTie) {
            this.rrPtsForGameTie = rrPtsForGameTie;
            return this;
        }

        public Builder setRrPtsForMatchWin(float rrPtsForMatchWin) {
            this.rrPtsForMatchWin = rrPtsForMatchWin;
            return this;
        }

        public Builder setRrPtsForMatchTie(float rrPtsForMatchTie) {
            this.rrPtsForMatchTie = rrPtsForMatchTie;
            return this;
        }

        public Builder setAcceptAttachments(boolean acceptAttachments) {
            this.acceptAttachments = acceptAttachments;
            return this;
        }

        public Builder setHideForum(boolean hideForum) {
            this.hideForum = hideForum;
            return this;
        }

        public Builder setShowRounds(boolean showRounds) {
            this.showRounds = showRounds;
            return this;
        }

        public Builder setPrivate(boolean _private) {
            this.aPrivate = _private;
            return this;
        }

        public Builder setNotifyUsersWhenTheTournamentEnds(boolean notifyUsersWhenTheTournamentEnds) {
            this.notifyUsersWhenTheTournamentEnds = notifyUsersWhenTheTournamentEnds;
            return this;
        }

        public Builder setSequentialPairings(boolean sequentialPairings) {
            this.sequentialPairings = sequentialPairings;
            return this;
        }

        public Builder setSignupCap(int signupCap) {
            this.signupCap = signupCap;
            return this;
        }

        public Builder setStartAt(Calendar startAt) {
            this.startAt = startAt;
            return this;
        }

        public Builder setCheckInDuration(int checkInDuration) {
            this.checkInDuration = checkInDuration;
            return this;
        }

        public Builder setGrandFinalsModifier(GrandFinalsModifier grandFinalsModifier) {
            this.grandFinalsModifier = grandFinalsModifier;
            return this;
        }

        public TournamentQuery build() {
            return new TournamentQuery(name, tournamentType, url, subdomain, description, openSignup, holdThirdPlaceMatch, ptsForMatchWin, ptsForMatchTie, ptsForGameWin, ptsForGameTie, ptsForBye, swissRounds, rankedBy, rrPtsForGameWin, rrPtsForGameTie, rrPtsForMatchWin, rrPtsForMatchTie, acceptAttachments, hideForum, showRounds, aPrivate, notifyUsersWhenTheTournamentEnds, sequentialPairings, signupCap, startAt, checkInDuration, grandFinalsModifier);
        }
    }
}
