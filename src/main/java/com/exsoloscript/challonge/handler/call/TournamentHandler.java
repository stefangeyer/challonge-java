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

package com.exsoloscript.challonge.handler.call;

import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.exsoloscript.challonge.handler.retrofit.RetrofitTournamentHandler;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.enumeration.query.TournamentQueryState;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.List;

/**
 * Accessible API handler for tournaments.
 *
 * @author EXSolo
 * @version 20160822.1
 */
@Singleton
public class TournamentHandler {

    private final RetrofitTournamentHandler tournamentHandler;
    private final RetrofitChallongeApiCallFactory factory;

    @Inject
    TournamentHandler(RetrofitServiceProvider provider, RetrofitChallongeApiCallFactory factory) {
        this.tournamentHandler = provider.createService(RetrofitTournamentHandler.class);
        this.factory = factory;
    }

    public ChallongeApiCall<List<Tournament>> getTournaments() {
        return this.getTournaments(null, null, null, null, null);
    }

    /**
     * @see RetrofitTournamentHandler#getTournaments(TournamentQueryState, TournamentType, String, String, String)
     */
    public ChallongeApiCall<List<Tournament>> getTournaments(TournamentQueryState state, TournamentType type, String createdAfter, String createdBefore, String subdomain) {
        return this.factory.createApiCall(this.tournamentHandler.getTournaments(state, type, createdAfter, createdBefore, subdomain));
    }

    /**
     * @see RetrofitTournamentHandler#getTournament(String, int, int)
     */
    public ChallongeApiCall<Tournament> getTournament(String name, boolean includeParticipants, boolean includeMatches) {
        Validate.isTrue(StringUtils.isNotBlank(name), "Tournament string is required");
        return this.factory.createApiCall(this.tournamentHandler.getTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0));
    }

    /**
     * @see RetrofitTournamentHandler#createTournament(TournamentQuery)
     */
    public ChallongeApiCall<Tournament> createTournament(TournamentQuery tournament) {
        return this.factory.createApiCall(this.tournamentHandler.createTournament(tournament));
    }

    /**
     * @see RetrofitTournamentHandler#updateTournament(String, TournamentQuery)
     */
    public ChallongeApiCall<Tournament> updateTournament(String name, TournamentQuery tournament) {
        Validate.isTrue(StringUtils.isNotBlank(name), "Tournament string is required");
        return this.factory.createApiCall(this.tournamentHandler.updateTournament(name, tournament));
    }

    /**
     * @see RetrofitTournamentHandler#deleteTournament(String)
     */
    public ChallongeApiCall<Tournament> deleteTournament(String name) {
        Validate.isTrue(StringUtils.isNotBlank(name), "Tournament string is required");
        return this.factory.createApiCall(this.tournamentHandler.deleteTournament(name));
    }

    /**
     * @see RetrofitTournamentHandler#processCheckIns(String, int, int)
     */
    public ChallongeApiCall<Tournament> processCheckIns(String name, boolean includeParticipants, boolean includeMatches) {
        Validate.isTrue(StringUtils.isNotBlank(name), "Tournament string is required");
        return this.factory.createApiCall(this.tournamentHandler.processCheckIns(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0));
    }

    /**
     * @see RetrofitTournamentHandler#abortCheckIn(String, int, int)
     */
    public ChallongeApiCall<Tournament> abortCheckIn(String name, boolean includeParticipants, boolean includeMatches) {
        Validate.isTrue(StringUtils.isNotBlank(name), "Tournament string is required");
        return this.factory.createApiCall(this.tournamentHandler.abortCheckIn(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0));
    }

    /**
     * @see RetrofitTournamentHandler#startTournament(String, int, int)
     */
    public ChallongeApiCall<Tournament> startTournament(String name, boolean includeParticipants, boolean includeMatches) {
        Validate.isTrue(StringUtils.isNotBlank(name), "Tournament string is required");
        return this.factory.createApiCall(this.tournamentHandler.startTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0));
    }

    /**
     * @see RetrofitTournamentHandler#finalizeTournament(String, int, int)
     */
    public ChallongeApiCall<Tournament> finalizeTournament(String name, boolean includeParticipants, boolean includeMatches) {
        Validate.isTrue(StringUtils.isNotBlank(name), "Tournament string is required");
        return this.factory.createApiCall(this.tournamentHandler.finalizeTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0));
    }

    /**
     * @see RetrofitTournamentHandler#resetTournament(String, int, int)
     */
    public ChallongeApiCall<Tournament> resetTournament(String name, boolean includeParticipants, boolean includeMatches) {
        Validate.isTrue(StringUtils.isNotBlank(name), "Tournament string is required");
        return this.factory.createApiCall(this.tournamentHandler.resetTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0));
    }
}
