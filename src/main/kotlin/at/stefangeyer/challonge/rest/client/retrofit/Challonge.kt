package at.stefangeyer.challonge.rest.client.retrofit

import at.stefangeyer.challonge.model.Attachment
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.MatchState
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.enumeration.query.TournamentQueryState
import at.stefangeyer.challonge.model.query.MatchQuery
import at.stefangeyer.challonge.model.query.ParticipantQuery
import at.stefangeyer.challonge.model.query.TournamentQuery
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.time.OffsetDateTime

interface Challonge {

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
    fun getTournaments(@Query("state") state: TournamentQueryState?,
                       @Query("type") type: TournamentType?,
                       @Query("created_after") createdAfter: OffsetDateTime?,
                       @Query("created_before") createdBefore: OffsetDateTime?,
                       @Query("subdomain") subdomain: String?
    ): Call<List<Tournament>>

    /**
     * Retrieve a single tournament record created with your account.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      include a list of matches in the response
     * @return Call
     */
    @GET("tournaments/{tournament}.json")
    fun getTournament(@Path("tournament") tournament: String,
                      @Query("include_participants") includeParticipants: Int,
                      @Query("include_matches") includeMatches: Int): Call<Tournament>

    /**
     * Create a new tournament.
     *
     * @param tournamentData An object with all the necessary information to create the tournament
     * @return Call
     */
    @POST("tournaments.json")
    fun createTournament(@Body tournamentData: TournamentQuery): Call<Tournament>

    /**
     * Update a tournament's attributes.
     *
     * @param tournament     Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param tournamentData An object with all the necessary information to update the tournament
     * @return Call
     */
    @PUT("tournaments/{tournament}.json")
    fun updateTournament(@Path("tournament") tournament: String,
                         @Body tournamentData: TournamentQuery): Call<Tournament>

    /**
     * Deletes a tournament along with all its associated records. There is no undo, so use with care!
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @return Call
     */
    @DELETE("tournaments/{tournament}.json")
    fun deleteTournament(@Path("tournament") tournament: String): Call<Tournament>

    /**
     * This should be invoked after a tournament's check-in window closes before the tournament is started.
     *
     *
     *
     *  1. Marks participants who have not checked in as inactive.
     *  1. Moves inactive participants to bottom seeds (ordered by original seed).
     *  1. Transitions the tournament state from 'checking_in' to 'checked_in'
     *
     *
     *
     * NOTE: Checked in participants on the waiting list will be promoted if slots become available.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/process_check_ins.json")
    fun processCheckIns(@Path("tournament") tournament: String,
                        @Query("include_participants") includeParticipants: Int,
                        @Query("include_matches") includeMatches: Int): Call<Tournament>

    /**
     * When your tournament is in a 'checking_in' or 'checked_in' state,
     * there's no way to edit the tournament's start time (start_at) or check-in duration (check_in_duration).
     * You must first abort check-in, then you may edit those attributes.
     *
     *
     *
     *  1. Makes all participants active and clears their checked_in_at times.
     *  1. Transitions the tournament state from 'checking_in' or 'checked_in' to 'pending'
     *
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/abort_check_in.json")
    fun abortCheckIn(@Path("tournament") tournament: String,
                     @Query("include_participants") includeParticipants: Int,
                     @Query("include_matches") includeMatches: Int): Call<Tournament>

    /**
     * Start a tournament, opening up first round matches for score reporting.
     * The tournament must have at least 2 participants.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/start.json")
    fun startTournament(@Path("tournament") tournament: String,
                        @Query("include_participants") includeParticipants: Int,
                        @Query("include_matches") includeMatches: Int): Call<Tournament>

    /**
     * Finalize a tournament that has had all match scores submitted, rendering its results permanent.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/finalize.json")
    fun finalizeTournament(@Path("tournament") tournament: String,
                           @Query("include_participants") includeParticipants: Int,
                           @Query("include_matches") includeMatches: Int): Call<Tournament>

    /**
     * Reset a tournament, clearing all of its scores and attachments.
     * You can then add/remove/edit participants before starting the tournament again.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/reset.json")
    fun resetTournament(@Path("tournament") tournament: String,
                        @Query("include_participants") includeParticipants: Int,
                        @Query("include_matches") includeMatches: Int): Call<Tournament>

    /**
     * Sets the state of the tournament to start accepting predictions.
     * Your tournament's 'prediction_method' attribute must be set to 1 (exponential scoring) or 2 (linear scoring)
     * to use this option. Note: Once open for predictions, match records will be persisted, so participant additions
     * and removals will no longer be permitted.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/open_for_predictions.json")
    fun openTournamentForPredictions(@Path("tournament") tournament: String,
                                     @Query("include_participants") includeParticipants: Int,
                                     @Query("include_matches") includeMatches: Int): Call<Tournament>

    /**
     * Retrieve a tournament's participant list.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @return Call
     */
    @GET("tournaments/{tournament}/participants.json")
    fun getParticipants(@Path("tournament") tournament: String): Call<List<Participant>>

