package org.kaloz.roulette.infrastucture.adapters.driving.console;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kaloz.roulette.app.RouletteService;
import org.kaloz.roulette.domain.core.bet.PlayerBet;
import org.kaloz.roulette.infrastucture.adapters.driving.console.assembler.PlayerBetAssembler;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConsolePlayerBetRunnableUTest {

    private static final String CONSOLE_INPUT = "first line\nsecond line";

    @Mock
    private RouletteService rouletteService;

    @Mock
    private PlayerBetAssembler playerBetAssembler;

    private InputStream consoleInputStream;

    private ConsolePlayerBetRunnable testObj;

    @Before
    public void before() {
        consoleInputStream = IOUtils.toInputStream(CONSOLE_INPUT);
        testObj = new ConsolePlayerBetRunnable(rouletteService, playerBetAssembler, consoleInputStream);
    }

    @Test
    public void testRun() {
        // act
        testObj.run();

        // assert
        verify(rouletteService, times(2)).placeBet(any(PlayerBet.class));
        verify(playerBetAssembler, times(2)).assemble(anyString());
    }

}