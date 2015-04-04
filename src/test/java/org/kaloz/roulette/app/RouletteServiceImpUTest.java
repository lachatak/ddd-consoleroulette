package org.kaloz.roulette.app;

import static org.kaloz.roulette.domain.core.TestFixtures.VALID_PLAYER_BET_PLAYER1;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_POCKET_1;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_ZERO_PLAYER_POSITION_PLAYER1;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kaloz.roulette.domain.Croupier;
import org.kaloz.roulette.domain.ResultBoard;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RouletteServiceImpUTest {

    @Mock
    private Croupier croupier;

    @Mock
    private ResultBoard resultBoard;

    @Mock
    private Lock lock;

    @InjectMocks
    private RouletteServiceImpl testObj;

    @Before
    public void before() throws InterruptedException {
        when(lock.tryLock(2000, TimeUnit.MILLISECONDS)).thenReturn(true);
    }

    @Test
    public void testRegisterPlayer() {
        // act
        testObj.registerPlayer(VALID_ZERO_PLAYER_POSITION_PLAYER1);

        // assert
        verify(croupier).registerPlayer(VALID_ZERO_PLAYER_POSITION_PLAYER1);
    }

    @Test
    public void testPlaceBet() {
        // act
        testObj.placeBet(VALID_PLAYER_BET_PLAYER1);

        // assert
        verify(croupier).placeBet(VALID_PLAYER_BET_PLAYER1);
    }

    @Test
    public void testAnnounceWinningPocket() {
        // act
        testObj.announceWinningPocket(VALID_POCKET_1);

        // assert
        verify(croupier).announceWinningPocket(VALID_POCKET_1);
        verify(croupier).playerPositions();
        verify(resultBoard).updateBetResults(eq(VALID_POCKET_1), anyList());
        verify(resultBoard).updatePlayerPositions(anyList());
    }
}