package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.enumeration.TournamentQueryState;
import at.stefangeyer.challonge.model.query.wrapper.MatchQueryWrapper;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryListWrapper;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryWrapper;
import at.stefangeyer.challonge.model.query.wrapper.TournamentQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.AttachmentWrapper;
import at.stefangeyer.challonge.model.wrapper.MatchWrapper;
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper;
import at.stefangeyer.challonge.model.wrapper.TournamentWrapper;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.time.OffsetDateTime;
import java.util.List;

public interface ChallongeRetrofit {
    /**
     * Retrieve a set of tournaments created with your account.
     *
     * @param state         only get tournaments with this state
     * @param type          only get tournaments with this type
     * @param createdAfter  get tournaments created after this date
     * @param createdBefore get tournaments created before this date
     * @param subdomain     only get tournaments with this subdomain
     * @return Call
     */
    @GET("tournaments.json")
    Call<List<TournamentWrapper>> getTournaments(@Query("state") TournamentQueryState state,
                                                 @Query("type") TournamentType type,
                                                 @Query("created_after") OffsetDateTime createdAfter,
                                                 @Query("created_before") OffsetDateTime createdBefore,
                                                 @Query("subdomain") String subdomain
    );

    /**
     * Retrieve a single tournament record created with your account.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      include a list of matches in the response
     * @return Call
     */
    @GET("tournaments/{tournament}.json")
    Call<TournamentWrapper> getTournament(@Path("tournament") String tournament,
                                          @Query("include_participants") int includeParticipants,
                                          @Query("include_matches") int includeMatches);

    /**
     * Create a new tournament.
     *
     * @param tournamentData An object with all the necessary information to create the tournament
     * @return Call
     */
    @POST("tournaments.json")
    Call<TournamentWrapper> createTournament(@Body TournamentQueryWrapper tournamentData);

    /**
     * Update a tournament's attributes.
     *
     * @param tournament     Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                       If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                       (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param tournamentData An object with all the necessary information to update the tournament
     * @return Call
     */
    @PUT("tournaments/{tournament}.json")
    Call<TournamentWrapper> updateTournament(@Path("tournament") String tournament,
                                             @Body TournamentQueryWrapper tournamentData);

    /**
     * Deletes a tournament along with all its associated records. There is no undo, so use with care!
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @return Call
     */
    @DELETE("tournaments/{tournament}.json")
    Call<TournamentWrapper> deleteTournament(@Path("tournament") String tournament);

    /**
     * This should be invoked after a tournament's check-in window closes before the tournament is started.
     * <ol>
     * <li>Marks participants who have not checked in as inactive.</li>
     * <li>Moves inactive participants to bottom seeds (ordered by original seed).</li>
     * <li>Transitions the tournament state from 'checking_in' to 'checked_in'</li>
     * </ol>
     * NOTE: Checked in participants on the waiting list will be promoted if slots become available.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/process_check_ins.json")
    Call<TournamentWrapper> processCheckIns(@Path("tournament") String tournament,
                                            @Query("include_participants") int includeParticipants,
                                            @Query("include_matches") int includeMatches);

    /**
     * When your tournament is in a 'checking_in' or 'checked_in' state,
     * there's no way to edit the tournament's start time (start_at) or check-in duration (check_in_duration).
     * You must first abort check-in, then you may edit those attributes.
     * <ol>
     * <li>Makes all participants active and clears their checked_in_at times.</li>
     * <li>Transitions the tournament state from 'checking_in' or 'checked_in' to 'pending'</li>
     * </ol>
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/abort_check_in.json")
    Call<TournamentWrapper> abortCheckIn(@Path("tournament") String tournament,
                                         @Query("include_participants") int includeParticipants,
                                         @Query("include_matches") int includeMatches);

    /**
     * Start a tournament, opening up first round matches for score reporting.
     * The tournament must have at least 2 participants.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/start.json")
    Call<TournamentWrapper> startTournament(@Path("tournament") String tournament,
                                            @Query("include_participants") int includeParticipants,
                                            @Query("include_matches") int includeMatches);

    /**
     * Finalize a tournament that has had all match scores submitted, rendering its results permanent.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/finalize.json")
    Call<TournamentWrapper> finalizeTournament(@Path("tournament") String tournament,
                                               @Query("include_participants") int includeParticipants,
                                               @Query("include_matches") int includeMatches);

    /**
     * Reset a tournament, clearing all of its scores and attachments.
     * You can then add/remove/edit participants before starting the tournament again.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/reset.json")
    Call<TournamentWrapper> resetTournament(@Path("tournament") String tournament,
                                            @Query("include_participants") int includeParticipants,
                                            @Query("include_matches") int includeMatches);

    /**
     * Sets the state of the tournament to start accepting predictions.
     * Your tournament's 'prediction_method' attribute must be set to 1 (exponential scoring) or 2 (linear scoring)
     * to use this option. Note: Once open for predictions, match records will be persisted, so participant additions
     * and removals will no longer be permitted.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/open_for_predictions.json")
    Call<TournamentWrapper> openTournamentForPredictions(@Path("tournament") String tournament,
                                                         @Query("include_participants") int includeParticipants,
                                                         @Query("include_matches") int includeMatches);

    /**
     * Retrieve a tournament's participant list.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @return Call
     */
    @GET("tournaments/{tournament}/participants.json")
    Call<List<ParticipantWrapper>> getParticipants(@Path("tournament") String tournament);

