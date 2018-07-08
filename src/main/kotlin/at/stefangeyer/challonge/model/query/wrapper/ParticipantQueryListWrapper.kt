package at.stefangeyer.challonge.model.query.wrapper

import at.stefangeyer.challonge.model.query.ParticipantQuery

/**
 * Wrapper for easy JSON serialisation of a List<ParticipantQuery>
 *
 * @author Stefan Geyer
 * @version 2018-07-08
 */
class ParticipantQueryListWrapper(var participants: List<ParticipantQuery>)