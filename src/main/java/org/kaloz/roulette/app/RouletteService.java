package org.kaloz.roulette.app;

import org.kaloz.roulette.domain.core.PlayerBet;
import org.kaloz.roulette.domain.core.PlayerPosition;
import org.kaloz.roulette.domain.core.Pocket;

public interface RouletteService {

    void registerPlayer(final PlayerPosition playerPosition);

    void placeBet(final PlayerBet playerBet);

    public void announceWinningPocket(final Pocket pocket);

}
