package com.exsoloscript.challonge.handler;

import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.ParticipantBase;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface ParticipantHandler extends ApiHandler {

    @GET("/tournaments/{tournament}/participants.json")
    Call<List<Participant>> getParticipants(@Path("tournament") String tournamentName);

    @POST("/tournaments/{tournament}/participants.json")
    Call<Participant> addParticipant(@Path("tournament") String tournamentName, @Body ParticipantBase participant);

    @POST("/tournaments/{tournament}/participants/bulk_add.json")
    Call<Participant> bulkAddParticipants(@Path("tournament") String tournamentName, @Body List<ParticipantBase> participant);

}
