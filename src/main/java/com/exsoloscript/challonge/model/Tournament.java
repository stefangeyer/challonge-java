package com.exsoloscript.challonge.model;

import java.util.List;

public class Tournament extends TournamentBase {
    private boolean allowParticipantMatchReporting;
    private boolean anonymousVoting;
    private Object category;
    private Object completedAt;
    private String createdAt;
    private boolean createdByApi;
    private boolean creditCapped;
    private int gameId;
    private boolean groupStagesEnabled;
    private boolean hideSeeds;
    private int id;
    private int maxPredictionsPerUser;
    private boolean notifyUsersWhenMatchesOpen;
    private int participantsCount;
    private int predictionMethod;
    private Object predictionsOpenedAt;
    private int progressMeter;
    private boolean quickAdvance;
    private boolean requireScoreAgreement;
    private String startedAt;
    private Object startedCheckingInAt;
    private TournamentState state;
    private boolean teams;
    private List<String> tieBreaks;
    private String updatedAt;
    private String descriptionSource;
    private String fullChallongeUrl;
    private String liveImageUrl;
    private String signUpUrl;
    private boolean reviewBeforeFinalizing;
    private boolean acceptingPredictions;
    private boolean participantsLocked;
    private String gameName;
    private boolean participantsSwappable;
    private boolean teamConvertable;
    private boolean groupStagesWereStarted;

    // WRONG!
    public enum TournamentState {
        ALL("all"), PENDING("pending"), IN_PROGRESS("in_progress"), ENDED("ended");

        private String lowerCase;

        TournamentState(String lowerCase) {
            this.lowerCase = lowerCase;
        }

        @Override
        public String toString() {
            return this.lowerCase;
        }
    }
}
