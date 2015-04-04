package org.kaloz.roulette.app;

import java.util.concurrent.locks.Lock;

import javax.inject.Inject;
import javax.inject.Named;

import org.kaloz.roulette.domain.Croupier;
import org.kaloz.roulette.domain.ResultBoard;
import org.kaloz.roulette.domain.core.PlayerBet;
import org.kaloz.roulette.domain.core.PlayerPosition;
import org.kaloz.roulette.domain.core.Pocket;

@Named
public class RouletteServiceImpl implements RouletteService {

    private final Croupier croupier;
    private final ResultBoard resultBoard;
    private final Lock lock;

    @Inject
    public RouletteServiceImpl(final Croupier croupier, final ResultBoard resultBoard, final Lock lock) {
        this.croupier = croupier;
        this.resultBoard = resultBoard;
        this.lock = lock;
    }

    public void registerPlayer(final PlayerPosition playerPosition) {

        new LockTemplate<Void>(lock) {
            @Override
            public Void apply() {
                croupier.registerPlayer(playerPosition);
                return null;
            }
        }.perform();
    }

    public void placeBet(final PlayerBet playerBet) {

        new LockTemplate<Void>(lock) {
            @Override
            public Void apply() {
                croupier.placeBet(playerBet);
                return null;
            }
        }.perform();
    }

    public void announceWinningPocket(final Pocket pocket) {
        new LockTemplate<Void>(lock) {
            @Override
            public Void apply() {
                resultBoard.updateBetResults(pocket, croupier.announceWinningPocket(pocket));
                resultBoard.updatePlayerPositions(croupier.playerPositions());
                return null;
            }
        }.perform();
    }
}
