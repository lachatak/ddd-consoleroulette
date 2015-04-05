package org.kaloz.roulette.infrastucture.adapters.driving.scheduled;

import static org.kaloz.roulette.domain.core.Pocket.pocket;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kaloz.roulette.app.RouletteService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WinningPocketAnnouncerRunnableUTest {

    @Mock
    private RouletteService rouletteService;

    @Mock
    private Random random;

    @InjectMocks
    private WinningPocketAnnouncerRunnable testObj;

    @Test
    public void testRun() {
        // setup
        when(random.nextInt(37)).thenReturn(1);

        // act
        testObj.run();

        // assert
        verify(rouletteService).announceWinningPocket(pocket(1));
        verify(random).nextInt(37);
    }
}