    /**
     * Retrieve a single participant record for a tournament.
     *
     * @param tournament     Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                       If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                       (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId  The participant's unique ID
     * @param includeMatches 0 or 1; Includes an array of associated match records
     * @return Call
     */
    @GET("tournaments/{tournament}/participants/{participant_id}.json")
    Call<ParticipantWrapper> getParticipant(@Path("tournament") String tournament,
                                            @Path("participant_id") long participantId,
                                            @Query("include_matches") int includeMatches);

    /**
     * Add a participant to a tournament (up until it is started).
     *
     * @param tournament  Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                    If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                    (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participant The participant data
     * @return Call
     */
    @POST("tournaments/{tournament}/participants.json")
    Call<ParticipantWrapper> addParticipant(@Path("tournament") String tournament,
                                            @Body ParticipantQueryWrapper participant);

    /**
     * Bulk add participants to a tournament (up until it is started).
     * If an invalid participant is detected, bulk participant creation will halt
     * and any previously added participants (from this API request) will be rolled back.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participants The participant data
     * @return Call
     */
    @POST("tournaments/{tournament}/participants/bulk_add.json")
    Call<List<ParticipantWrapper>> bulkAddParticipants(@Path("tournament") String tournament,
                                                       @Body ParticipantQueryListWrapper participants);

    /**
     * Update the attributes of a tournament participant.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @param participant   The participant data
     * @return Call
     */
    @PUT("tournaments/{tournament}/participants/{participant_id}.json")
    Call<ParticipantWrapper> updateParticipant(@Path("tournament") String tournament,
                                               @Path("participant_id") long participantId,
                                               @Body ParticipantQueryWrapper participant);

    /**
     * Checks a participant in, setting checked_in_at to the current time.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @return Call
     */
    @POST("tournaments/{tournament}/participants/{participant_id}/check_in.json")
    Call<ParticipantWrapper> checkInParticipant(@Path("tournament") String tournament,
                                                @Path("participant_id") long participantId);

    /**
     * Marks a participant as having not checked in, setting checked_in_at to nil.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @return Call
     */
    @POST("tournaments/{tournament}/participants/{participant_id}/undo_check_in.json")
    Call<ParticipantWrapper> undoCheckInParticipant(@Path("tournament") String tournament,
                                                    @Path("participant_id") long participantId);

    /**
     * If the tournament has not started, delete a participant, automatically filling in the abandoned seed number.
     * If tournament is underway, mark a participant inactive, automatically forfeiting his/her remaining matches.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @return Call
     */
    @DELETE("tournaments/{tournament}/participants/{participant_id}.json")
    Call<ParticipantWrapper> deleteParticipant(@Path("tournament") String tournament,
                                               @Path("participant_id") long participantId);

    /**
     * Randomize seeds among participants. Only applicable before a tournament has started.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @return Call
     */
    @POST("tournaments/{tournament}/participants/randomize.json")
    Call<List<ParticipantWrapper>> randomizeParticipants(@Path("tournament") String tournament);

