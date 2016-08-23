package com.exsoloscript.challonge.handler.call;

import com.exsoloscript.challonge.handler.retrofit.RetrofitMatchHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.enumeration.MatchState;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.MatchQuery;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.util.List;

/**
 * Accessible API handler for matches.
 *
 * @author EXSolo
 * @version 20160822.1
 */
@Singleton
public class MatchHandler {

    private RetrofitMatchHandler matchHandler;
    private RetrofitChallongeApiCallFactory factory;

    @Inject
    MatchHandler(RetrofitServiceProvider provider, RetrofitChallongeApiCallFactory factory) {
        this.matchHandler = provider.createService(RetrofitMatchHandler.class);
        this.factory = factory;
    }

    /**
     * @see RetrofitMatchHandler#getMatches(String, int, MatchState)
     */
    public ChallongeApiCall<List<Match>> getMatches(String tournament, int participantId, MatchState state) throws IOException, ChallongeException {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        return this.factory.createApiCall(this.matchHandler.getMatches(tournament, participantId, state));
    }

    /**
     * @see RetrofitMatchHandler#getMatch(String, int, int)
     */
    public ChallongeApiCall<Match> getMatch(String tournament, int matchId, boolean includeAttachments) throws IOException, ChallongeException {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        Validate.notNull(matchId, "Match id is required");
        return this.factory.createApiCall(matchHandler.getMatch(tournament, matchId, includeAttachments ? 1 : 0));
    }

    /**
     * @see RetrofitMatchHandler#updateMatch(String, int, MatchQuery)
     */
    public ChallongeApiCall<Match> updateMatch(String tournament, int matchId, MatchQuery match) throws IOException, ChallongeException {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        Validate.notNull(matchId, "Match id is required");
        return this.factory.createApiCall(this.matchHandler.updateMatch(tournament, matchId, match));
    }
}
