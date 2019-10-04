package at.stefangeyer.challonge.serializer.gson.adapter;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Type adapter for the [Participant] class.
 * The received json object is being unpacked.
 *
 * @author Stefan Geyer
 * @version 2018-07-06
 */
public class ParticipantAdapter implements JsonDeserializer<Participant> {

    @Override
    public Participant deserialize(JsonElement src, Type typeOfSource, JsonDeserializationContext context) throws JsonParseException {
        JsonObject e = src.getAsJsonObject().has("participant") ?
                src.getAsJsonObject().get("participant").getAsJsonObject() : src.getAsJsonObject();

        long id = e.get("id").getAsLong();
        OffsetDateTime updatedAt = context.deserialize(e.get("updated_at"), OffsetDateTime.class);
        Long groupId = getOrNull(e, "group_id") != null ? getOrNull(e, "group_id").getAsLong() : null;
        OffsetDateTime createdAt = context.deserialize(e.get("created_at"), OffsetDateTime.class);
        long tournamentId = e.get("tournament_id").getAsLong();
        int seed = e.get("seed").getAsInt();
        String name = getOrNull(e, "name") != null ? getOrNull(e, "name").getAsString() : null;
        String displayNameWithInvitationEmailAddress = getOrNull(e, "display_name_with_invitation_email_address") != null ?
                getOrNull(e, "display_name_with_invitation_email_address").getAsString() : null;
        String misc = getOrNull(e, "misc") != null ? getOrNull(e, "misc").getAsString() : null;
        String challongeUsername = getOrNull(e, "challonge_username") != null ? getOrNull(e, "challonge_username").getAsString() : null;
        String inviteEmail = getOrNull(e, "invite_email") != null ? getOrNull(e, "invite_email").getAsString() : null;
        boolean active = e.get("active").getAsBoolean();
        String attachedParticipatablePortraitUrl = getOrNull(e, "attached_participatable_portrait_url") != null ?
                getOrNull(e, "attached_participatable_portrait_url").getAsString() : null;
        boolean canCheckIn = e.get("can_check_in").getAsBoolean();
        String challongeEmailAddressVerified = getOrNull(e, "challonge_email_address_verified") != null ?
                getOrNull(e, "challonge_email_address_verified").getAsString() : null;
        boolean checkedIn = e.get("checked_in").getAsBoolean();
        OffsetDateTime checkedInAt = context.deserialize(e.get("checked_in_at"), OffsetDateTime.class);
        boolean confirmRemove = e.get("confirm_remove").getAsBoolean();
        String emailHash = getOrNull(e, "email_hash") != null ? getOrNull(e, "email_hash").getAsString() : null;
        Integer finalRank = getOrNull(e, "final_rank") != null ? getOrNull(e, "final_rank").getAsInt() : null;
        String icon = getOrNull(e, "icon") != null ? getOrNull(e, "icon").getAsString() : null;
        Long invitationId = getOrNull(e, "invitation_id") != null ? getOrNull(e, "invitation_id").getAsLong() : null;
        boolean invitationPending = e.get("invitation_pending").getAsBoolean();
        boolean onWaitingList = e.get("on_waiting_list").getAsBoolean();
        boolean participatableOrInvitationAttached = e.get("participatable_or_invitation_attached").getAsBoolean();
        boolean reactivatable = e.get("reactivatable").getAsBoolean();
        boolean removable = e.get("removable").getAsBoolean();
        String displayName = e.get("display_name").getAsString();
        Boolean checkInOpen = e.get("check_in_open").getAsBoolean();
        Boolean hasIrrelevantSeed = e.get("has_irrelevant_seed").getAsBoolean();
        String username = getOrNull(e, "username") != null ? getOrNull(e, "username").getAsString() : null;
        List<Match> matches = context.deserialize(e.get("matches"), new TypeToken<List<Match>>() {
        }.getType());

        return Participant.builder().id(id).updatedAt(updatedAt).groupId(groupId).createdAt(createdAt)
                .tournamentId(tournamentId).seed(seed).name(name)
                .displayNameWithInvitationEmailAddress(displayNameWithInvitationEmailAddress).misc(misc)
                .challongeUsername(challongeUsername).inviteEmail(inviteEmail).active(active)
                .attachedParticipatablePortraitUrl(attachedParticipatablePortraitUrl).canCheckIn(canCheckIn)
                .challongeEmailAddressVerified(challongeEmailAddressVerified).checkedIn(checkedIn)
                .checkedInAt(checkedInAt).confirmRemove(confirmRemove).emailHash(emailHash).finalRank(finalRank)
                .icon(icon).invitationId(invitationId).invitationPending(invitationPending).onWaitingList(onWaitingList)
                .participatableOrInvitationAttached(participatableOrInvitationAttached).reactivatable(reactivatable)
                .displayName(displayName).checkInOpen(checkInOpen).hasIrrelevantSeed(hasIrrelevantSeed)
                .removable(removable).username(username).matches(matches).build();
    }

    private JsonElement getOrNull(JsonObject o, String key) {
        return !o.has(key) || o.get(key).isJsonNull() ? null : o.get(key);
    }
}
