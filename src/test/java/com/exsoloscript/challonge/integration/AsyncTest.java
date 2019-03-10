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

package com.exsoloscript.challonge.integration;

import com.exsoloscript.challonge.ChallongeApi;
import com.exsoloscript.challonge.integration.guice.ChallongeTestModule;
import com.exsoloscript.challonge.integration.guice.GuiceJUnitRunner;
import com.exsoloscript.challonge.handler.call.AsyncCallback;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.ParticipantQuery;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.google.inject.Inject;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({ChallongeTestModule.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AsyncTest {

    private Tournament tournament;

    private ChallongeApi challongeApi;

    @Inject
    public void setChallongeApi(ChallongeApi challongeApi) {
        this.challongeApi = challongeApi;
    }

    @Before
    public void setUp() throws Exception {
        this.tournament = this.challongeApi.tournaments().createTournament(
                TournamentQuery.builder()
                        .name("Test")
                        .url("javatesttournament")
                        .build()
        ).sync();
    }

    @Test
    public void testAsyncCallback() throws Exception {
        ParticipantQuery query = ParticipantQuery.builder()
                .name("User1")
                .build();

        CountDownLatch latch = new CountDownLatch(1);

        this.challongeApi.participants().addParticipant(this.tournament.url(), query).async(new AsyncCallback<Participant>() {
            @Override
            public void handleSuccess(Participant response) {
                assertEquals(query.name(), response.name());
                latch.countDown();
            }

            @Override
            public void handleFailure(Throwable exception) {
                // not tested in this case
            }
        });

        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }

    @Test
    public void testAsyncCallbackFailure() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        this.challongeApi.tournaments().createTournament(
                TournamentQuery.builder()
                        .name("Test")
                        .url(this.tournament.url())
                        .build()
        ).async(new AsyncCallback<Tournament>() {
            @Override
            public void handleSuccess(Tournament response) {
                // not tested in this case
            }

            @Override
            public void handleFailure(Throwable throwable) {
                assertTrue(throwable instanceof ChallongeException);
                latch.countDown();
            }
        });

        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }

    @Test
    public void testCallbackSuccess() throws Exception {
        ParticipantQuery query = ParticipantQuery.builder()
                .name("User2")
                .build();

        CountDownLatch latch = new CountDownLatch(1);

        this.challongeApi.participants().addParticipant(this.tournament.url(), query).async((participant) -> {
            assertEquals(query.name(), participant.name());
            latch.countDown();
        }, (throwable) -> {
            // ignored
        });

        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }

    @Test
    public void testCallbackFailure() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        this.challongeApi.tournaments().createTournament(
                TournamentQuery.builder().name("Test").url(this.tournament.url()).build()
        ).async((tournament) -> {
            // ignored
        }, (throwable) -> {
            assertTrue(throwable instanceof ChallongeException);
            latch.countDown();
        });

        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }

    @After
    public void tearDown() throws Exception {
        this.challongeApi.tournaments().deleteTournament(this.tournament.url()).sync();
    }
}
