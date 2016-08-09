package com.exsoloscript.challonge.model;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Tournament {
    private String name;
    @SerializedName("tournament_type")
    //type
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
    //rankedby
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
    private String startAt;
    @SerializedName("check_in_duration")
    private int checkInDuration;
    @SerializedName("allow_participant_match_reporting")
    private boolean allowParticipantMatchReporting;
    @SerializedName("anonymous_voting")
    private boolean anonymousVoting;
    private String category;
    @SerializedName("completed_at")
    private ZonedDateTime completedAt;
    @SerializedName("created_at")
    private ZonedDateTime createdAt;
    @SerializedName("created_by_api")
    private boolean createdByApi;
    @SerializedName("credit_capped")
    private boolean creditCapped;
    @SerializedName("game_id")
    private int gameId;
    @SerializedName("group_stages_enabled")
    private boolean groupStagesEnabled;
    @SerializedName("hide_seeds")
    private boolean hideSeeds;
    private int id;
    @SerializedName("max_predictions_per_user")
    private int maxPredictionsPerUser;
    @SerializedName("notify_users_when_matches_open")
    private boolean notifyUsersWhenMatchesOpen;
    @SerializedName("participants_count")
    private int participantsCount;
    @SerializedName("prediction_method")
    private int predictionMethod;
    @SerializedName("predictions_opened_at")
    private String predictionsOpenedAt;
    @SerializedName("progress_meter")
    private int progressMeter;
    @SerializedName("quick_advance")
    private boolean quickAdvance;
    @SerializedName("require_score_agreement")
    private boolean requireScoreAgreement;
    @SerializedName("started_at")
    private ZonedDateTime startedAt;
    private String startedCheckingInAt;
    //state
    private TournamentState state;
    private boolean teams;
    @SerializedName("tie_breaks")
    private List<String> tieBreaks;
    @SerializedName("updated_at")
    private ZonedDateTime updatedAt;
    @SerializedName("description_source")
    private String descriptionSource;
    @SerializedName("full_challonge_url")
    private String fullChallongeUrl;
    @SerializedName("live_image_url")
    private String liveImageUrl;
    @SerializedName("sign_up_url")
    private String signUpUrl;
    @SerializedName("review_before_finalizing")
    private boolean reviewBeforeFinalizing;
    @SerializedName("accepting_predictions")
    private boolean acceptingPredictions;
    @SerializedName("participants_locked")
    private boolean participantsLocked;
    @SerializedName("game_name")
    private String gameName;
    @SerializedName("participants_swappable")
    private boolean participantsSwappable;
    @SerializedName("team_convertable")
    private boolean teamConvertable;
    @SerializedName("group_stages_were_started")
    private boolean groupStagesWereStarted;

    public enum TournamentType {
        SINGLE_ELIMINATION("single elimination"), DOUBLE_ELIMINATION("double elimination"), ROUND_ROBIN("round robin"), SWISS("swiss");

        private String lowerCase;

        TournamentType(String lowerCase) {
            this.lowerCase = lowerCase;
        }

        public static TournamentType fromString(String name) {
            Optional<TournamentType> optType = Arrays.stream(values()).filter(type -> type.toString().equals(name.toLowerCase())).findFirst();
            return optType.isPresent() ? optType.get() : null;
        }

        @Override
        public String toString() {
            return this.lowerCase;
        }
    }

    public enum TournamentState {
        PENDING("pending"),
        UNDERWAY("underway"),
        GROUP_STAGES_UNDERWAY("group_stages_underway"),
        GROUP_STAGES_FINALIZED("group_stages_finalized"),
        AWAITING_REVIEW("awaiting_review"),
        COMPLETE("complete");

        private String lowerCase;

        TournamentState(String lowerCase) {
            this.lowerCase = lowerCase;
        }

        public static TournamentState fromString(String name) {
            return valueOf(name.toUpperCase());
        }

        @Override
        public String toString() {
            return this.lowerCase;
        }
    }

    public enum RankedBy {
        MATCH_WINS("match wins"), GAME_WINS("game wins"), POINT_SCORED("point scored"), POINTS_DIFFERENCE("points difference"), CUSTOM("custom");

        private String lowerCase;

        RankedBy(String lowerCase) {
            this.lowerCase = lowerCase;
        }

        public static RankedBy fromString(String name) {
            Optional<RankedBy> optRankedBy = Arrays.stream(values()).filter(rankedBy -> rankedBy.toString().equals(name.toLowerCase())).findFirst();
            return optRankedBy.isPresent() ? optRankedBy.get() : null;
        }

        @Override
        public String toString() {
            return this.lowerCase;
        }
    }
}
