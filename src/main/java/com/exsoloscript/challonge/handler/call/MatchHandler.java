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

import com.exsoloscript.challonge.handler.retrofit.RetrofitMatchHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.enumeration.MatchState;
import com.exsoloscript.challonge.model.query.MatchQuery;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.List;

/**
 * Accessible API handler for matches.
 *
 * @author EXSolo
 * @version 20160822.1
 */
@Singleton
public class MatchHandler {

    private final RetrofitMatchHandler matchHandler;
    private final RetrofitChallongeApiCallFactory factory;

    @Inject
    MatchHandler(RetrofitServiceProvider provider, RetrofitChallongeApiCallFactory factory) {
        this.matchHandler = provider.createService(RetrofitMatchHandler.class);
        this.factory = factory;
    }

    /**
     * @see RetrofitMatchHandler#getMatches(String, Integer, MatchState)
     */
    public ChallongeApiCall<List<Match>> getMatches(String tournament, Integer participantId, MatchState state) {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        return this.factory.createApiCall(this.matchHandler.getMatches(tournament, participantId, state));
    }

    public ChallongeApiCall<List<Match>> getMatches(String tournament) {
        return getMatches(tournament, null, null);
    }

    /**
     * @see RetrofitMatchHandler#getMatch(String, int, int)
     */
    public ChallongeApiCall<Match> getMatch(String tournament, int matchId, boolean includeAttachments) {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        return this.factory.createApiCall(matchHandler.getMatch(tournament, matchId, includeAttachments ? 1 : 0));
    }

    /**
     * @see RetrofitMatchHandler#updateMatch(String, int, MatchQuery)
     */
    public ChallongeApiCall<Match> updateMatch(String tournament, int matchId, MatchQuery match) {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        return this.factory.createApiCall(this.matchHandler.updateMatch(tournament, matchId, match));
    }
}
