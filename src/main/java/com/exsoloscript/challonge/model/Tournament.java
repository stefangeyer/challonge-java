package com.exsoloscript.challonge.model;

import com.exsoloscript.challonge.model.enumeration.RankedBy;
import com.exsoloscript.challonge.model.enumeration.TournamentState;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.enumeration.query.GrandFinalsModifier;
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
    @SerializedName("notify_users_when_the_tournament_ends")
    private Boolean notifyUsersWhenTheTournamentEnds;
    @SerializedName("sequential_pairings")
    private Boolean sequentialPairings;
    @SerializedName("signup_cap")
    private int signupCap;
    @SerializedName("start_at")
    private ZonedDateTime startAt;
    @SerializedName("check_in_duration")
    private int checkInDuration;
    @SerializedName("allow_participant_match_reporting")
    private Boolean allowParticipantMatchReporting;
    @SerializedName("anonymous_voting")
    private Boolean anonymousVoting;
    private String category;
    @SerializedName("completed_at")
    private ZonedDateTime completedAt;
    @SerializedName("created_at")
    private ZonedDateTime createdAt;
    @SerializedName("created_by_api")
    private Boolean createdByApi;
    @SerializedName("credit_capped")
    private Boolean creditCapped;
    @SerializedName("game_id")
    private int gameId;
    @SerializedName("group_stages_enabled")
    private Boolean groupStagesEnabled;
    @SerializedName("hide_seeds")
    private Boolean hideSeeds;
    private int id;
    @SerializedName("max_predictions_per_user")
    private int maxPredictionsPerUser;
    @SerializedName("notify_users_when_matches_open")
    private Boolean notifyUsersWhenMatchesOpen;
    @SerializedName("participants_count")
    private int participantsCount;
    @SerializedName("prediction_method")
    private int predictionMethod;
    @SerializedName("predictions_opened_at")
    private ZonedDateTime predictionsOpenedAt;
    @SerializedName("progress_meter")
    private int progressMeter;
    @SerializedName("quick_advance")
    private Boolean quickAdvance;
    @SerializedName("require_score_agreement")
    private Boolean requireScoreAgreement;
    @SerializedName("started_at")
    private ZonedDateTime startedAt;
    private String startedCheckingInAt;
    private TournamentState state;
    private Boolean teams;
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
    private Boolean reviewBeforeFinalizing;
    @SerializedName("accepting_predictions")
    private Boolean acceptingPredictions;
    @SerializedName("participants_locked")
    private Boolean participantsLocked;
    @SerializedName("game_name")
    private String gameName;
    @SerializedName("participants_swappable")
    private Boolean participantsSwappable;
    @SerializedName("team_convertable")
    private Boolean teamConvertable;
    @SerializedName("group_stages_were_started")
    private Boolean groupStagesWereStarted;
    @SerializedName("locked_at")
    private ZonedDateTime lockedAt;
    @SerializedName("event_id")
    private Integer eventId;
    @SerializedName("public_predictions_before_start_time")
    private Boolean publicPredictionsBeforeStartTime;
    @SerializedName("grand_finals_modifier")
    private GrandFinalsModifier grandFinalsModifier;

    private List<Participant> participants;
    private List<Match> matches;

    public String getName() {
        return name;
    }

    public TournamentType getTournamentType() {
        return tournamentType;
    }

    public ZonedDateTime getLockedAt() {
        return lockedAt;
    }

    public Integer getEventId() {
        return eventId;
    }

    public Boolean getPublicPredictionsBeforeStartTime() {
        return publicPredictionsBeforeStartTime;
    }

    public GrandFinalsModifier getGrandFinalsModifier() {
        return grandFinalsModifier;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public List<Match> getMatches() {
        return matches;
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

    public Boolean isSignupOpen() {
        return openSignup;
    }

    public Boolean holdThirdPlaceMatch() {
        return holdThirdPlaceMatch;
    }

    public float getPointsForMatchWin() {
        return this.pointsForMatchWin;
    }

    public float getPointsForMatchTie() {
        return pointsForMatchTie;
    }

    public float getPointsForGameWin() {
        return pointsForGameWin;
    }

    public float getPointsForGameTie() {
        return pointsForGameTie;
    }

    public float getPointsForBye() {
        return pointsForBye;
    }

    public int getSwissRounds() {
        return swissRounds;
    }

    public RankedBy rankedBy() {
        return rankedBy;
    }

    public float getRoundRobinPointsForGameWin() {
        return roundRobinPointsForGameWin;
    }

    public float getRoundRobinPointsForGameTie() {
        return roundRobinPointsForGameTie;
    }

    public float getRoundRobinPointsForMatchWin() {
        return roundRobinPointsForMatchWin;
    }

    public float getRoundRobinPointsForMatchTie() {
        return roundRobinPointsForMatchTie;
    }

    public Boolean isAcceptAttachments() {
        return acceptAttachments;
    }

    public Boolean isHideForum() {
        return hideForum;
    }

    public Boolean isShowRounds() {
        return showRounds;
    }

    public Boolean isPrivate() {
        return _private;
    }

    public Boolean doesNotifyUsersWhenTheTournamentEnds() {
        return notifyUsersWhenTheTournamentEnds;
    }

    public Boolean hasSequentialPairings() {
        return sequentialPairings;
    }

    public int getSignupCap() {
        return signupCap;
    }

    public ZonedDateTime startsAt() {
        return startAt;
    }

    public int getCheckInDuration() {
        return checkInDuration;
    }

    public Boolean isAllowParticipantMatchReporting() {
        return allowParticipantMatchReporting;
    }

    public Boolean isAnonymousVoting() {
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

    public Boolean isCreatedByApi() {
        return createdByApi;
    }

    public Boolean isCreditCapped() {
        return creditCapped;
    }

    public int getGameId() {
        return gameId;
    }

    public Boolean isGroupStagesEnabled() {
        return groupStagesEnabled;
    }

    public Boolean areSeedsHidden() {
        return hideSeeds;
    }

    public int getId() {
        return id;
    }

    public int getMaxPredictionsPerUser() {
        return maxPredictionsPerUser;
    }

    public Boolean doesNotifyUsersWhenMatchesOpen() {
        return notifyUsersWhenMatchesOpen;
    }

    public int getParticipantsCount() {
        return participantsCount;
    }

    public int getPredictionMethod() {
        return predictionMethod;
    }

    public ZonedDateTime predictionsOpenedAt() {
        return predictionsOpenedAt;
    }

    public int getProgressMeter() {
        return progressMeter;
    }

    public Boolean isQuickAdvance() {
        return quickAdvance;
    }

    public Boolean isRequireScoreAgreement() {
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

    public Boolean isTeams() {
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

    public Boolean isReviewBeforeFinalizing() {
        return reviewBeforeFinalizing;
    }

    public Boolean isAcceptingPredictions() {
        return acceptingPredictions;
    }

    public Boolean isParticipantsLocked() {
        return participantsLocked;
    }

    public String getGameName() {
        return gameName;
    }

    public Boolean isParticipantsSwappable() {
        return participantsSwappable;
    }

    public Boolean isTeamConvertable() {
        return teamConvertable;
    }

    public Boolean isGroupStagesWereStarted() {
        return groupStagesWereStarted;
    }
}
