package com.exsoloscript.challonge.model;

public class Match extends MatchBase {
    private Object attachmentCount;
    private String createdAt;
    private Object groupId;
    private boolean hasAttachment;
    private int id;
    private String identifier;
    private Object location;
    private Object loserId;
    private int player1Id;
    private boolean player1IsPrereqMatchLoser;
    private Object player1PrereqMatchId;
    private int player2Id;
    private boolean player2IsPrereqMatchLoser;
    private Object player2PrereqMatchId;
    private int round;
    private Object scheduledTime;
    private String startedAt;
    private MatchState state;
    private int tournamentId;
    private Object underwayAt;
    private String updatedAt;
    private String prerequisiteMatchIdsCsv;

    public enum MatchState {
        ALL("all"), PENDING("pending"), OPEN("open"), COMPLETE("complete");

        private String lowerCase;

        MatchState(String lowerCase) {
            this.lowerCase = lowerCase;
        }

        @Override
        public String toString() {
            return this.lowerCase;
        }
    }
}
