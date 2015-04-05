package org.kaloz.roulette.domain;

import static org.kaloz.roulette.domain.TestFixtures.VALID_BET_RESULT_PLAYER1;
import static org.kaloz.roulette.domain.TestFixtures.VALID_PLAYER1;
import static org.kaloz.roulette.domain.TestFixtures.VALID_PLAYER_BET_PLAYER1;
import static org.kaloz.roulette.domain.TestFixtures.VALID_PLAYER_POSITION_PLAYER1_WIN;
import static org.kaloz.roulette.domain.TestFixtures.VALID_POCKET_1;
import static org.kaloz.roulette.domain.TestFixtures.VALID_ZERO_PLAYER_POSITION_PLAYER1;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class CroupierUTest {

    @Mock
    private ResultBoard resultBoard;

    @Mock
    private RouletteGame rouletteGame;

    @InjectMocks
    private Croupier testObj;

    @Test(expected = NullPointerException.class)
    public void testNullRouletteGameRegistrationShouldThrowException() {
        // act
        testObj.registerPlayer(null, VALID_ZERO_PLAYER_POSITION_PLAYER1);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPLayerPositionRegistrationShouldThrowException() {
        // act
        testObj.registerPlayer(rouletteGame, null);
    }

    @Test
    public void testValidPlayerRegistration() {
        // act
        testObj.registerPlayer(rouletteGame, VALID_ZERO_PLAYER_POSITION_PLAYER1);

        // assert
        verify(rouletteGame).registerPlayer(VALID_ZERO_PLAYER_POSITION_PLAYER1);
    }

    @Test(expected = PlayerNotRegisteredException.class)
    public void testNotRegisteredPlayerBetShouldThrowException() {
        // setup
        when(rouletteGame.containsPlayers(VALID_PLAYER1)).thenReturn(false);

        // act
        testObj.placeBet(rouletteGame, VALID_PLAYER_BET_PLAYER1);
    }

    @Test(expected = NullPointerException.class)
    public void testNullRouletteGameShouldThrowException() {
        // act
        testObj.placeBet(null, VALID_PLAYER_BET_PLAYER1);
    }

    @Test(expected = NullPointerException.class)
    public void testNullBetShouldThrowException() {
        // act
        testObj.placeBet(rouletteGame, null);
    }

    @Test
    public void testRegisteredPlayerShouldBeAbleToPlaceBet() {
        // setup
        when(rouletteGame.containsPlayers(VALID_PLAYER1)).thenReturn(true);

        // act
        testObj.placeBet(rouletteGame, VALID_PLAYER_BET_PLAYER1);

        // assert
        verify(rouletteGame).placeBet(VALID_PLAYER_BET_PLAYER1);
    }

    @Test(expected = NullPointerException.class)
    public void testNullRouletteGameAnnounceWinningNumberShouldThrowException() {
        // act
        testObj.announceWinningPocket(null, VALID_POCKET_1);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPocketAnnounceWinningNumberShouldThrowException() {
        // act
        testObj.announceWinningPocket(rouletteGame, null);
    }

    @Test
    public void testAnnounceWinningNumber() {
        // setup
        when(rouletteGame.updatePlayerPositions(VALID_POCKET_1)).thenReturn(Lists.newArrayList(VALID_BET_RESULT_PLAYER1));
        when(rouletteGame.playerPositions()).thenReturn(Lists.newArrayList(VALID_PLAYER_POSITION_PLAYER1_WIN));

        // act
        testObj.announceWinningPocket(rouletteGame, VALID_POCKET_1);

        // assert
        verify(rouletteGame).updatePlayerPositions(VALID_POCKET_1);
        verify(resultBoard).updateBetResults(VALID_POCKET_1, Lists.newArrayList(VALID_BET_RESULT_PLAYER1));
        verify(resultBoard).updatePlayerPositions(Lists.newArrayList(VALID_PLAYER_POSITION_PLAYER1_WIN));
    }
}