    /**
     * Retrieve a single participant record for a tournament.
     *
     * @param tournament     Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId  The participant's unique ID
     * @param includeMatches 0 or 1; Includes an array of associated match records
     * @return Call
     */
    @GET("tournaments/{tournament}/participants/{participant_id}.json")
    fun getParticipant(@Path("tournament") tournament: String,
                       @Path("participant_id") participantId: Long,
                       @Query("include_matches") includeMatches: Int): Call<Participant>

    /**
     * Add a participant to a tournament (up until it is started).
     *
     * @param tournament  Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participant The participant data
     * @return Call
     */
    @POST("tournaments/{tournament}/participants.json")
    fun addParticipant(@Path("tournament") tournament: String,
                       @Body participant: ParticipantQuery): Call<Participant>

    /**
     * Bulk add participants to a tournament (up until it is started).
     * If an invalid participant is detected, bulk participant creation will halt
     * and any previously added participants (from this API request) will be rolled back.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participants The participant data
     * @return Call
     */
    @POST("tournaments/{tournament}/participants/bulk_add.json")
    fun bulkAddParticipants(@Path("tournament") tournament: String,
                            @Body participants: List<ParticipantQuery>): Call<List<Participant>>

    /**
     * Update the attributes of a tournament participant.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @param participant   The participant data
     * @return Call
     */
    @PUT("tournaments/{tournament}/participants/{participant_id}.json")
    fun updateParticipant(@Path("tournament") tournament: String,
                          @Path("participant_id") participantId: Long,
                          @Body participant: ParticipantQuery): Call<Participant>

    /**
     * Checks a participant in, setting checked_in_at to the current time.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @return Call
     */
    @POST("tournaments/{tournament}/participants/{participant_id}/check_in.json")
    fun checkInParticipant(@Path("tournament") tournament: String,
                           @Path("participant_id") participantId: Long): Call<Participant>

    /**
     * Marks a participant as having not checked in, setting checked_in_at to nil.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @return Call
     */
    @POST("tournaments/{tournament}/participants/{participant_id}/undo_check_in.json")
    fun undoCheckInParticipant(@Path("tournament") tournament: String,
                               @Path("participant_id") participantId: Long): Call<Participant>

    /**
     * If the tournament has not started, delete a participant, automatically filling in the abandoned seed number.
     * If tournament is underway, mark a participant inactive, automatically forfeiting his/her remaining matches.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @return Call
     */
    @DELETE("tournaments/{tournament}/participants/{participant_id}.json")
    fun deleteParticipant(@Path("tournament") tournament: String,
                          @Path("participant_id") participantId: Long): Call<Participant>

