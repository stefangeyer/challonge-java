package com.exsoloscript.challonge.model.query;

import com.exsoloscript.challonge.model.enumeration.RankedBy;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.enumeration.query.GrandFinalsModifier;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.Validate;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Query for creating or updating a tournament. This class can be accessed using it's builder.
 *
 * @author EXSolo
 * @version 20160819.1
 */
@Data
@Accessors(fluent = true)
@Builder
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
}
