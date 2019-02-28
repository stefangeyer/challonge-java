package at.stefangeyer.challonge.model.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticipantQuery {
    private String name;
    private String email;
    private String challongeUsername;
    private Integer seed;
    private String misc;
    private String inviteNameOrEmail;
}
