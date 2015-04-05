package org.kaloz.roulette.domain;

import static org.kaloz.roulette.domain.core.bet.Bet.bet;
import static org.kaloz.roulette.domain.core.result.BetResult.betResult;
import static org.kaloz.roulette.domain.core.Player.player;
import static org.kaloz.roulette.domain.core.bet.PlayerBet.playerBet;
import static org.kaloz.roulette.domain.core.position.PlayerPosition.playerPosition;
import static org.kaloz.roulette.domain.core.Pocket.pocket;
import static org.kaloz.roulette.domain.core.RouletteGameId.rouletteGameId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.kaloz.roulette.domain.RouletteGame;
import org.kaloz.roulette.domain.core.Player;
import org.kaloz.roulette.domain.core.Pocket;
import org.kaloz.roulette.domain.core.RouletteGameId;
import org.kaloz.roulette.domain.core.bet.Bet;
import org.kaloz.roulette.domain.core.bet.PlayerBet;
import org.kaloz.roulette.domain.core.position.PlayerPosition;
import org.kaloz.roulette.domain.core.result.BetResult;
import org.kaloz.roulette.infrastucture.adapters.driven.persistence.data.PersistenceRouletteGame;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

abstract public class TestFixtures {

    public static final BigDecimal VALID_TOTAL_WIN_36 = new BigDecimal(36);
    public static final BigDecimal VALID_TOTAL_WIN_1 = BigDecimal.ONE;
    public static final BigDecimal VALID_TOTAL_WIN_2 = new BigDecimal(2);
    public static final BigDecimal VALID_TOTAL_BET_1 = BigDecimal.ONE;
    public static final BigDecimal VALID_TOTAL_BET_2 = new BigDecimal(2);
    public static final BigDecimal VALID_WINNINGS_2 = new BigDecimal(2);
    public static final BigDecimal VALID_MULTIPLIER_2 = new BigDecimal(2);
    public static final BigDecimal VALID_MULTIPLIER_36 = new BigDecimal(36);

    public static final String VALID_PLAYER_NAME_PLAYER1 = "player1";
    public static final String EMPTY_PLAYER_NAME = "";
    public static final String EMPTY_GAME_ID = "";
    public static final String VALID_GAME_ID = "valid";
    public static final String VALID_FIELD_1 = "1";
    public static final String VALID_AMOUNT_1 = "1";
    public static final String VALID_PLAYER_INPUT = "player1 1 1";
    public static final String INVALID_PLAYER_INPUT = "player1 1";
    public static final String VALID_LOAD_INPUT = "player1,0,0";
    public static final String INVALID_LOAD_INPUT_LESS_FIELDS = "player1,1";
    public static final String INVALID_LOAD_INPUT_AMOUNT = "player1,1,c";
    public static final String INVALID_LOAD_INPUT_FIELD = "player1,d,1";

    public static final String PERSISTENCE_BETS_PLAYER1 = "player1,1,1";
    public static final String PERSISTENCE_POSITION_PLAYER1 = "player1,36,1";

    public static final Pocket VALID_POCKET_1 = pocket(1);
    public static final Pocket VALID_POCKET_10 = pocket(10);

    public static final RouletteGameId VALID_ROULETTE_ID = rouletteGameId(VALID_GAME_ID);
    public static final Bet VALID_BET_1 = bet(VALID_FIELD_1, VALID_AMOUNT_1);
    public static final Player VALID_PLAYER1 = player(VALID_PLAYER_NAME_PLAYER1);
    public static final PlayerBet VALID_PLAYER_BET_PLAYER1 = playerBet(VALID_PLAYER1, VALID_BET_1);
    public static final List<PlayerBet> VALID_PLAYER_BET_LIST_PLAYER1 = Lists.newArrayList(VALID_PLAYER_BET_PLAYER1);
    public static final BetResult VALID_BET_RESULT_PLAYER1 = betResult(VALID_PLAYER_BET_PLAYER1, VALID_POCKET_1);
    public static final List<BetResult> VALID_PLAYER_BET_RESULTS_PLAYER1_LIST = Lists.newArrayList(VALID_BET_RESULT_PLAYER1);
    public static final PlayerPosition VALID_ZERO_PLAYER_POSITION_PLAYER1 = playerPosition(VALID_PLAYER1);
    public static final PlayerPosition VALID_PLAYER_POSITION_PLAYER1_WIN = playerPosition(VALID_PLAYER1, VALID_TOTAL_WIN_36, VALID_TOTAL_BET_1);
    public static final List<PlayerPosition> VALID_PLAYER_POSITION_LIST_PLAYER1_WIN = Lists.newArrayList(VALID_PLAYER_POSITION_PLAYER1_WIN);

    public static final RouletteGame ROULETTE_GAME = rouletteGame();
    public static final PersistenceRouletteGame PERSISTENCE_ROULETTE_GAME = persistencerouletteGame();

    private static RouletteGame rouletteGame() {
        Map<Player, List<PlayerBet>> currentBets = Maps.newHashMap();
        Map<Player, PlayerPosition> playerPositions = Maps.newHashMap();

        currentBets.put(VALID_PLAYER1, VALID_PLAYER_BET_LIST_PLAYER1);
        playerPositions.put(VALID_PLAYER1, VALID_PLAYER_POSITION_PLAYER1_WIN);
        return new RouletteGame(VALID_ROULETTE_ID, currentBets, playerPositions);
    }

    private static PersistenceRouletteGame persistencerouletteGame() {
        List<String> currentBets = Lists.newArrayList(PERSISTENCE_BETS_PLAYER1);
        List<String> playerPositions = Lists.newArrayList(PERSISTENCE_POSITION_PLAYER1);

        PersistenceRouletteGame persistenceRouletteGame = new PersistenceRouletteGame(currentBets, playerPositions);
        persistenceRouletteGame.setId(VALID_GAME_ID);
        return persistenceRouletteGame;
    }

    private TestFixtures() {

    }
}
