package com.exsoloscript.challonge.model;

import com.exsoloscript.challonge.model.enumeration.RankedBy;
import com.exsoloscript.challonge.model.enumeration.TournamentState;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.enumeration.query.GrandFinalsModifier;
import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * The POJO that will be mapped to the tournament requests by Gson
 *
 * @author EXSolo
 * @version 20160820.1
 */
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

    public String name() {
        return name;
    }

    public TournamentType tournamentType() {
        return tournamentType;
    }

    public String url() {
        return url;
    }

    public String subdomain() {
        return subdomain;
    }

    public String description() {
        return description;
    }

    public Boolean openSignup() {
        return openSignup;
    }

    public Boolean holdThirdPlaceMatch() {
        return holdThirdPlaceMatch;
    }

    public Float pointsForMatchWin() {
        return pointsForMatchWin;
    }

    public Float pointsForMatchTie() {
        return pointsForMatchTie;
    }

    public Float pointsForGameWin() {
        return pointsForGameWin;
    }

    public Float pointsForGameTie() {
        return pointsForGameTie;
    }

    public Float pointsForBye() {
        return pointsForBye;
    }

    public Integer swissRounds() {
        return swissRounds;
    }

    public RankedBy rankedBy() {
        return rankedBy;
    }

    public Float roundRobinPointsForGameWin() {
        return roundRobinPointsForGameWin;
    }

    public Float roundRobinPointsForGameTie() {
        return roundRobinPointsForGameTie;
    }

    public Float roundRobinPointsForMatchWin() {
        return roundRobinPointsForMatchWin;
    }

    public Float roundRobinPointsForMatchTie() {
        return roundRobinPointsForMatchTie;
    }

    public Boolean acceptAttachments() {
        return acceptAttachments;
    }

    public Boolean hideForum() {
        return hideForum;
    }

    public Boolean showRounds() {
        return showRounds;
    }

    public Boolean isPrivate() {
        return _private;
    }

    public Boolean notifyUsersWhenTheTournamentEnds() {
        return notifyUsersWhenTheTournamentEnds;
    }

    public Boolean sequentialPairings() {
        return sequentialPairings;
    }

    public Integer signupCap() {
        return signupCap;
    }

    public OffsetDateTime startAt() {
        return startAt;
    }

    public Integer checkInDuration() {
        return checkInDuration;
    }

    public Boolean allowParticipantMatchReporting() {
        return allowParticipantMatchReporting;
    }

    public Boolean anonymousVoting() {
        return anonymousVoting;
    }

    public String category() {
        return category;
    }

    public OffsetDateTime completedAt() {
        return completedAt;
    }

    public OffsetDateTime createdAt() {
        return createdAt;
    }

    public Boolean createdByApi() {
        return createdByApi;
    }

    public Boolean creditCapped() {
        return creditCapped;
    }

    public Integer gameId() {
        return gameId;
    }

    public Boolean groupStagesEnabled() {
        return groupStagesEnabled;
    }

    public Boolean hideSeeds() {
        return hideSeeds;
    }

    public Integer id() {
        return id;
    }

    public Integer maxPredictionsPerUser() {
        return maxPredictionsPerUser;
    }

    public Boolean notifyUsersWhenMatchesOpen() {
        return notifyUsersWhenMatchesOpen;
    }

    public Integer participantsCount() {
        return participantsCount;
    }

    public Integer predictionMethod() {
        return predictionMethod;
    }

    public OffsetDateTime predictionsOpenedAt() {
        return predictionsOpenedAt;
    }

    public Integer progressMeter() {
        return progressMeter;
    }

    public Boolean quickAdvance() {
        return quickAdvance;
    }

    public Boolean requireScoreAgreement() {
        return requireScoreAgreement;
    }

    public OffsetDateTime startedAt() {
        return startedAt;
    }

    public OffsetDateTime startedCheckingInAt() {
        return startedCheckingInAt;
    }

    public TournamentState state() {
        return state;
    }

    public Boolean teams() {
        return teams;
    }

    public List<String> tieBreaks() {
        return Lists.newArrayList(tieBreaks);
    }

    public OffsetDateTime updatedAt() {
        return updatedAt;
    }

    public String descriptionSource() {
        return descriptionSource;
    }

    public String fullChallongeUrl() {
        return fullChallongeUrl;
    }

    public String liveImageUrl() {
        return liveImageUrl;
    }

    public String signUpUrl() {
        return signUpUrl;
    }

    public Boolean reviewBeforeFinalizing() {
        return reviewBeforeFinalizing;
    }

    public Boolean acceptingPredictions() {
        return acceptingPredictions;
    }

    public Boolean participantsLocked() {
        return participantsLocked;
    }

    public String gameName() {
        return gameName;
    }

    public Boolean participantsSwappable() {
        return participantsSwappable;
    }

    public Boolean teamConvertable() {
        return teamConvertable;
    }

    public Boolean groupStagesWereStarted() {
        return groupStagesWereStarted;
    }

    public OffsetDateTime lockedAt() {
        return lockedAt;
    }

    public Integer eventId() {
        return eventId;
    }

    public Boolean publicPredictionsBeforeStartTime() {
        return publicPredictionsBeforeStartTime;
    }

    public GrandFinalsModifier grandFinalsModifier() {
        return grandFinalsModifier;
    }

    public List<Participant> participants() {
        return participants;
    }

    public List<Match> matches() {
        return matches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tournament that = (Tournament) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tournamentType != that.tournamentType) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (subdomain != null ? !subdomain.equals(that.subdomain) : that.subdomain != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (openSignup != null ? !openSignup.equals(that.openSignup) : that.openSignup != null) return false;
        if (holdThirdPlaceMatch != null ? !holdThirdPlaceMatch.equals(that.holdThirdPlaceMatch) : that.holdThirdPlaceMatch != null)
            return false;
        if (pointsForMatchWin != null ? !pointsForMatchWin.equals(that.pointsForMatchWin) : that.pointsForMatchWin != null)
            return false;
        if (pointsForMatchTie != null ? !pointsForMatchTie.equals(that.pointsForMatchTie) : that.pointsForMatchTie != null)
            return false;
        if (pointsForGameWin != null ? !pointsForGameWin.equals(that.pointsForGameWin) : that.pointsForGameWin != null)
            return false;
        if (pointsForGameTie != null ? !pointsForGameTie.equals(that.pointsForGameTie) : that.pointsForGameTie != null)
            return false;
        if (pointsForBye != null ? !pointsForBye.equals(that.pointsForBye) : that.pointsForBye != null) return false;
        if (swissRounds != null ? !swissRounds.equals(that.swissRounds) : that.swissRounds != null) return false;
        if (rankedBy != that.rankedBy) return false;
        if (roundRobinPointsForGameWin != null ? !roundRobinPointsForGameWin.equals(that.roundRobinPointsForGameWin) : that.roundRobinPointsForGameWin != null)
            return false;
        if (roundRobinPointsForGameTie != null ? !roundRobinPointsForGameTie.equals(that.roundRobinPointsForGameTie) : that.roundRobinPointsForGameTie != null)
            return false;
        if (roundRobinPointsForMatchWin != null ? !roundRobinPointsForMatchWin.equals(that.roundRobinPointsForMatchWin) : that.roundRobinPointsForMatchWin != null)
            return false;
        if (roundRobinPointsForMatchTie != null ? !roundRobinPointsForMatchTie.equals(that.roundRobinPointsForMatchTie) : that.roundRobinPointsForMatchTie != null)
            return false;
        if (acceptAttachments != null ? !acceptAttachments.equals(that.acceptAttachments) : that.acceptAttachments != null)
            return false;
        if (hideForum != null ? !hideForum.equals(that.hideForum) : that.hideForum != null) return false;
        if (showRounds != null ? !showRounds.equals(that.showRounds) : that.showRounds != null) return false;
        if (_private != null ? !_private.equals(that._private) : that._private != null) return false;
        if (notifyUsersWhenTheTournamentEnds != null ? !notifyUsersWhenTheTournamentEnds.equals(that.notifyUsersWhenTheTournamentEnds) : that.notifyUsersWhenTheTournamentEnds != null)
            return false;
        if (sequentialPairings != null ? !sequentialPairings.equals(that.sequentialPairings) : that.sequentialPairings != null)
            return false;
        if (signupCap != null ? !signupCap.equals(that.signupCap) : that.signupCap != null) return false;
        if (startAt != null ? !startAt.equals(that.startAt) : that.startAt != null) return false;
        if (checkInDuration != null ? !checkInDuration.equals(that.checkInDuration) : that.checkInDuration != null)
            return false;
        if (allowParticipantMatchReporting != null ? !allowParticipantMatchReporting.equals(that.allowParticipantMatchReporting) : that.allowParticipantMatchReporting != null)
            return false;
        if (anonymousVoting != null ? !anonymousVoting.equals(that.anonymousVoting) : that.anonymousVoting != null)
            return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (completedAt != null ? !completedAt.equals(that.completedAt) : that.completedAt != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (createdByApi != null ? !createdByApi.equals(that.createdByApi) : that.createdByApi != null) return false;
        if (creditCapped != null ? !creditCapped.equals(that.creditCapped) : that.creditCapped != null) return false;
        if (gameId != null ? !gameId.equals(that.gameId) : that.gameId != null) return false;
        if (groupStagesEnabled != null ? !groupStagesEnabled.equals(that.groupStagesEnabled) : that.groupStagesEnabled != null)
            return false;
        if (hideSeeds != null ? !hideSeeds.equals(that.hideSeeds) : that.hideSeeds != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (maxPredictionsPerUser != null ? !maxPredictionsPerUser.equals(that.maxPredictionsPerUser) : that.maxPredictionsPerUser != null)
            return false;
        if (notifyUsersWhenMatchesOpen != null ? !notifyUsersWhenMatchesOpen.equals(that.notifyUsersWhenMatchesOpen) : that.notifyUsersWhenMatchesOpen != null)
            return false;
        if (participantsCount != null ? !participantsCount.equals(that.participantsCount) : that.participantsCount != null)
            return false;
        if (predictionMethod != null ? !predictionMethod.equals(that.predictionMethod) : that.predictionMethod != null)
            return false;
        if (predictionsOpenedAt != null ? !predictionsOpenedAt.equals(that.predictionsOpenedAt) : that.predictionsOpenedAt != null)
            return false;
        if (progressMeter != null ? !progressMeter.equals(that.progressMeter) : that.progressMeter != null)
            return false;
        if (quickAdvance != null ? !quickAdvance.equals(that.quickAdvance) : that.quickAdvance != null) return false;
        if (requireScoreAgreement != null ? !requireScoreAgreement.equals(that.requireScoreAgreement) : that.requireScoreAgreement != null)
            return false;
        if (startedAt != null ? !startedAt.equals(that.startedAt) : that.startedAt != null) return false;
        if (startedCheckingInAt != null ? !startedCheckingInAt.equals(that.startedCheckingInAt) : that.startedCheckingInAt != null)
            return false;
        if (state != that.state) return false;
        if (teams != null ? !teams.equals(that.teams) : that.teams != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(tieBreaks, that.tieBreaks)) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (descriptionSource != null ? !descriptionSource.equals(that.descriptionSource) : that.descriptionSource != null)
            return false;
        if (fullChallongeUrl != null ? !fullChallongeUrl.equals(that.fullChallongeUrl) : that.fullChallongeUrl != null)
            return false;
        if (liveImageUrl != null ? !liveImageUrl.equals(that.liveImageUrl) : that.liveImageUrl != null) return false;
        if (signUpUrl != null ? !signUpUrl.equals(that.signUpUrl) : that.signUpUrl != null) return false;
        if (reviewBeforeFinalizing != null ? !reviewBeforeFinalizing.equals(that.reviewBeforeFinalizing) : that.reviewBeforeFinalizing != null)
            return false;
        if (acceptingPredictions != null ? !acceptingPredictions.equals(that.acceptingPredictions) : that.acceptingPredictions != null)
            return false;
        if (participantsLocked != null ? !participantsLocked.equals(that.participantsLocked) : that.participantsLocked != null)
            return false;
        if (gameName != null ? !gameName.equals(that.gameName) : that.gameName != null) return false;
        if (participantsSwappable != null ? !participantsSwappable.equals(that.participantsSwappable) : that.participantsSwappable != null)
            return false;
        if (teamConvertable != null ? !teamConvertable.equals(that.teamConvertable) : that.teamConvertable != null)
            return false;
        if (groupStagesWereStarted != null ? !groupStagesWereStarted.equals(that.groupStagesWereStarted) : that.groupStagesWereStarted != null)
            return false;
        if (lockedAt != null ? !lockedAt.equals(that.lockedAt) : that.lockedAt != null) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (publicPredictionsBeforeStartTime != null ? !publicPredictionsBeforeStartTime.equals(that.publicPredictionsBeforeStartTime) : that.publicPredictionsBeforeStartTime != null)
            return false;
        if (grandFinalsModifier != that.grandFinalsModifier) return false;
        if (participants != null ? !participants.equals(that.participants) : that.participants != null) return false;
        return matches != null ? matches.equals(that.matches) : that.matches == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (tournamentType != null ? tournamentType.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (subdomain != null ? subdomain.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (openSignup != null ? openSignup.hashCode() : 0);
        result = 31 * result + (holdThirdPlaceMatch != null ? holdThirdPlaceMatch.hashCode() : 0);
        result = 31 * result + (pointsForMatchWin != null ? pointsForMatchWin.hashCode() : 0);
        result = 31 * result + (pointsForMatchTie != null ? pointsForMatchTie.hashCode() : 0);
        result = 31 * result + (pointsForGameWin != null ? pointsForGameWin.hashCode() : 0);
        result = 31 * result + (pointsForGameTie != null ? pointsForGameTie.hashCode() : 0);
        result = 31 * result + (pointsForBye != null ? pointsForBye.hashCode() : 0);
        result = 31 * result + (swissRounds != null ? swissRounds.hashCode() : 0);
        result = 31 * result + (rankedBy != null ? rankedBy.hashCode() : 0);
        result = 31 * result + (roundRobinPointsForGameWin != null ? roundRobinPointsForGameWin.hashCode() : 0);
        result = 31 * result + (roundRobinPointsForGameTie != null ? roundRobinPointsForGameTie.hashCode() : 0);
        result = 31 * result + (roundRobinPointsForMatchWin != null ? roundRobinPointsForMatchWin.hashCode() : 0);
        result = 31 * result + (roundRobinPointsForMatchTie != null ? roundRobinPointsForMatchTie.hashCode() : 0);
        result = 31 * result + (acceptAttachments != null ? acceptAttachments.hashCode() : 0);
        result = 31 * result + (hideForum != null ? hideForum.hashCode() : 0);
        result = 31 * result + (showRounds != null ? showRounds.hashCode() : 0);
        result = 31 * result + (_private != null ? _private.hashCode() : 0);
        result = 31 * result + (notifyUsersWhenTheTournamentEnds != null ? notifyUsersWhenTheTournamentEnds.hashCode() : 0);
        result = 31 * result + (sequentialPairings != null ? sequentialPairings.hashCode() : 0);
        result = 31 * result + (signupCap != null ? signupCap.hashCode() : 0);
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (checkInDuration != null ? checkInDuration.hashCode() : 0);
        result = 31 * result + (allowParticipantMatchReporting != null ? allowParticipantMatchReporting.hashCode() : 0);
        result = 31 * result + (anonymousVoting != null ? anonymousVoting.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (completedAt != null ? completedAt.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (createdByApi != null ? createdByApi.hashCode() : 0);
        result = 31 * result + (creditCapped != null ? creditCapped.hashCode() : 0);
        result = 31 * result + (gameId != null ? gameId.hashCode() : 0);
        result = 31 * result + (groupStagesEnabled != null ? groupStagesEnabled.hashCode() : 0);
        result = 31 * result + (hideSeeds != null ? hideSeeds.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (maxPredictionsPerUser != null ? maxPredictionsPerUser.hashCode() : 0);
        result = 31 * result + (notifyUsersWhenMatchesOpen != null ? notifyUsersWhenMatchesOpen.hashCode() : 0);
        result = 31 * result + (participantsCount != null ? participantsCount.hashCode() : 0);
        result = 31 * result + (predictionMethod != null ? predictionMethod.hashCode() : 0);
        result = 31 * result + (predictionsOpenedAt != null ? predictionsOpenedAt.hashCode() : 0);
        result = 31 * result + (progressMeter != null ? progressMeter.hashCode() : 0);
        result = 31 * result + (quickAdvance != null ? quickAdvance.hashCode() : 0);
        result = 31 * result + (requireScoreAgreement != null ? requireScoreAgreement.hashCode() : 0);
        result = 31 * result + (startedAt != null ? startedAt.hashCode() : 0);
        result = 31 * result + (startedCheckingInAt != null ? startedCheckingInAt.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (teams != null ? teams.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(tieBreaks);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (descriptionSource != null ? descriptionSource.hashCode() : 0);
        result = 31 * result + (fullChallongeUrl != null ? fullChallongeUrl.hashCode() : 0);
        result = 31 * result + (liveImageUrl != null ? liveImageUrl.hashCode() : 0);
        result = 31 * result + (signUpUrl != null ? signUpUrl.hashCode() : 0);
        result = 31 * result + (reviewBeforeFinalizing != null ? reviewBeforeFinalizing.hashCode() : 0);
        result = 31 * result + (acceptingPredictions != null ? acceptingPredictions.hashCode() : 0);
        result = 31 * result + (participantsLocked != null ? participantsLocked.hashCode() : 0);
        result = 31 * result + (gameName != null ? gameName.hashCode() : 0);
        result = 31 * result + (participantsSwappable != null ? participantsSwappable.hashCode() : 0);
        result = 31 * result + (teamConvertable != null ? teamConvertable.hashCode() : 0);
        result = 31 * result + (groupStagesWereStarted != null ? groupStagesWereStarted.hashCode() : 0);
        result = 31 * result + (lockedAt != null ? lockedAt.hashCode() : 0);
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (publicPredictionsBeforeStartTime != null ? publicPredictionsBeforeStartTime.hashCode() : 0);
        result = 31 * result + (grandFinalsModifier != null ? grandFinalsModifier.hashCode() : 0);
        result = 31 * result + (participants != null ? participants.hashCode() : 0);
        result = 31 * result + (matches != null ? matches.hashCode() : 0);
        return result;
    }
}
