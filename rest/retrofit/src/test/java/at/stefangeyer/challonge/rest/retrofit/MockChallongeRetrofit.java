package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.enumeration.RankedBy;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.MatchQuery;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.model.query.enumeration.GrandFinalsModifier;
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
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class MockChallongeRetrofit implements ChallongeRetrofit {

    private BehaviorDelegate<ChallongeRetrofit> delegate;

    private Random random = new Random();

    private List<Tournament> tournaments = new ArrayList<>(Arrays.asList(
            Tournament.builder().id(10L).url("tourney123")
                    .tournamentType(TournamentType.SINGLE_ELIMINATION)
                    .participants(new ArrayList<>(Arrays.asList(
                            Participant.builder().id(1L).tournamentId(10L).name("Participant 1").matches(new ArrayList<>()).build(),
                            Participant.builder().id(2L).tournamentId(10L).name("Participant 2").matches(new ArrayList<>()).build(),
                            Participant.builder().id(3L).tournamentId(10L).name("Participant 3").matches(new ArrayList<>()).build(),
                            Participant.builder().id(4L).tournamentId(10L).name("Participant 4").matches(new ArrayList<>()).build(),
                            Participant.builder().id(5L).tournamentId(10L).name("Participant 5").matches(new ArrayList<>()).build()
                    ))).matches(new ArrayList<>(Collections.singletonList(
                    Match.builder().id(1L).tournamentId(10L).player1Id(1L).player2Id(2L).attachments(new ArrayList<>(Arrays.asList(
                            Attachment.builder().id(1L).description("Attachment note").build(),
                            Attachment.builder().id(2L).description("Some description").build(),
                            Attachment.builder().id(3L).url("http://some.resource.com/resource").build()
                    ))).build()
            ))).build(),
            Tournament.builder().id(11002L).url("sometourney")
                    .tournamentType(TournamentType.ROUND_ROBIN)
                    .participants(new ArrayList<>())
                    .matches(new ArrayList<>()).build(),
            Tournament.builder().id(45678L).url("tournament45")
                    .tournamentType(TournamentType.DOUBLE_ELIMINATION)
                    .rankedBy(RankedBy.GAME_WINS)
                    .participants(new ArrayList<>())
                    .matches(new ArrayList<>()).build()));

    public MockChallongeRetrofit(BehaviorDelegate<ChallongeRetrofit> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Call<List<TournamentWrapper>> getTournaments(TournamentQueryState state, TournamentType type,
                                                        OffsetDateTime createdAfter, OffsetDateTime createdBefore,
                                                        String subdomain) {
        List<TournamentWrapper> res = this.tournaments.stream()
                .filter(t -> type == null || t.getTournamentType().equals(type))
                .filter(t -> createdAfter == null || t.getCreatedAt().isBefore(createdAfter))
                .filter(t -> createdBefore == null || t.getCreatedAt().isAfter(createdBefore))
                .filter(t -> subdomain == null || t.getSubdomain().equals(subdomain))
                .map(TournamentWrapper::new).collect(Collectors.toList());

        return this.delegate.returningResponse(res).getTournaments(state, type, createdAfter, createdBefore, subdomain);
    }

    @Override
    public Call<TournamentWrapper> getTournament(String tournament, int includeParticipants, int includeMatches) {
        Optional<Tournament> optional = this.tournaments.stream()
                .filter(t -> t.getUrl().equals(tournament) || t.getId().toString().equals(tournament)).findFirst();

        if (!optional.isPresent())
            this.delegate.returning(Calls.failure(new IOException("requested tournament is absent")))
                    .getTournament(tournament, includeParticipants, includeMatches);
        Tournament t = optional.get();

        if (includeParticipants == 0) t.getParticipants().clear();
        if (includeMatches == 0) t.getMatches().clear();

        return this.delegate.returningResponse(new TournamentWrapper(t)).getTournament(tournament, includeParticipants,
                includeMatches);
    }

    @Override
    public Call<TournamentWrapper> createTournament(TournamentQueryWrapper tournamentData) {
        TournamentQuery data = tournamentData.getTournament();

        if (data.getTournamentType() == null || data.getUrl() == null) {
            return this.delegate.returning(Calls.failure(new IOException("Must provide valid query data"))).createTournament(tournamentData);
        }

        Tournament tournament = Tournament.builder().id((long) this.random.nextInt(1000))
                .name(data.getName()).tournamentType(data.getTournamentType())
                .url(data.getUrl()).description(data.getDescription())
                .openSignup(ifNotNull(data.getOpenSignup(), false))
                .holdThirdPlaceMatch(ifNotNull(data.getHoldThirdPlaceMatch(), false))
                .pointsForMatchWin(ifNotNull(data.getPointsForMatchWin(), 0F))
                .pointsForMatchTie(ifNotNull(data.getPointsForMatchTie(), 0F))
                .pointsForGameWin(ifNotNull(data.getPointsForGameWin(), 0F))
                .pointsForGameTie(ifNotNull(data.getPointsForGameTie(), 0F))
                .pointsForBye(ifNotNull(data.getPointsForBye(), 0F))
                .swissRounds(ifNotNull(data.getSwissRounds(), 0))
                .rankedBy(ifNotNull(data.getRankedBy(), RankedBy.MATCH_WINS))
                .roundRobinPointsForGameWin(ifNotNull(data.getRoundRobinPointsForGameWin(), 0F))
                .roundRobinPointsForGameTie(ifNotNull(data.getRoundRobinPointsForGameTie(), 0F))
                .roundRobinPointsForMatchWin(ifNotNull(data.getRoundRobinPointsForMatchWin(), 0F))
                .roundRobinPointsForMatchTie(ifNotNull(data.getRoundRobinPointsForMatchTie(), 0F))
                .acceptAttachments(ifNotNull(data.getAcceptAttachments(), false))
                .hideForum(ifNotNull(data.getHideForum(), false))
                .showRounds(ifNotNull(data.getShowRounds(), false))
                .privateOnly(ifNotNull(data.getPrivateOnly(), false))
                .notifyUsersWhenMatchesOpen(ifNotNull(data.getNotifyUsersWhenMatchesOpen(), false))
                .notifyUsersWhenTheTournamentEnds(ifNotNull(data.getNotifyUsersWhenTheTournamentEnds(), false))
                .sequentialPairings(ifNotNull(data.getSequentialPairings(), false))
                .signupCap(ifNotNull(data.getSignupCap(), 0))
                .startAt(data.getStartAt()).checkInDuration(ifNotNull(data.getCheckInDuration(), 0L))
                .grandFinalsModifier(ifNotNull(data.getGrandFinalsModifier(), GrandFinalsModifier.BLANK))
                .tieBreaks(ifNotNull(data.getTieBreaks(), new ArrayList<>()))
                .createdAt(OffsetDateTime.now()).build();

        this.tournaments.add(tournament);

        return this.delegate.returningResponse(new TournamentWrapper(tournament)).createTournament(tournamentData);
    }

    @Override
    public Call<TournamentWrapper> updateTournament(String tournament, TournamentQueryWrapper tournamentData) {
        Tournament curr = get(tournament);
        TournamentQuery data = tournamentData.getTournament();

        Tournament updated = Tournament.builder().id(curr.getId())
                .name(ifNotNull(data.getName(), curr.getName()))
                .tournamentType(ifNotNull(data.getTournamentType(), curr.getTournamentType()))
                .url(ifNotNull(data.getUrl(), curr.getUrl()))
                .description(ifNotNull(data.getDescription(), curr.getDescription()))
                .openSignup(ifNotNull(data.getOpenSignup(), curr.getOpenSignup()))
                .holdThirdPlaceMatch(ifNotNull(data.getHoldThirdPlaceMatch(), curr.getHoldThirdPlaceMatch()))
                .pointsForMatchWin(ifNotNull(data.getPointsForMatchWin(), curr.getPointsForMatchWin()))
                .pointsForMatchTie(ifNotNull(data.getPointsForMatchTie(), curr.getPointsForMatchTie()))
                .pointsForGameWin(ifNotNull(data.getPointsForGameWin(), curr.getPointsForGameWin()))
                .pointsForGameTie(ifNotNull(data.getPointsForGameTie(), curr.getPointsForGameTie()))
                .pointsForBye(ifNotNull(data.getPointsForBye(), curr.getPointsForBye()))
                .swissRounds(ifNotNull(data.getSwissRounds(), curr.getSwissRounds()))
                .rankedBy(ifNotNull(data.getRankedBy(), curr.getRankedBy()))
                .roundRobinPointsForGameWin(ifNotNull(data.getRoundRobinPointsForGameWin(), curr.getRoundRobinPointsForGameWin()))
                .roundRobinPointsForGameTie(ifNotNull(data.getRoundRobinPointsForGameTie(), curr.getRoundRobinPointsForGameTie()))
                .roundRobinPointsForMatchWin(ifNotNull(data.getRoundRobinPointsForMatchWin(), curr.getRoundRobinPointsForMatchWin()))
                .roundRobinPointsForMatchTie(ifNotNull(data.getRoundRobinPointsForMatchTie(), curr.getPointsForMatchTie()))
                .acceptAttachments(ifNotNull(data.getAcceptAttachments(), curr.getAcceptAttachments()))
                .hideForum(ifNotNull(data.getHideForum(), curr.getHideForum()))
                .showRounds(ifNotNull(data.getShowRounds(), curr.getShowRounds()))
                .privateOnly(ifNotNull(data.getPrivateOnly(), curr.getPrivateOnly()))
                .notifyUsersWhenMatchesOpen(ifNotNull(data.getNotifyUsersWhenMatchesOpen(), curr.getNotifyUsersWhenMatchesOpen()))
                .notifyUsersWhenTheTournamentEnds(ifNotNull(data.getNotifyUsersWhenTheTournamentEnds(), curr.getNotifyUsersWhenTheTournamentEnds()))
                .sequentialPairings(ifNotNull(data.getSequentialPairings(), curr.getSequentialPairings()))
                .signupCap(ifNotNull(data.getSignupCap(), curr.getSignupCap()))
                .startAt(data.getStartAt()).checkInDuration(ifNotNull(data.getCheckInDuration(), curr.getCheckInDuration()))
                .grandFinalsModifier(ifNotNull(data.getGrandFinalsModifier(), curr.getGrandFinalsModifier()))
                .tieBreaks(ifNotNull(data.getTieBreaks(), curr.getTieBreaks()))
                .createdAt(curr.getCreatedAt()).updatedAt(OffsetDateTime.now())
                .participants(curr.getParticipants()).matches(curr.getMatches())
                .build();

        this.tournaments.set(this.tournaments.indexOf(curr), updated);

        return this.delegate.returningResponse(new TournamentWrapper(updated)).updateTournament(tournament, tournamentData);
    }

    @Override
    public Call<TournamentWrapper> deleteTournament(String tournament) {
        Tournament t = get(tournament);
        this.tournaments.remove(t);
        return this.delegate.returningResponse(new TournamentWrapper(t)).deleteTournament(tournament);
    }

    @Override
    public Call<TournamentWrapper> processCheckIns(String tournament, int includeParticipants, int includeMatches) {
        Tournament t = get(tournament);

        if (includeParticipants == 0) t.getParticipants().clear();
        if (includeMatches == 0) t.getMatches().clear();

        // emitted content update

        return this.delegate.returningResponse(new TournamentWrapper(t)).processCheckIns(tournament, includeParticipants, includeMatches);
    }

    @Override
    public Call<TournamentWrapper> abortCheckIn(String tournament, int includeParticipants, int includeMatches) {
        Tournament t = get(tournament);

        // emitted content update

        if (includeParticipants == 0) t.getParticipants().clear();
        if (includeMatches == 0) t.getMatches().clear();

        return this.delegate.returningResponse(new TournamentWrapper(t)).abortCheckIn(tournament, includeParticipants, includeMatches);
    }

    @Override
    public Call<TournamentWrapper> startTournament(String tournament, int includeParticipants, int includeMatches) {
        Tournament t = get(tournament);

        // emitted content update

        if (includeParticipants == 0) t.getParticipants().clear();
        if (includeMatches == 0) t.getMatches().clear();

        return this.delegate.returningResponse(new TournamentWrapper(t)).startTournament(tournament, includeParticipants, includeMatches);
    }

    @Override
    public Call<TournamentWrapper> finalizeTournament(String tournament, int includeParticipants, int includeMatches) {
        Tournament t = get(tournament);

        // emitted content update

        if (includeParticipants == 0) t.getParticipants().clear();
        if (includeMatches == 0) t.getMatches().clear();

        return this.delegate.returningResponse(new TournamentWrapper(t)).finalizeTournament(tournament, includeParticipants, includeMatches);
    }

    @Override
    public Call<TournamentWrapper> resetTournament(String tournament, int includeParticipants, int includeMatches) {
        Tournament t = get(tournament);

        // emitted content update

        if (includeParticipants == 0) t.getParticipants().clear();
        if (includeMatches == 0) t.getMatches().clear();

        return this.delegate.returningResponse(new TournamentWrapper(t)).resetTournament(tournament, includeParticipants, includeMatches);
    }

    @Override
    public Call<TournamentWrapper> openTournamentForPredictions(String tournament, int includeParticipants, int includeMatches) {
        Tournament t = get(tournament);

        // emitted content update

        if (includeParticipants == 0) t.getParticipants().clear();
        if (includeMatches == 0) t.getMatches().clear();

        return this.delegate.returningResponse(new TournamentWrapper(t)).openTournamentForPredictions(tournament,
                includeParticipants, includeMatches);
    }

    @Override
    public Call<List<ParticipantWrapper>> getParticipants(String tournament) {
        Tournament t = get(tournament);
        if (t == null) return Calls.failure(new IllegalArgumentException("no tournament with given identifier"));
        return this.delegate.returningResponse(t.getParticipants().stream().map(ParticipantWrapper::new)
                .collect(Collectors.toList())).getParticipants(tournament);
    }

    @Override
    public Call<ParticipantWrapper> getParticipant(String tournament, long participantId, int includeMatches) {
        Tournament t = get(tournament);
        Participant p = get(tournament, participantId);
        List<Match> matches = new ArrayList<>();

        if (includeMatches == 1) {
            matches = t.getMatches().stream()
                    .filter(m -> m.getPlayer1Id().equals(p.getId()) || m.getPlayer2Id().equals(p.getId()))
                    .collect(Collectors.toList());
        }
        p.setMatches(matches);

        return this.delegate.returningResponse(new ParticipantWrapper(p)).getParticipant(tournament, participantId, includeMatches);
    }

    @Override
    public Call<ParticipantWrapper> addParticipant(String tournament, ParticipantQueryWrapper participant) {
        Tournament t = get(tournament);
        ParticipantQuery data = participant.getParticipant();
        Participant p = Participant.builder().id((long) this.random.nextInt(1000)).name(data.getName())
                .inviteEmail(data.getEmail()).challongeUsername(data.getChallongeUsername()).seed(data.getSeed())
                .misc(data.getMisc()).displayNameWithInvitationEmailAddress(data.getInviteNameOrEmail())
                .matches(new ArrayList<>()).build();

        t.getParticipants().add(p);

        return this.delegate.returningResponse(new ParticipantWrapper(p)).addParticipant(tournament, participant);
    }

    @Override
    public Call<List<ParticipantWrapper>> bulkAddParticipants(String tournament, ParticipantQueryListWrapper participants) {
        Tournament t = get(tournament);
        List<ParticipantWrapper> result = new ArrayList<>();

        for (ParticipantQuery data : participants.getParticipants()) {
            Participant p = Participant.builder().id((long) this.random.nextInt(1000)).name(data.getName())
                    .inviteEmail(data.getEmail()).challongeUsername(data.getChallongeUsername()).seed(data.getSeed())
                    .misc(data.getMisc()).displayNameWithInvitationEmailAddress(data.getInviteNameOrEmail())
                    .matches(new ArrayList<>()).build();

            t.getParticipants().add(p);
            result.add(new ParticipantWrapper(p));
        }

        return this.delegate.returningResponse(result).bulkAddParticipants(tournament, participants);
    }

    @Override
    public Call<ParticipantWrapper> updateParticipant(String tournament, long participantId, ParticipantQueryWrapper participant) {
        ParticipantQuery data = participant.getParticipant();

        Tournament t = get(tournament);
        Participant curr = get(tournament, participantId);

        Participant updated = Participant.builder()
                .name(ifNotNull(data.getName(), curr.getName()))
                .inviteEmail(ifNotNull(data.getEmail(), curr.getInviteEmail()))
                .challongeUsername(ifNotNull(data.getChallongeUsername(), curr.getChallongeUsername()))
                .seed(ifNotNull(data.getSeed(), curr.getSeed()))
                .misc(ifNotNull(data.getMisc(), curr.getMisc()))
                .displayNameWithInvitationEmailAddress(ifNotNull(data.getInviteNameOrEmail(),
                        curr.getDisplayNameWithInvitationEmailAddress()))
                .matches(curr.getMatches())
                .build();

        t.getParticipants().remove(curr);
        t.getParticipants().add(updated);

        return this.delegate.returningResponse(new ParticipantWrapper(updated)).updateParticipant(tournament, participantId, participant);
    }

    @Override
    public Call<ParticipantWrapper> checkInParticipant(String tournament, long participantId) {
        Participant p = get(tournament, participantId);

        // emitted content update

        return this.delegate.returningResponse(new ParticipantWrapper(p)).checkInParticipant(tournament, participantId);
    }

    @Override
    public Call<ParticipantWrapper> undoCheckInParticipant(String tournament, long participantId) {
        Participant p = get(tournament, participantId);

        // emitted content update

        return this.delegate.returningResponse(new ParticipantWrapper(p)).undoCheckInParticipant(tournament, participantId);
    }

    @Override
    public Call<ParticipantWrapper> deleteParticipant(String tournament, long participantId) {
        Tournament t = get(tournament);
        Participant p = get(tournament, participantId);

        t.getParticipants().remove(p);

        return this.delegate.returningResponse(new ParticipantWrapper(p)).deleteParticipant(tournament, participantId);
    }

    @Override
    public Call<List<ParticipantWrapper>> randomizeParticipants(String tournament) {
        List<Participant> participants = get(tournament).getParticipants();

        Collections.shuffle(participants);

        return this.delegate.returningResponse(participants.stream().map(ParticipantWrapper::new).collect(Collectors.toList()))
                .randomizeParticipants(tournament);
    }

    @Override
    public Call<List<MatchWrapper>> getMatches(String tournament, Long pid, MatchState state) {
        Tournament t = get(tournament);
        List<Match> matches = t.getMatches();

        if (pid != null) {
            matches = matches.stream()
                    .filter(m -> m.getPlayer1Id().equals(pid) || m.getPlayer2Id().equals(pid))
                    .collect(Collectors.toList());
        }

        if (state != null) {
            matches = matches.stream().filter(m -> m.getPlayer1Id().equals(pid) || m.getPlayer2Id().equals(pid))
                    .collect(Collectors.toList());
        }

        return this.delegate.returningResponse(matches.stream().map(MatchWrapper::new).collect(Collectors.toList()))
                .getMatches(tournament, pid, state);
    }

    @Override
    public Call<MatchWrapper> getMatch(String tournament, long matchId, int includeAttachments) {
        Tournament t = get(tournament);
        Match m = get(t, matchId);

        if (includeAttachments == 0) {
            m.getAttachments().clear();
        }

        return this.delegate.returningResponse(new MatchWrapper(m)).getMatch(tournament, matchId, includeAttachments);
    }

    @Override
    public Call<MatchWrapper> updateMatch(String tournament, long matchId, MatchQueryWrapper match) {
        Tournament t = get(tournament);
        Match m = get(t, matchId);

        MatchQuery data = match.getMatch();

        Match updated = Match.builder().id(m.getId()).tournamentId(m.getTournamentId())
                .winnerId(ifNotNull(data.getWinnerId(), m.getWinnerId()))
                .scoresCsv(ifNotNull(data.getScoresCsv(), m.getScoresCsv()))
                .build();

        t.getMatches().set(t.getMatches().indexOf(m), updated);

        return this.delegate.returningResponse(new MatchWrapper(updated)).updateMatch(tournament, matchId, match);
    }

    @Override
    public Call<MatchWrapper> reopenMatch(String tournament, long matchId) {
        Tournament t = get(tournament);
        Match m = get(t, matchId);

        // emitted content update

        return this.delegate.returningResponse(new MatchWrapper(m)).reopenMatch(tournament, matchId);
    }

    @Override
    public Call<List<AttachmentWrapper>> getAttachments(String tournament, long matchId) {
        Tournament t = get(tournament);
        Match m = get(t, matchId);

        return this.delegate.returningResponse(m.getAttachments().stream().map(AttachmentWrapper::new)
                .collect(Collectors.toList())).getAttachments(tournament, matchId);
    }

    @Override
    public Call<AttachmentWrapper> getAttachment(String tournament, long matchId, long attachmentId) {
        Tournament t = get(tournament);
        Match m = get(t, matchId);
        Attachment a = get(m, attachmentId);

        return this.delegate.returningResponse(new AttachmentWrapper(a)).getAttachment(tournament, matchId, attachmentId);
    }

    @Override
    public Call<AttachmentWrapper> createAttachment(String tournament, long matchId, MultipartBody.Part asset, MultipartBody.Part url, MultipartBody.Part description) {
        Tournament t = get(tournament);
        Match m = get(t, matchId);

        Attachment a = Attachment.builder()
                .id((long) this.random.nextInt(1000))
                .assetUrl(url != null ? url.toString() : null)
                .description(description != null ? description.toString() : null).build();

        m.getAttachments().add(a);

        return this.delegate.returningResponse(new AttachmentWrapper(a)).createAttachment(tournament, matchId, asset, url, description);
    }

    @Override
    public Call<AttachmentWrapper> updateAttachment(String tournament, long matchId, long attachmentId, MultipartBody.Part asset, MultipartBody.Part url, MultipartBody.Part description) {
        Tournament t = get(tournament);
        Match m = get(t, matchId);
        Attachment a = get(m, attachmentId);

        // emitted content update

        return this.delegate.returningResponse(new AttachmentWrapper(a))
                .updateAttachment(tournament, matchId, attachmentId, asset, url, description);
    }

    @Override
    public Call<AttachmentWrapper> deleteAttachment(String tournament, long matchId, long attachmentId) {
        Tournament t = get(tournament);
        Match m = get(t, matchId);
        Attachment a = get(m, attachmentId);

        m.getAttachments().remove(a);

        return this.delegate.returningResponse(new AttachmentWrapper(a)).deleteAttachment(tournament, matchId, attachmentId);
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    protected <T> T ifNotNull(T t, T def) {
        return t != null ? t : def;
    }

    protected Tournament get(String key) {
        Optional<Tournament> optional = this.tournaments.stream().filter(
                t -> t.getId().toString().equals(key) || t.getUrl().equals(key)).findFirst();
        return optional.orElse(null);
    }

    protected Participant get(String tournament, long id) {
        Optional<Participant> optional = get(tournament).getParticipants().stream().filter(p -> p.getId() == id).findFirst();
        return optional.orElse(null);
    }

    protected Match get(Tournament t, long id) {
        Optional<Match> optional = t.getMatches().stream().filter(m -> m.getId().equals(id)).findFirst();
        return optional.orElse(null);
    }

    protected Attachment get(Match m, long id) {
        Optional<Attachment> optional = m.getAttachments().stream().filter(a -> a.getId().equals(id)).findFirst();
        return optional.orElse(null);
    }
}
