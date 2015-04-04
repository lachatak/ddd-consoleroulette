package org.kaloz.roulette.domain.core;

import static org.kaloz.roulette.domain.core.Bet.bet;
import static org.kaloz.roulette.domain.core.BetResult.betResult;
import static org.kaloz.roulette.domain.core.Player.player;
import static org.kaloz.roulette.domain.core.PlayerBet.playerBet;
import static org.kaloz.roulette.domain.core.PlayerPosition.playerPosition;
import static org.kaloz.roulette.domain.core.Pocket.pocket;

import java.math.BigDecimal;

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
    public static final String VALID_FIELD_1 = "1";
    public static final String VALID_AMOUNT_1 = "1";
    public static final String VALID_PLAYER_INPUT = "player1 1 1";
    public static final String INVALID_PLAYER_INPUT = "player1 1";
    public static final String VALID_LOAD_INPUT = "player1,0,0";
    public static final String INVALID_LOAD_INPUT_LESS_FIELDS = "player1,1";
    public static final String INVALID_LOAD_INPUT_AMOUNT = "player1,1,c";
    public static final String INVALID_LOAD_INPUT_FIELD = "player1,d,1";

    public static final Pocket VALID_POCKET_1 = pocket(1);
    public static final Pocket VALID_POCKET_10 = pocket(10);

    public static final Bet VALID_BET_1 = bet(VALID_FIELD_1, VALID_AMOUNT_1);
    public static final Player VALID_PLAYER1 = player(VALID_PLAYER_NAME_PLAYER1);
    public static final PlayerBet VALID_PLAYER_BET_PLAYER1 = playerBet(VALID_PLAYER1, VALID_BET_1);
    public static final BetResult VALID_BET_RESULT_PLAYER1 = betResult(VALID_PLAYER_BET_PLAYER1, VALID_POCKET_1);
    public static final PlayerPosition VALID_ZERO_PLAYER_POSITION_PLAYER1 = playerPosition(VALID_PLAYER1);
    public static final PlayerPosition VALID_PLAYER_POSITION_PLAYER1_WIN = playerPosition(VALID_PLAYER1, VALID_TOTAL_WIN_36, VALID_TOTAL_BET_1);

    private TestFixtures() {

    }
}
