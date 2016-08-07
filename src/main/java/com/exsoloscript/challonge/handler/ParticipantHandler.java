package com.exsoloscript.challonge.handler;

import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.ParticipantBase;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ParticipantHandler extends ApiHandler {

    @GET("/tournaments/{tournament}/participants.json")
    Call<List<Participant>> getParticipants(@Path("tournament") String tournamentName);

    @GET("/tournaments/{tournament}/participants/{participant_id}.json")
    Call<Participant> getParticipant(@Path("tournament") String tournamentName, @Path("participant_id") int participantId, @Query("include_matches") boolean includeMatches);

    @POST("/tournaments/{tournament}/participants.json")
    Call<Participant> addParticipant(@Path("tournament") String tournamentName, @Body ParticipantBase participant);

    @POST("/tournaments/{tournament}/participants/bulk_add.json")
    Call<Participant> bulkAddParticipants(@Path("tournament") String tournamentName, @Body List<ParticipantBase> participant);

    @PUT("/tournaments/{tournament}/participants/{participant_id}.json")
    Call<Participant> updateParticipant(@Path("tournament") String tournamentName, @Path("participant_id") int participantId, @Body ParticipantBase participant);

    @POST("/tournaments/{tournament}/participants/{participant_id}/check_in.json")
    Call<Participant> checkInParticipant(@Path("tournament") String tournamentName, @Path("participant_id") int participantId);

    @POST("/tournaments/{tournament}/participants/{participant_id}/undo_check_in.json")
    Call<Participant> undoParticipantCheckIn(@Path("tournament") String tournamentName, @Path("participant_id") int participantId);

    @DELETE("/tournaments/{tournament}/participants/{participant_id}.json")
    Call<Participant> deleteParticipant(@Path("tournament") String tournamentName, @Path("participant_id") int participantId);

    @POST("/tournaments/{tournament}/participants/randomize.json")
    Call<List<Participant>> randomizeParticipants(@Path("tournament") String tournamentName);
}
