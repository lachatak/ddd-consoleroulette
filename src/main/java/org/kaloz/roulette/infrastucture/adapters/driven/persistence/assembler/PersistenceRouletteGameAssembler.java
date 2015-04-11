package org.kaloz.roulette.infrastucture.adapters.driven.persistence.assembler;

import static org.kaloz.roulette.domain.core.bet.Bet.bet;
import static org.kaloz.roulette.domain.core.Player.player;
import static org.kaloz.roulette.domain.core.PlayerBet.playerBet;
import static org.kaloz.roulette.domain.core.position.PlayerPosition.playerPosition;
import static org.kaloz.roulette.domain.core.RouletteGameId.rouletteGameId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Named;

import org.kaloz.roulette.domain.RouletteGame;
import org.kaloz.roulette.domain.core.Player;
import org.kaloz.roulette.domain.core.PlayerBet;
import org.kaloz.roulette.domain.core.position.PlayerPosition;
import org.kaloz.roulette.infrastucture.adapters.driven.persistence.data.PersistenceRouletteGame;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@Named
public class PersistenceRouletteGameAssembler {

    private static final String PERSISTENCE_FORMAT = "%s,%s,%s";
    private static final String PERSISTENCE_FIELD_SEPARATOR = ",";

    public PersistenceRouletteGame assemble(final RouletteGame rouletteGame) {
        PersistenceRouletteGame persistenceRouletteGame = new PersistenceRouletteGame(persistenceCurrentBets(rouletteGame), persistencePlayerPositions(rouletteGame));
        if (rouletteGame.getRouletteGameId() != null) {
            persistenceRouletteGame.setId(rouletteGame.getRouletteGameId().getId());
        }
        return persistenceRouletteGame;
    }

    private List<String> persistencePlayerPositions(RouletteGame rouletteGame) {
        List<String> persistencePlayerPositions = Lists.newArrayList();
        for (PlayerPosition playerPosition : rouletteGame.playerPositions()) {
            persistencePlayerPositions.add(String.format(PERSISTENCE_FORMAT, playerPosition.getPlayer().getName(), playerPosition.getTotalWin().getAmount(), playerPosition
                    .getTotalBet().getAmount()));
        }
        return persistencePlayerPositions;
    }

    private List<String> persistenceCurrentBets(RouletteGame rouletteGame) {
        List<String> persistenceCurrentBets = Lists.newArrayList();
        for (PlayerBet playerBet : rouletteGame.currentBets()) {
            persistenceCurrentBets.add(String.format(PERSISTENCE_FORMAT, playerBet.getPlayer().getName(), playerBet.getBet().getField().bet(), playerBet.getBet().getAmount()));
        }
        return persistenceCurrentBets;
    }

    public RouletteGame disassemble(final PersistenceRouletteGame persistenceRouletteGame) {

        Map<Player, PlayerPosition> playerPositions = playerPositions(persistenceRouletteGame);

        Map<Player, List<PlayerBet>> currentBets = currentBets(persistenceRouletteGame);

        Set<Player> missingPlayers = Sets.difference(playerPositions.keySet(), currentBets.keySet());
        for (Player player : missingPlayers) {
            currentBets.put(player, Lists.<PlayerBet> newArrayList());
        }

        return new RouletteGame(rouletteGameId(persistenceRouletteGame.getId()), currentBets, playerPositions);
    }

    private Map<Player, List<PlayerBet>> currentBets(PersistenceRouletteGame persistenceRouletteGame) {
        Map<Player, List<PlayerBet>> currentBets = Maps.newHashMap();
        for (String playerBetString : persistenceRouletteGame.getCurrentBets()) {

            String[] playerBetTokens = playerBetString.split(PERSISTENCE_FIELD_SEPARATOR);
            PlayerBet playerBet = playerBet(player(playerBetTokens[0]), bet(playerBetTokens[1], playerBetTokens[2]));

            if (!currentBets.containsKey(playerBet.getPlayer())) {
                currentBets.put(playerBet.getPlayer(), Lists.<PlayerBet> newArrayList());
            }
            currentBets.get(playerBet.getPlayer()).add(playerBet);
        }
        return currentBets;
    }

    private Map<Player, PlayerPosition> playerPositions(PersistenceRouletteGame persistenceRouletteGame) {
        Map<Player, PlayerPosition> playerPositions = Maps.newHashMap();
        for (String playerPositionString : persistenceRouletteGame.getPlayerPositions()) {
            String[] playerPositionTokens = playerPositionString.split(PERSISTENCE_FIELD_SEPARATOR);
            PlayerPosition playerPosition = playerPosition(player(playerPositionTokens[0]), new BigDecimal(playerPositionTokens[1]), new BigDecimal(playerPositionTokens[2]));

            playerPositions.put(playerPosition.getPlayer(), playerPosition);
        }
        return playerPositions;
    }
}
