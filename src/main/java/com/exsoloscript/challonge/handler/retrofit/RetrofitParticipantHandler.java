package com.exsoloscript.challonge.handler.retrofit;

import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.query.ParticipantQuery;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RetrofitParticipantHandler {

    @GET("tournaments/{tournament}/participants.json")
    Call<List<Participant>> getParticipants(@Path("tournament") String tournamentName);

    @GET("tournaments/{tournament}/participants/{participant_id}.json")
    Call<Participant> getParticipant(@Path("tournament") String tournamentName,
                                     @Path("participant_id") int participantId,
                                     @Query("include_matches") int includeMatches);

    @POST("tournaments/{tournament}/participants.json")
    Call<Participant> addParticipant(@Path("tournament") String tournamentName,
                                     @Body ParticipantQuery participant);

    @POST("tournaments/{tournament}/participants/bulk_add.json")
    Call<Participant> bulkAddParticipants(@Path("tournament") String tournamentName,
                                          @Body List<ParticipantQuery> participants);

    @PUT("tournaments/{tournament}/participants/{participant_id}.json")
    Call<Participant> updateParticipant(@Path("tournament") String tournamentName,
                                        @Path("participant_id") int participantId,
                                        @Body ParticipantQuery participant);

    @POST("tournaments/{tournament}/participants/{participant_id}/check_in.json")
    Call<Participant> checkInParticipant(@Path("tournament") String tournamentName,
                                         @Path("participant_id") int participantId);

    @POST("tournaments/{tournament}/participants/{participant_id}/undo_check_in.json")
    Call<Participant> undoParticipantCheckIn(@Path("tournament") String tournamentName,
                                             @Path("participant_id") int participantId);

    @DELETE("tournaments/{tournament}/participants/{participant_id}.json")
    Call<Participant> deleteParticipant(@Path("tournament") String tournamentName,
                                        @Path("participant_id") int participantId);

    @POST("tournaments/{tournament}/participants/randomize.json")
    Call<List<Participant>> randomizeParticipants(@Path("tournament") String tournamentName);
}
