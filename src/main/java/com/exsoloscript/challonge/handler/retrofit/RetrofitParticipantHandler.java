package com.exsoloscript.challonge.handler.retrofit;

import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.query.ParticipantQuery;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Participant related API bindings
 *
 * @author EXSolo
 * @version 20160822.1
 */
public interface RetrofitParticipantHandler {

    /**
     * Retrieve a tournament's participant list.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @return Call
     */
    @GET("tournaments/{tournament}/participants.json")
    Call<List<Participant>> getParticipants(@Path("tournament") String tournament);

    /**
     * Retrieve a single participant record for a tournament.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @param includeMatches 0 or 1; Includes an array of associated match records
     * @return Call
     */
    @GET("tournaments/{tournament}/participants/{participant_id}.json")
    Call<Participant> getParticipant(@Path("tournament") String tournament,
                                     @Path("participant_id") int participantId,
                                     @Query("include_matches") int includeMatches);

    /**
     * Add a participant to a tournament (up until it is started).
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participant The participant data
     * @return Call
     */
    @POST("tournaments/{tournament}/participants.json")
    Call<Participant> addParticipant(@Path("tournament") String tournament,
                                     @Body ParticipantQuery participant);

    /**
     * Bulk add participants to a tournament (up until it is started).
     * If an invalid participant is detected, bulk participant creation will halt
     * and any previously added participants (from this API request) will be rolled back.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participants The participant data
     * @return Call
     */
    @POST("tournaments/{tournament}/participants/bulk_add.json")
    Call<Participant> bulkAddParticipants(@Path("tournament") String tournament,
                                          @Body List<ParticipantQuery> participants);

    /**
     * Update the attributes of a tournament participant.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @param participant The participant data
     * @return Call
     */
    @PUT("tournaments/{tournament}/participants/{participant_id}.json")
    Call<Participant> updateParticipant(@Path("tournament") String tournament,
                                        @Path("participant_id") int participantId,
                                        @Body ParticipantQuery participant);

    /**
     * Checks a participant in, setting checked_in_at to the current time.
     *
     * @param tournament 	Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @return Call
     */
    @POST("tournaments/{tournament}/participants/{participant_id}/check_in.json")
    Call<Participant> checkInParticipant(@Path("tournament") String tournament,
                                         @Path("participant_id") int participantId);

    /**
     * Marks a participant as having not checked in, setting checked_in_at to nil.
     *
     * @param tournament 	Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @return Call
     */
    @POST("tournaments/{tournament}/participants/{participant_id}/undo_check_in.json")
    Call<Participant> undoParticipantCheckIn(@Path("tournament") String tournament,
                                             @Path("participant_id") int participantId);

    /**
     * If the tournament has not started, delete a participant, automatically filling in the abandoned seed number.
     * If tournament is underway, mark a participant inactive, automatically forfeiting his/her remaining matches.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @return Call
     */
    @DELETE("tournaments/{tournament}/participants/{participant_id}.json")
    Call<Participant> deleteParticipant(@Path("tournament") String tournament,
                                        @Path("participant_id") int participantId);

    /**
     * Randomize seeds among participants. Only applicable before a tournament has started.
     *
     * @param tournament 	Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @return Call
     */
    @POST("tournaments/{tournament}/participants/randomize.json")
    Call<List<Participant>> randomizeParticipants(@Path("tournament") String tournament);
}
