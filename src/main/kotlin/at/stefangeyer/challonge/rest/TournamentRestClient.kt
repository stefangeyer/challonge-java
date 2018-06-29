package at.stefangeyer.challonge.rest

import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.enumeration.query.TournamentQueryState

/**
 * Tournament Rest Client Definition
 *
 * @author Stefan Geyer
 * @version 2018-06-29
 */
interface TournamentRestClient {

    /**
     *
     */
    fun getTournaments(state: TournamentQueryState, type: TournamentType, createdAfter: String,
                       createdBefore: String, subdomain: String): List<Tournament>
}