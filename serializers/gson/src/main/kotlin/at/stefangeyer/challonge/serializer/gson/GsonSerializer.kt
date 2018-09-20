package at.stefangeyer.challonge.serializer.gson

import at.stefangeyer.challonge.model.Attachment
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.query.MatchQuery
import at.stefangeyer.challonge.model.query.ParticipantQuery
import at.stefangeyer.challonge.model.query.TournamentQuery
import at.stefangeyer.challonge.model.wrapper.MatchWrapperListWrapper
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapperListWrapper
import at.stefangeyer.challonge.serializer.Serializer
import at.stefangeyer.challonge.serializer.gson.adapter.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type
import java.time.OffsetDateTime

/**
 * Gson serializer gson
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class GsonSerializer(builder: GsonBuilder) : Serializer {

    val gson: Gson

    /**
     * Create serializer with a new GsonBuilder object
     */
    constructor() : this(GsonBuilder())

    init {
        builder.registerTypeAdapter(Tournament::class.java, TournamentAdapter())
        builder.registerTypeAdapter(Participant::class.java, ParticipantAdapter())
        builder.registerTypeAdapter(Match::class.java, MatchAdapter())
        builder.registerTypeAdapter(Attachment::class.java, AttachmentAdapter())

        builder.registerTypeAdapter(TournamentQuery::class.java, TournamentQueryAdapter())
        builder.registerTypeAdapter(ParticipantQuery::class.java, ParticipantQueryAdapter())
        builder.registerTypeAdapter(MatchQuery::class.java, MatchQueryAdapter())

        builder.registerTypeAdapter(ParticipantWrapperListWrapper::class.java, ParticipantWrapperListWrapperAdapter())
        builder.registerTypeAdapter(MatchWrapperListWrapper::class.java, MatchWrapperListWrapperAdapter())

        builder.registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeAdapter())

        this.gson = builder.create()
    }

    override fun serialize(any: Any): String = this.gson.toJson(any)

    override fun <T> deserialize(string: String, type: Type): T = this.gson.fromJson<T>(string, type)
}