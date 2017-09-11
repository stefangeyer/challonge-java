/*
 * Copyright 2017 Stefan Geyer <stefangeyer at outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