    /**
     * Retrieve a tournament's match list.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId Only retrieve matches that include the specified participant. This parameter is optional. Provide null if you want to skip it.
     * @param state         all (default), pending, open, complete. This parameter is optional. Provide null if you want to skip it.
     * @return Call
     */
    @GET("tournaments/{tournament}/matches.json")
    Call<List<MatchWrapper>> getMatches(@Path("tournament") String tournament,
                                        @Query("participant_id") Long participantId,
                                        @Query("state") MatchState state);

    /**
     * Retrieve a single match record for a tournament.
     *
     * @param tournament         Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId            The match's unique ID
     * @param includeAttachments 0 or 1; include an array of associated attachment records
     * @return Call
     */
    @GET("tournaments/{tournament}/matches/{match_id}.json")
    Call<MatchWrapper> getMatch(@Path("tournament") String tournament,
                                @Path("match_id") long matchId,
                                @Query("include_attachments") int includeAttachments);

    /**
     * Update/submit the score(s) for a match.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param match      The match data
     * @return Call
     */
    @PUT("tournaments/{tournament}/matches/{match_id}.json")
    Call<MatchWrapper> updateMatch(@Path("tournament") String tournament,
                                   @Path("match_id") long matchId,
                                   @Body MatchQueryWrapper match);

    /**
     * Marks a match as underway
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @return Call
     */
    @POST("tournaments/{tournament}/matches/{match_id}/mark_as_underway.json")
    Call<MatchWrapper> markMatchAsUnderway(@Path("tournament") String tournament,
                                           @Path("match_id") long matchId);

    /**
     * Unmarks a match as underway
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @return Call
     */
    @POST("tournaments/{tournament}/matches/{match_id}/unmark_as_underway.json")
    Call<MatchWrapper> unmarkMatchAsUnderway(@Path("tournament") String tournament,
                                             @Path("match_id") long matchId);

    /**
     * Reopens a match that was marked completed, automatically resetting matches that follow it
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @return Call
     */
    @POST("tournaments/{tournament}/matches/{match_id}/reopen.json")
    Call<MatchWrapper> reopenMatch(@Path("tournament") String tournament,
                                   @Path("match_id") long matchId);

    /**
     * Retrieve a match's attachments.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @return Call
     */
    @GET("tournaments/{tournament}/matches/{match_id}/attachments.json")
    Call<List<AttachmentWrapper>> getAttachments(@Path("tournament") String tournament,
                                                 @Path("match_id") long matchId);

    /**
     * Retrieve a single match attachment record.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @return Call
     */
    @GET("tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    Call<AttachmentWrapper> getAttachment(@Path("tournament") String tournament,
                                          @Path("match_id") long matchId,
                                          @Path("attachment_id") long attachmentId);

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the query object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament  Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                    If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                    (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId     The match's unique ID
     * @param asset       A file upload (250KB max, no more than 4 attachments per match). If provided, the url parameter will be ignored.
     * @param url         A web URL
     * @param description Text to describe the file or URL attachment, or this can simply be standalone text.
     * @return Call
     */
    @Multipart
    @POST("tournaments/{tournament}/matches/{match_id}/attachments.json")
    Call<AttachmentWrapper> createAttachment(@Path("tournament") String tournament,
                                             @Path("match_id") long matchId,
                                             @Part MultipartBody.Part asset,
                                             @Part MultipartBody.Part url,
                                             @Part MultipartBody.Part description);

    /**
     * Update the attributes of a match attachment.
     * <p>
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     * </p>
     * At least 1 of the 3 optional parameters (asset, url or description in the query object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @param asset        A file upload (250KB max, no more than 4 attachments per match). If provided, the url parameter will be ignored.
     * @param url          A web URL
     * @param description  Text to describe the file or URL attachment.
     * @return Call
     */
    @Multipart
    @PUT("tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    Call<AttachmentWrapper> updateAttachment(@Path("tournament") String tournament,
                                             @Path("match_id") long matchId,
                                             @Path("attachment_id") long attachmentId,
                                             @Part MultipartBody.Part asset,
                                             @Part MultipartBody.Part url,
                                             @Part MultipartBody.Part description);

    /**
     * Delete a match attachment.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @return Call
     */
    @DELETE("tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    Call<AttachmentWrapper> deleteAttachment(@Path("tournament") String tournament,
                                             @Path("match_id") long matchId,
                                             @Path("attachment_id") long attachmentId);
}
