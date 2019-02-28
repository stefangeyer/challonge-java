package at.stefangeyer.challonge.model.query.wrapper;

import at.stefangeyer.challonge.model.query.TournamentQuery;

public class TournamentQueryWrapper {

    private TournamentQuery tournament;

    public TournamentQueryWrapper(TournamentQuery tournament) {
        this.tournament = tournament;
    }

    public TournamentQuery getTournament() {
        return this.tournament;
    }
}
