package com.exsoloscript.challonge.model.query;

import com.exsoloscript.challonge.model.Tournament;
import com.google.gson.annotations.SerializedName;
import org.joda.time.DateTime;

public class
TournamentQuery {
    private String name;
    @SerializedName("tournament_type")
    private Tournament.TournamentType tournamentType;
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
    private Tournament.RankedBy rankedBy;
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
    private DateTime startAt;
    @SerializedName("check_in_duration")
    private int checkInDuration;
    @SerializedName("grand_finals_modifier")
    private GrandFinalsModifier grandFinalsModifier;

    public enum TournamentQueryState {
        ALL("all"), PENDING("pending"), IN_PROGRESS("in_progress"), ENDED("ended");

        private String lowerCase;

        TournamentQueryState(String lowerCase) {
            this.lowerCase = lowerCase;
        }

        @Override
        public String toString() {
            return this.lowerCase;
        }

        public static TournamentQueryState fromString(String name) {
            return valueOf(name.toUpperCase());
        }
    }

    public enum GrandFinalsModifier {
        BLANK(null), SINGLE_MATCH("single match"), SKIP("skip");

        private String lowerCase;

        GrandFinalsModifier(String lowerCase) {
            this.lowerCase = lowerCase;
        }

        @Override
        public String toString() {
            return this.lowerCase;
        }

        public static GrandFinalsModifier fromString(String name) {
            return valueOf(name.toUpperCase());
        }
    }
}
