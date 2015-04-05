package org.kaloz.roulette.domain;

import static com.google.common.collect.Iterables.concat;
import static org.kaloz.roulette.domain.core.result.BetResult.betResult;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

import org.kaloz.roulette.domain.core.result.BetResult;
import org.kaloz.roulette.domain.core.Player;
import org.kaloz.roulette.domain.core.bet.PlayerBet;
import org.kaloz.roulette.domain.core.position.PlayerPosition;
import org.kaloz.roulette.domain.core.Pocket;
import org.kaloz.roulette.domain.core.RouletteGameId;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@ToString
@EqualsAndHashCode(of = "rouletteGameId")
public class RouletteGame {

    @Getter
    private final RouletteGameId rouletteGameId;

    private final Map<Player, List<PlayerBet>> currentBets;

    private final Map<Player, PlayerPosition> playerPositions;

    public RouletteGame(final RouletteGameId rouletteGameId, final Map<Player, List<PlayerBet>> currentBets, final Map<Player, PlayerPosition> playerPositions) {
        this.rouletteGameId = rouletteGameId;
        this.currentBets = currentBets;
        this.playerPositions = playerPositions;
    }

    public RouletteGame() {
        this(null, Maps.<Player, List<PlayerBet>> newHashMap(), Maps.<Player, PlayerPosition> newHashMap());
    }

    public void registerPlayer(final PlayerPosition playerPosition) {
        currentBets.put(playerPosition.getPlayer(), Lists.<PlayerBet> newArrayList());
        playerPositions.put(playerPosition.getPlayer(), playerPosition);
    }

    public void placeBet(final PlayerBet playerBet) {
        currentBets.get(playerBet.getPlayer()).add(playerBet);
    }

    public List<BetResult> updatePlayerPositions(final Pocket pocket) {

        ImmutableList.Builder<BetResult> builder = ImmutableList.builder();
        for (PlayerBet playerBet : concat(currentBets.values())) {
            builder.add(betResult(playerBet, pocket));
        }
        ImmutableList<BetResult> betResults = builder.build();

        currentBets.clear();

        for (BetResult betResult : betResults) {
            playerPositions.put(betResult.getPlayerBet().getPlayer(),
                    playerPositions.get(betResult.getPlayerBet().getPlayer()).add(betResult.getWinnings().getAmount(), betResult.getPlayerBet().getBet().getAmount()));
        }

        return betResults;
    }

    public List<PlayerPosition> playerPositions() {
        return ImmutableList.copyOf(playerPositions.values());
    }

    public List<PlayerBet> currentBets() {
        return ImmutableList.copyOf(concat(currentBets.values()));
    }

    public boolean containsPlayers(final Player player) {
        return playerPositions.containsKey(player);
    }

}