    /**
     * Randomize seeds among participants. Only applicable before a tournament has started.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @return Call
     */
    @POST("tournaments/{tournament}/participants/randomize.json")
    fun randomizeParticipants(@Path("tournament") tournament: String): Call<List<Participant>>


    /**
     * Retrieve a tournament's match list.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId Only retrieve matches that include the specified participant. This parameter is optional. Provide null if you want to skip it.
     * @param state         all (default), pending, open, complete. This parameter is optional. Provide null if you want to skip it.
     * @return Call
     */
    @GET("tournaments/{tournament}/matches.json")
    fun getMatches(@Path("tournament") tournament: String,
                   @Query("participant_id") participantId: Long?,
                   @Query("state") state: MatchState?): Call<List<Match>>

    /**
     * Retrieve a single match record for a tournament.
     *
     * @param tournament         Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId            The match's unique ID
     * @param includeAttachments 0 or 1; include an array of associated attachment records
     * @return Call
     */
    @GET("tournaments/{tournament}/matches/{match_id}.json")
    fun getMatch(@Path("tournament") tournament: String,
                 @Path("match_id") matchId: Long,
                 @Query("include_attachments") includeAttachments: Int): Call<Match>

    /**
     * Update/submit the score(s) for a match.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param match      The match data
     * @return Call
     */
    @PUT("tournaments/{tournament}/matches/{match_id}.json")
    fun updateMatch(@Path("tournament") tournament: String,
                    @Path("match_id") matchId: Long,
                    @Body match: MatchQuery): Call<Match>

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
    fun reopenMatch(@Path("tournament") tournament: String,
                    @Path("match_id") matchId: Long): Call<Match>

    /**
     * Retrieve a match's attachments.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @return Call
     */
    @GET("tournaments/{tournament}/matches/{match_id}/attachments.json")
    fun getAttachments(@Path("tournament") tournament: String,
                       @Path("match_id") matchId: Long): Call<List<Attachment>>

    /**
     * Retrieve a single match attachment record.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @return Call
     */
    @GET("tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    fun getAttachment(@Path("tournament") tournament: String,
                      @Path("match_id") matchId: Long,
                      @Path("attachment_id") attachmentId: Long): Call<Attachment>

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     *
     *
     * At least 1 of the 3 optional parameters (asset, url or description in the query object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament  Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId     The match's unique ID
     * @param asset       A file upload (250KB max, no more than 4 attachments per match). If provided, the url parameter will be ignored.
     * @param url         A web URL
     * @param description Text to describe the file or URL attachment, or this can simply be standalone text.
     * @return Call
     */
    @Multipart
    @POST("tournaments/{tournament}/matches/{match_id}/attachments.json")
    fun createAttachment(@Path("tournament") tournament: String,
                         @Path("match_id") matchId: Long,
                         @Part asset: MultipartBody.Part?,
                         @Part url: MultipartBody.Part?,
                         @Part description: MultipartBody.Part?): Call<Attachment>

    /**
     * Update the attributes of a match attachment.
     *
     *
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     *
     * At least 1 of the 3 optional parameters (asset, url or description in the query object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @param asset        A file upload (250KB max, no more than 4 attachments per match). If provided, the url parameter will be ignored.
     * @param url          A web URL
     * @param description  Text to describe the file or URL attachment.
     * @return Call
     */
    @Multipart
    @PUT("tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    fun updateAttachment(@Path("tournament") tournament: String,
                         @Path("match_id") matchId: Long,
                         @Path("attachment_id") attachmentId: Long,
                         @Part asset: MultipartBody.Part?,
                         @Part url: MultipartBody.Part?,
                         @Part description: MultipartBody.Part?): Call<Attachment>

    /**
     * Delete a match attachment.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     * If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     * (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @return Call
     */
    @DELETE("tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    fun deleteAttachment(@Path("tournament") tournament: String,
                         @Path("match_id") matchId: Long,
                         @Path("attachment_id") attachmentId: Long): Call<Attachment>
}