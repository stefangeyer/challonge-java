package com.exsoloscript.challonge.model;

public class TournamentBase {
    private String name;
    private TournamentType tournamentType;
    private String url;
    private String subdomain;
    private String description;
    private boolean openSignup;
    private boolean holdThirdPlaceMatch;
    private String ptsForMatchWin;
    private String ptsForMatchTie;
    private String ptsForGameWin;
    private String ptsForGameTie;
    private String ptsForBye;
    private int swissRounds;
    private String rankedBy;
    private String rrPtsForGameWin;
    private String rrPtsForGameTie;
    private String rrPtsForMatchWin;
    private String rrPtsForMatchTie;
    private boolean acceptAttachments;
    private boolean hideForum;
    private boolean showRounds;
    private boolean _private;
    private boolean notifyUsersWhenTheTournamentEnds;
    private boolean sequentialPairings;
    private Object signupCap;
    private Object startAt;
    private Object checkInDuration;
    private GrandFinalsModifier grandFinalsModifier;

    public enum TournamentType {
        SINGLE_ELIMINATION("single_elimination"), DOUBLE_ELIMINATION("double_elimination"), ROUND_ROBIN("round_robin"), SWISS("swiss");

        private String lowerCase;

        TournamentType(String lowerCase) {
            this.lowerCase = lowerCase;
        }

        @Override
        public String toString() {
            return this.lowerCase;
        }
    }

    public enum GrandFinalsModifier {
        BLANK(null), SINGLE_MATCH("single match"), SKIP("skip");

        private String lowerCase;

        GrandFinalsModifier(String lowerCase) {
            this.lowerCase = lowerCase;
        }

        @Override
        public String toString() {
            return this.lowerCase;
        }
    }
}
