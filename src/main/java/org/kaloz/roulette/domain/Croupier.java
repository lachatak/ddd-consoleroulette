package org.kaloz.roulette.domain;

import static com.google.common.collect.Iterables.concat;
import static org.kaloz.roulette.domain.core.BetResult.betResult;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Named;

import org.kaloz.roulette.domain.core.BetResult;
import org.kaloz.roulette.domain.core.Player;
import org.kaloz.roulette.domain.core.PlayerBet;
import org.kaloz.roulette.domain.core.PlayerPosition;
import org.kaloz.roulette.domain.core.Pocket;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Named
@Slf4j
public class Croupier {

    private ConcurrentMap<Player, List<PlayerBet>> currentBets = Maps.newConcurrentMap();

    private ConcurrentMap<Player, PlayerPosition> playerPositions = Maps.newConcurrentMap();

    public void registerPlayer(final PlayerPosition playerPosition) {

        log.info("Register player {}", playerPosition);

        currentBets.put(playerPosition.getPlayer(), Lists.<PlayerBet> newArrayList());
        playerPositions.put(playerPosition.getPlayer(), playerPosition);
    }

    public void placeBet(final PlayerBet playerBet) {

        log.info("Place bet {}", playerBet);

        if (!playerPositions.containsKey(playerBet.getPlayer())) {
            throw new PlayerNotRegisteredException(String.format("%s is not registered for this game!", playerBet.getPlayer()));
        }

        currentBets.get(playerBet.getPlayer()).add(playerBet);

        log.info("Current bets {}", currentBets.get(playerBet.getPlayer()));
    }

    public List<BetResult> announceWinningPocket(final Pocket pocket) {

        log.info("Winning number is {}", pocket);

        ImmutableList.Builder<BetResult> builder = ImmutableList.builder();
        for (PlayerBet playerBet : concat(currentBets.values())) {
            builder.add(betResult(playerBet, pocket));
        }
        currentBets.clear();

        ImmutableList<BetResult> betResults = builder.build();
        updateBetPositions(betResults);

        return betResults;
    }

    private void updateBetPositions(final List<BetResult> betResults) {
        for (BetResult betResult : betResults) {
            playerPositions.replace(betResult.getPlayerBet().getPlayer(),
                    playerPositions.get(betResult.getPlayerBet().getPlayer()).add(betResult.getWinnings().getAmount(), betResult.getPlayerBet().getBet().getAmount()));
        }
    }

    public List<PlayerBet> currentBets() {
        return ImmutableList.copyOf(concat(currentBets.values()));
    }

    public List<PlayerPosition> playerPositions() {
        return ImmutableList.copyOf(playerPositions.values());
    }
}
