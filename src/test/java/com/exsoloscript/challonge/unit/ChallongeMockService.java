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

package com.exsoloscript.challonge.unit;

import com.exsoloscript.challonge.handler.retrofit.RetrofitTournamentHandler;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.enumeration.query.TournamentQueryState;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChallongeMockService {

    static final class MockTournamentHandler implements RetrofitTournamentHandler {
        private final BehaviorDelegate<RetrofitTournamentHandler> delegate;
        private final List<Tournament> tournaments;

        public MockTournamentHandler(BehaviorDelegate<RetrofitTournamentHandler> delegate) {
            this.delegate = delegate;
            this.tournaments = new ArrayList<>();


        }

        void addTournament(String url, String name) {
            Tournament t = Tournament.builder()
                    .url(url)
                    .name(name)
                    .build();

            this.tournaments.add(t);
        }

        @Override
        public Call<List<Tournament>> getTournaments(TournamentQueryState state, TournamentType type, String createdAfter, String createdBefore, String subdomain) {
            Stream<Tournament> stream = this.tournaments.stream();

            //TODO state
            if (type != null) stream = stream.filter(t -> t.tournamentType() == type);
            if (subdomain != null) stream = stream.filter(t -> t.subdomain().equals(subdomain));
            if (createdAfter != null) {
                OffsetDateTime ca = OffsetDateTime.parse(createdAfter);
                stream = stream.filter(t -> t.createdAt().isAfter(ca));
            }
            if (createdBefore != null) {
                OffsetDateTime cb = OffsetDateTime.parse(createdBefore);
                stream = stream.filter(t -> t.createdAt().isBefore(cb));
            }

            return this.delegate.returningResponse(stream.collect(Collectors.toList())).getTournaments(state, type, createdAfter, createdBefore, subdomain);
        }

        @Override
        public Call<Tournament> getTournament(String tournament, int includeParticipants, int includeMatches) {
            Optional<Tournament> tourn = this.tournaments.stream().filter(t -> t.name().equals(tournament)).findFirst();

            return null;
        }

        @Override
        public Call<Tournament> createTournament(TournamentQuery tournamentData) {
            return null;
        }

        @Override
        public Call<Tournament> updateTournament(String tournament, TournamentQuery tournamentData) {
            return null;
        }

        @Override
        public Call<Tournament> deleteTournament(String tournament) {
            return null;
        }

        @Override
        public Call<Tournament> processCheckIns(String tournament, int includeParticipants, int includeMatches) {
            return null;
        }

        @Override
        public Call<Tournament> abortCheckIn(String tournament, int includeParticipants, int includeMatches) {
            return null;
        }

        @Override
        public Call<Tournament> startTournament(String tournament, int includeParticipants, int includeMatches) {
            return null;
        }

        @Override
        public Call<Tournament> finalizeTournament(String tournament, int includeParticipants, int includeMatches) {
            return null;
        }

        @Override
        public Call<Tournament> resetTournament(String tournament, int includeParticipants, int includeMatches) {
            return null;
        }
    }
}
