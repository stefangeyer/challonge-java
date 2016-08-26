package com.exsoloscript.challonge.model.query;

import com.exsoloscript.challonge.model.enumeration.RankedBy;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.enumeration.query.GrandFinalsModifier;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.Validate;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Query for creating or updating a tournament. This class can be accessed using it's builder.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class TournamentQuery {

    private String name;

    @SerializedName("tournament_type")
    private TournamentType tournamentType;

    private String url;

    private String subdomain;

    private String description;

    @SerializedName("open_signup")
    private Boolean openSignup;

    @SerializedName("hold_third_place_match")
    private Boolean holdThirdPlaceMatch;

    @SerializedName("pts_for_match_win")
    private Float pointsForMatchWin;

    @SerializedName("pts_for_match_tie")
    private Float pointsForMatchTie;

    @SerializedName("pts_for_game_win")
    private Float pointsForGameWin;

    @SerializedName("pts_for_game_tie")
    private Float pointsForGameTie;

    @SerializedName("pts_for_bye")
    private Float pointsForBye;

    @SerializedName("swiss_rounds")
    private Integer swissRounds;

    @SerializedName("ranked_by")
    private RankedBy rankedBy;

    @SerializedName("rr_pts_for_game_win")
    private Float roundRobinPointsForGameWin;

    @SerializedName("rr_pts_for_game_tie")
    private Float roundRobinPointsForGameTie;

    @SerializedName("rr_pts_for_match_win")
    private Float roundRobinPointsForMatchWin;

    @SerializedName("rr_pts_for_match_tie")
    private Float roundRobinPointsForMatchTie;

    @SerializedName("accept_attachments")
    private Boolean acceptAttachments;

    @SerializedName("hide_forum")
    private Boolean hideForum;

    @SerializedName("show_rounds")
    private Boolean showRounds;

    @SerializedName("private")
    private Boolean _private;

    @SerializedName("notify_users_when_matches_open")
    private Boolean notifyUsersWhenMatchesOpen;

    @SerializedName("notify_users_when_the_tournament_ends")
    private Boolean notifyUsersWhenTheTournamentEnds;

    @SerializedName("sequential_pairings")
    private Boolean sequentialPairings;

    @SerializedName("signup_cap")
    private Integer signupCap;

    @SerializedName("start_at")
    private OffsetDateTime startAt;

    @SerializedName("check_in_duration")
    private Integer checkInDuration;

    @SerializedName("grand_finals_modifier")
    private GrandFinalsModifier grandFinalsModifier;

    @SerializedName("tie_breaks")
    private List<String> tieBreaks;

    private TournamentQuery(String name, TournamentType tournamentType, String url, String subdomain, String description, Boolean openSignup, Boolean holdThirdPlaceMatch, Float pointsForMatchWin, Float pointsForMatchTie, Float pointsForGameWin, Float pointsForGameTie, Float pointsForBye, Integer swissRounds, RankedBy rankedBy, Float roundRobinPointsForGameWin, Float roundRobinPointsForGameTie, Float roundRobinPointsForMatchWin, Float roundRobinPointsForMatchTie, Boolean acceptAttachments, Boolean hideForum, Boolean showRounds, Boolean _private, Boolean notifyUsersWhenMatchesOpen, Boolean notifyUsersWhenTheTournamentEnds, Boolean sequentialPairings, Integer signupCap, OffsetDateTime startAt, Integer checkInDuration, GrandFinalsModifier grandFinalsModifier) {
        this.name = name;
        this.tournamentType = tournamentType;
        this.url = url;
        this.subdomain = subdomain;
        this.description = description;
        this.openSignup = openSignup;
        this.holdThirdPlaceMatch = holdThirdPlaceMatch;
        this.pointsForMatchWin = pointsForMatchWin;
        this.pointsForMatchTie = pointsForMatchTie;
        this.pointsForGameWin = pointsForGameWin;
        this.pointsForGameTie = pointsForGameTie;
        this.pointsForBye = pointsForBye;
        this.swissRounds = swissRounds;
        this.rankedBy = rankedBy;
        this.roundRobinPointsForGameWin = roundRobinPointsForGameWin;
        this.roundRobinPointsForGameTie = roundRobinPointsForGameTie;
        this.roundRobinPointsForMatchWin = roundRobinPointsForMatchWin;
        this.roundRobinPointsForMatchTie = roundRobinPointsForMatchTie;
        this.acceptAttachments = acceptAttachments;
        this.hideForum = hideForum;
        this.showRounds = showRounds;
        this._private = _private;
        this.notifyUsersWhenMatchesOpen = notifyUsersWhenMatchesOpen;
        this.notifyUsersWhenTheTournamentEnds = notifyUsersWhenTheTournamentEnds;
        this.sequentialPairings = sequentialPairings;
        this.signupCap = signupCap;
        this.startAt = startAt;
        this.checkInDuration = checkInDuration;
        this.grandFinalsModifier = grandFinalsModifier;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your event's name/title (Max: 60 characters)
     */
    public String name() {
        return name;
    }

    /**
     * Single elimination (default), double elimination, round robin, swiss
     */
    public TournamentType tournamentType() {
        return tournamentType;
    }

    /**
     * challonge.com/url (letters, numbers, and underscores only)
     */
    public String url() {
        return url;
    }

    /**
     * subdomain.challonge.com/url (Requires write access to the specified subdomain)
     */
    public String subdomain() {
        return subdomain;
    }

    /**
     * Description/instructions to be displayed above the bracket
     */
    public String description() {
        return description;
    }

    /**
     * Have Challonge host a sign-up page (otherwise, you manually add all participants)
     */
    public Boolean openSignup() {
        return openSignup;
    }

    /**
     * Single Elimination only. Include a match between semifinal losers? (default: false)
     */
    public Boolean holdThirdPlaceMatch() {
        return holdThirdPlaceMatch;
    }

    /**
     * Decimal (to the nearest tenth) - Swiss only - default: 1.0
     */
    public Float pointsForMatchWin() {
        return pointsForMatchWin;
    }

    /**
     * Decimal (to the nearest tenth) - Swiss only - default: 0.5
     */
    public Float pointsForMatchTie() {
        return pointsForMatchTie;
    }

    /**
     * Decimal (to the nearest tenth) - Swiss only - default: 0.0
     */
    public Float pointsForGameWin() {
        return pointsForGameWin;
    }

    /**
     * Decimal (to the nearest tenth) - Swiss only - default: 0.0
     */
    public Float pointsForGameTie() {
        return pointsForGameTie;
    }

    /**
     * Decimal (to the nearest tenth) - Swiss only - default: 1.0
     */
    public Float pointsForBye() {
        return pointsForBye;
    }

    /**
     * Swiss only -
     * We recommend limiting the number of rounds to less than two-thirds
     * the number of players. Otherwise, an impossible pairing situation
     * can be reached and your tournament may end before the desired number
     * of rounds are played.
     */
    public Integer swissRounds() {
        return swissRounds;
    }

    /**
     * One of the following: 'match wins', 'game wins', 'points scored', 'points difference', 'custom'
     *
     * For more information see http://feedback.challonge.com/knowledgebase/articles/448540-rank-tie-break-statistics
     */
    public RankedBy rankedBy() {
        return rankedBy;
    }

    /**
     * Rounded to the nearest tenth - Round Robin "custom" only - default: 1.0
     */
    public Float roundRobinPointsForGameWin() {
        return roundRobinPointsForGameWin;
    }

    /**
     * Rounded to the nearest tenth - Round Robin "custom" only - default: 0.5
     */
    public Float roundRobinPointsForGameTie() {
        return roundRobinPointsForGameTie;
    }

    /**
     * Rounded to the nearest tenth - Round Robin "custom" only - default: 0.0
     */
    public Float roundRobinPointsForMatchWin() {
        return roundRobinPointsForMatchWin;
    }

    /**
     * Rounded to the nearest tenth - Round Robin "custom" only - default: 0.0
     */
    public Float roundRobinPointsForMatchTie() {
        return roundRobinPointsForMatchTie;
    }

    /**
     * Allow match attachment uploads (default: false)
     */
    public Boolean acceptAttachments() {
        return acceptAttachments;
    }

    /**
     * Hide the forum tab on your Challonge page (default: false)
     */
    public Boolean hideForum() {
        return hideForum;
    }

    /**
     * Single and Double Elimination only - Label each round above the bracket (default: false)
     */
    public Boolean showRounds() {
        return showRounds;
    }

    /**
     * Hide this tournament from the public browsable index and your profile (default: false)
     */
    public Boolean isPrivate() {
        return _private;
    }

    /**
     * Email registered Challonge participants when matches open up for them (default: false)
     */
    public Boolean notifyUsersWhenMatchesOpen() {
        return notifyUsersWhenMatchesOpen;
    }

    /**
     * Email registered Challonge participants the results when this tournament ends (default: false)
     */
    public Boolean notifyUsersWhenTheTournamentEnds() {
        return notifyUsersWhenTheTournamentEnds;
    }

    /**
     * Instead of traditional seeding rules, make pairings by going straight down the list of participants.
     * First round matches are filled in top to bottom, then qualifying matches (if applicable). (default: false)
     */
    public Boolean sequentialPairings() {
        return sequentialPairings;
    }

    /**
     * Maximum number of participants in the bracket.
     * A waiting list (attribute on Participant) will capture participants once the cap is reached.
     */
    public Integer signupCap() {
        return signupCap;
    }

    /**
     * The planned or anticipated start time for the tournament
     * (Used with check_in_duration to determine participant check-in window).
     * Timezone defaults to Eastern.
     */
    public OffsetDateTime startAt() {
        return startAt;
    }

    /**
     * Length of the participant check-in window in minutes.
     * Check-in must be an interval of 5 (0, 5, 10...)
     */
    public Integer checkInDuration() {
        return checkInDuration;
    }

    /**
     * This option only affects double elimination.
     * <p>
     * null/blank (default) - give the winners bracket finalist two chances to beat the losers bracket finalist<br>
     * 'single match' - create only one grand finals match<br>
     * 'skip' - don't create a finals match between winners and losers bracket finalists
     * </p>
     */
    public GrandFinalsModifier grandFinalsModifier() {
        return grandFinalsModifier;
    }

    public static class Builder {
        private String name;
        private TournamentType tournamentType;
        private String url;
        private String subdomain;
        private String description;
        private Boolean openSignup;
        private Boolean holdThirdPlaceMatch;
        private Float pointsForMatchWin;
        private Float pointsForMatchTie;
        private Float pointsForGameWin;
        private Float pointsForGameTie;
        private Float pointsForBye;
        private Integer swissRounds;
        private RankedBy rankedBy;
        private Float roundRobinPointsForGameWin;
        private Float roundRobinPointsForGameTie;
        private Float roundRobinPointsForMatchWin;
        private Float roundRobinPointsForMatchTie;
        private Boolean acceptAttachments;
        private Boolean hideForum;
        private Boolean showRounds;
        private Boolean aPrivate;
        private Boolean notifyUsersWhenMatchesOpen;
        private Boolean notifyUsersWhenTheTournamentEnds;
        private Boolean sequentialPairings;
        private Integer signupCap;
        private OffsetDateTime startAt;
        private Integer checkInDuration;
        private GrandFinalsModifier grandFinalsModifier;
        private Boolean noName = false;
        private Boolean noUrl = false;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder noName() {
            this.noName = true;
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

        public Builder noUrl() {
            this.noUrl = true;
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

        public Builder setOpenSignup(Boolean openSignup) {
            this.openSignup = openSignup;
            return this;
        }

        public Builder setHoldThirdPlaceMatch(Boolean holdThirdPlaceMatch) {
            this.holdThirdPlaceMatch = holdThirdPlaceMatch;
            return this;
        }

        public Builder setPointsForMatchWin(Float pointsForMatchWin) {
            this.pointsForMatchWin = pointsForMatchWin;
            return this;
        }

        public Builder setPointsForMatchTie(Float pointsForMatchTie) {
            this.pointsForMatchTie = pointsForMatchTie;
            return this;
        }

        public Builder setPointsForGameWin(Float pointsForGameWin) {
            this.pointsForGameWin = pointsForGameWin;
            return this;
        }

        public Builder setPointsForGameTie(Float pointsForGameTie) {
            this.pointsForGameTie = pointsForGameTie;
            return this;
        }

        public Builder setPointsForBye(Float pointsForBye) {
            this.pointsForBye = pointsForBye;
            return this;
        }

        public Builder setSwissRounds(Integer swissRounds) {
            this.swissRounds = swissRounds;
            return this;
        }

        public Builder setRankedBy(RankedBy rankedBy) {
            this.rankedBy = rankedBy;
            return this;
        }

        public Builder setRoundRobinPointsForGameWin(Float roundRobinPointsForGameWin) {
            this.roundRobinPointsForGameWin = roundRobinPointsForGameWin;
            return this;
        }

        public Builder setRoundRobinPointsForGameTie(Float roundRobinPointsForGameTie) {
            this.roundRobinPointsForGameTie = roundRobinPointsForGameTie;
            return this;
        }

        public Builder setRoundRobinPointsForMatchWin(Float roundRobinPointsForMatchWin) {
            this.roundRobinPointsForMatchWin = roundRobinPointsForMatchWin;
            return this;
        }

        public Builder setRoundRobinPointsForMatchTie(Float roundRobinPointsForMatchTie) {
            this.roundRobinPointsForMatchTie = roundRobinPointsForMatchTie;
            return this;
        }

        public Builder setAcceptAttachments(Boolean acceptAttachments) {
            this.acceptAttachments = acceptAttachments;
            return this;
        }

        public Builder setHideForum(Boolean hideForum) {
            this.hideForum = hideForum;
            return this;
        }

        public Builder setShowRounds(Boolean showRounds) {
            this.showRounds = showRounds;
            return this;
        }

        public Builder setPrivate(Boolean _private) {
            this.aPrivate = _private;
            return this;
        }

        public Builder setNotifyUsersWhenTheTournamentEnds(Boolean notifyUsersWhenTheTournamentEnds) {
            this.notifyUsersWhenTheTournamentEnds = notifyUsersWhenTheTournamentEnds;
            return this;
        }

        public Builder setNotifyUsersWhenMatchesOpen(Boolean notifyUsersWhenMatchesOpen) {
            this.notifyUsersWhenMatchesOpen = notifyUsersWhenMatchesOpen;
            return this;
        }

        public Builder setSequentialPairings(Boolean sequentialPairings) {
            this.sequentialPairings = sequentialPairings;
            return this;
        }

        public Builder setSignupCap(Integer signupCap) {
            this.signupCap = signupCap;
            return this;
        }

        public Builder setStartAt(OffsetDateTime startAt) {
            this.startAt = startAt;
            return this;
        }

        public Builder setCheckInDuration(Integer checkInDuration) {
            this.checkInDuration = checkInDuration;
            return this;
        }

        public Builder setGrandFinalsModifier(GrandFinalsModifier grandFinalsModifier) {
            this.grandFinalsModifier = grandFinalsModifier;
            return this;
        }

        public TournamentQuery build() {
            if (!this.noName) {
                Validate.notBlank(name, "Name can't be blank");
                Validate.isTrue(name.length() <= 60, "Name can't be longer than 60 characters");
            }
            if (!this.noUrl) {
                Validate.notBlank(url, "URL can't be blank");
                Validate.matchesPattern(url, "^[a-zA-Z0-9_]*$", "URL can contain letters, numbers, and underscores only");
            }
            Validate.isTrue(signupCap > 3, "Participant / Signup Cap must be greater than 3");
            return new TournamentQuery(name, tournamentType, url, subdomain, description, openSignup, holdThirdPlaceMatch, pointsForMatchWin, pointsForMatchTie, pointsForGameWin, pointsForGameTie, pointsForBye, swissRounds, rankedBy, roundRobinPointsForGameWin, roundRobinPointsForGameTie, roundRobinPointsForMatchWin, roundRobinPointsForMatchTie, acceptAttachments, hideForum, showRounds, aPrivate, notifyUsersWhenMatchesOpen, notifyUsersWhenTheTournamentEnds, sequentialPairings, signupCap, startAt, checkInDuration, grandFinalsModifier);
        }
    }
}
