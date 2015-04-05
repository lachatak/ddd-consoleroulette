package org.kaloz.roulette.app;

import javax.inject.Inject;
import javax.inject.Named;

import org.kaloz.roulette.app.lock.LockAction;
import org.kaloz.roulette.app.lock.LockTemplate;
import org.kaloz.roulette.domain.Croupier;
import org.kaloz.roulette.domain.RouletteGame;
import org.kaloz.roulette.domain.core.bet.PlayerBet;
import org.kaloz.roulette.domain.core.position.PlayerPosition;
import org.kaloz.roulette.domain.core.Pocket;

@Named
public class RouletteServiceImpl implements RouletteService {

    private final Croupier croupier;
    private final LockTemplate lockTemplate;

    @Inject
    public RouletteServiceImpl(final Croupier croupier, final LockTemplate lockTemplate) {
        this.croupier = croupier;
        this.lockTemplate = lockTemplate;
    }

    public void registerPlayer(final PlayerPosition playerPosition) {

        lockTemplate.perform(new LockAction<Void>() {
            @Override
            protected Void apply(RouletteGame rouletteGame) {
                croupier.registerPlayer(rouletteGame, playerPosition);
                return null;
            }
        });
    }

    public void placeBet(final PlayerBet playerBet) {

        lockTemplate.perform(new LockAction<Void>() {
            @Override
            protected Void apply(RouletteGame rouletteGame) {
                croupier.placeBet(rouletteGame, playerBet);
                return null;
            }
        });
    }

    public void announceWinningPocket(final Pocket pocket) {

        lockTemplate.perform(new LockAction<Void>() {
            @Override
            protected Void apply(RouletteGame rouletteGame) {
                croupier.announceWinningPocket(rouletteGame, pocket);
                return null;
            }
        });
    }
}
