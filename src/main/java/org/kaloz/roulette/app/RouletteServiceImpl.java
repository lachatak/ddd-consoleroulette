package org.kaloz.roulette.app;

import javax.inject.Inject;
import javax.inject.Named;

import org.kaloz.roulette.app.lock.LockTemplate;
import org.kaloz.roulette.app.lock.VoidLockAction;
import org.kaloz.roulette.domain.Croupier;
import org.kaloz.roulette.domain.RouletteGame;
import org.kaloz.roulette.domain.core.PlayerBet;
import org.kaloz.roulette.domain.core.Pocket;
import org.kaloz.roulette.domain.core.position.PlayerPosition;

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

        lockTemplate.performVoid(new VoidLockAction() {
            @Override
            protected void apply(RouletteGame rouletteGame) {
                croupier.registerPlayer(rouletteGame, playerPosition);
            }
        });
    }

    public void placeBet(final PlayerBet playerBet) {

        lockTemplate.performVoid(new VoidLockAction() {
            @Override
            protected void apply(RouletteGame rouletteGame) {
                croupier.placeBet(rouletteGame, playerBet);
            }
        });
    }

    public void announceWinningPocket(final Pocket pocket) {

        lockTemplate.performVoid(new VoidLockAction() {
            @Override
            protected void apply(RouletteGame rouletteGame) {
                croupier.announceWinningPocket(rouletteGame, pocket);
            }
        });
    }
}
