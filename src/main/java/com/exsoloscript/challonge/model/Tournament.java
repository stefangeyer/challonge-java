package com.exsoloscript.challonge.model;

import com.exsoloscript.challonge.model.enumeration.RankedBy;
import com.exsoloscript.challonge.model.enumeration.TournamentState;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.enumeration.query.GrandFinalsModifier;
import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * The POJO that will be mapped to the tournament requests by Gson
 *
 * @author EXSolo
 * @version 20160820.1
 */
@Data
@Accessors(fluent = true)
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
    private Integer signupCap;
    @SerializedName("start_at")
    private OffsetDateTime startAt;
    @SerializedName("check_in_duration")
    private Integer checkInDuration;
    @SerializedName("allow_participant_match_reporting")
    private Boolean allowParticipantMatchReporting;
    @SerializedName("anonymous_voting")
    private Boolean anonymousVoting;
    private String category;
    @SerializedName("completed_at")
    private OffsetDateTime completedAt;
    @SerializedName("created_at")
    private OffsetDateTime createdAt;
    @SerializedName("created_by_api")
    private Boolean createdByApi;
    @SerializedName("credit_capped")
    private Boolean creditCapped;
    @SerializedName("game_id")
    private Integer gameId;
    @SerializedName("group_stages_enabled")
    private Boolean groupStagesEnabled;
    @SerializedName("hide_seeds")
    private Boolean hideSeeds;
    private Integer id;
    @SerializedName("max_predictions_per_user")
    private Integer maxPredictionsPerUser;
    @SerializedName("notify_users_when_matches_open")
    private Boolean notifyUsersWhenMatchesOpen;
    @SerializedName("participants_count")
    private Integer participantsCount;
    @SerializedName("prediction_method")
    private Integer predictionMethod;
    @SerializedName("predictions_opened_at")
    private OffsetDateTime predictionsOpenedAt;
    @SerializedName("progress_meter")
    private Integer progressMeter;
    @SerializedName("quick_advance")
    private Boolean quickAdvance;
    @SerializedName("require_score_agreement")
    private Boolean requireScoreAgreement;
    @SerializedName("started_at")
    private OffsetDateTime startedAt;
    @SerializedName("started_checking_in_at")
    private OffsetDateTime startedCheckingInAt;
    private TournamentState state;
    private Boolean teams;
    @SerializedName("tie_breaks")
    private String[] tieBreaks;
    @SerializedName("updated_at")
    private OffsetDateTime updatedAt;
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
    private OffsetDateTime lockedAt;
    @SerializedName("event_id")
    private Integer eventId;
    @SerializedName("public_predictions_before_start_time")
    private Boolean publicPredictionsBeforeStartTime;
    @SerializedName("grand_finals_modifier")
    private GrandFinalsModifier grandFinalsModifier;
    private List<Participant> participants;
    private List<Match> matches;
}
