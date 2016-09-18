package com.exsoloscript.challonge.model.query;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Query for creating or updating a participant. This class can be accessed using it's builder.
 *
 * @author EXSolo
 * @version 20160819.1
 */
@Data
@Accessors(fluent = true)
@Builder
public class ParticipantQuery {

    private String name;

    private String email;

    @SerializedName("challonge_username")
    private String challongeUsername;

    private Integer seed;

    private String misc;

    @SerializedName("invite_name_or_email")
    private String inviteNameOrEmail;
}
