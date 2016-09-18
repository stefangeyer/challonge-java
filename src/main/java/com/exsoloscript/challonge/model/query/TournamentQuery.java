/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 EXSolo <exsoloscripter at gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.exsoloscript.challonge.model.query;

import com.exsoloscript.challonge.model.enumeration.RankedBy;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.enumeration.query.GrandFinalsModifier;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@EqualsAndHashCode(exclude = {"startAt"})
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
