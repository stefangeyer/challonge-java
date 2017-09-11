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

package com.exsoloscript.challonge;

import com.exsoloscript.challonge.handler.call.AttachmentHandler;
import com.exsoloscript.challonge.handler.call.MatchHandler;
import com.exsoloscript.challonge.handler.call.ParticipantHandler;
import com.exsoloscript.challonge.handler.call.TournamentHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * A collection of all the available handlers.
 * For more details see the individual handlers.
 *
 * @author EXSolo
 * @version 20160822.1
 */
@Singleton
public class ChallongeApi {

    @Inject
    private TournamentHandler tournaments;

    @Inject
    private ParticipantHandler participants;

    @Inject
    private MatchHandler matches;

    @Inject
    private AttachmentHandler attachments;

    /**
     * @return TournamentHandler
     */
    public TournamentHandler tournaments() {
        return tournaments;
    }

    /**
     * @return ParticipantHandler
     */
    public ParticipantHandler participants() {
        return participants;
    }

    /**
     * @return MatchHandler
     */
    public MatchHandler matches() {
        return matches;
    }

    /**
     * @return AttachmentHandler
     */
    public AttachmentHandler attachments() {
        return attachments;
    }
}