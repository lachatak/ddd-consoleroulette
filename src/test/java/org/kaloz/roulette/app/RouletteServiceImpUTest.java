package org.kaloz.roulette.app;

import static org.kaloz.roulette.domain.TestFixtures.VALID_PLAYER_BET_PLAYER1;
import static org.kaloz.roulette.domain.TestFixtures.VALID_POCKET_1;
import static org.kaloz.roulette.domain.TestFixtures.VALID_ZERO_PLAYER_POSITION_PLAYER1;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kaloz.roulette.app.lock.LockTemplate;
import org.kaloz.roulette.domain.Croupier;
import org.kaloz.roulette.domain.RouletteGame;
import org.kaloz.roulette.domain.RouletteGameRepository;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RouletteServiceImpUTest {

    @Mock
    private RouletteGameRepository rouletteGameRepository;

    @Mock
    private Croupier croupier;

    @Mock
    private Lock lock;

    @Mock
    private RouletteGame rouletteGame;

    private RouletteServiceImpl testObj;

    @Before
    public void before() throws InterruptedException {
        when(rouletteGameRepository.load()).thenReturn(rouletteGame);
        when(lock.tryLock(2000, TimeUnit.MILLISECONDS)).thenReturn(true);

        LockTemplate lockTemplate = new LockTemplate(rouletteGameRepository, lock);
        testObj = new RouletteServiceImpl(croupier, lockTemplate);

    }

    @Test
    public void testRegisterPlayer() {
        // act
        testObj.registerPlayer(VALID_ZERO_PLAYER_POSITION_PLAYER1);

        // assert
        verify(croupier).registerPlayer(rouletteGame, VALID_ZERO_PLAYER_POSITION_PLAYER1);
    }

    @Test
    public void testPlaceBet() {
        // act
        testObj.placeBet(VALID_PLAYER_BET_PLAYER1);

        // assert
        verify(croupier).placeBet(rouletteGame, VALID_PLAYER_BET_PLAYER1);
    }

    @Test
    public void testAnnounceWinningPocket() {
        // act
        testObj.announceWinningPocket(VALID_POCKET_1);

        // assert
        verify(croupier).announceWinningPocket(rouletteGame, VALID_POCKET_1);
    }
}