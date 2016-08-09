package com.exsoloscript.challonge.model;

import com.exsoloscript.challonge.model.enumeration.RankedBy;
import com.exsoloscript.challonge.model.enumeration.TournamentState;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;
import java.util.List;

public class Tournament {
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

    public String getStartAt() {
        return startAt;
    }

    public int getCheckInDuration() {
        return checkInDuration;
    }

    public boolean isAllowParticipantMatchReporting() {
        return allowParticipantMatchReporting;
    }

    public boolean isAnonymousVoting() {
        return anonymousVoting;
    }

    public String getCategory() {
        return category;
    }

    public ZonedDateTime getCompletedAt() {
        return completedAt;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isCreatedByApi() {
        return createdByApi;
    }

    public boolean isCreditCapped() {
        return creditCapped;
    }

    public int getGameId() {
        return gameId;
    }

    public boolean isGroupStagesEnabled() {
        return groupStagesEnabled;
    }

    public boolean isHideSeeds() {
        return hideSeeds;
    }

    public int getId() {
        return id;
    }

    public int getMaxPredictionsPerUser() {
        return maxPredictionsPerUser;
    }

    public boolean isNotifyUsersWhenMatchesOpen() {
        return notifyUsersWhenMatchesOpen;
    }

    public int getParticipantsCount() {
        return participantsCount;
    }

    public int getPredictionMethod() {
        return predictionMethod;
    }

    public String getPredictionsOpenedAt() {
        return predictionsOpenedAt;
    }

    public int getProgressMeter() {
        return progressMeter;
    }

    public boolean isQuickAdvance() {
        return quickAdvance;
    }

    public boolean isRequireScoreAgreement() {
        return requireScoreAgreement;
    }

    public ZonedDateTime getStartedAt() {
        return startedAt;
    }

    public String getStartedCheckingInAt() {
        return startedCheckingInAt;
    }

    public TournamentState getState() {
        return state;
    }

    public boolean isTeams() {
        return teams;
    }

    public List<String> getTieBreaks() {
        return tieBreaks;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getDescriptionSource() {
        return descriptionSource;
    }

    public String getFullChallongeUrl() {
        return fullChallongeUrl;
    }

    public String getLiveImageUrl() {
        return liveImageUrl;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public boolean isReviewBeforeFinalizing() {
        return reviewBeforeFinalizing;
    }

    public boolean isAcceptingPredictions() {
        return acceptingPredictions;
    }

    public boolean isParticipantsLocked() {
        return participantsLocked;
    }

    public String getGameName() {
        return gameName;
    }

    public boolean isParticipantsSwappable() {
        return participantsSwappable;
    }

    public boolean isTeamConvertable() {
        return teamConvertable;
    }

    public boolean isGroupStagesWereStarted() {
        return groupStagesWereStarted;
    }
}
