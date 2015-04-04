package org.kaloz.roulette.infrastucture.adapters.driven.console;

import static org.mockito.Mockito.verify;

import java.util.concurrent.ExecutorService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConsolePlayerBetReaderUTest {

    @Mock
    private ConsolePlayerBetRunnable consolePlayerBetRunnable;

    @Mock
    private ExecutorService executorService;

    @InjectMocks
    private ConsolePlayerBetReader testObj;

    @Test
    public void testReadUserInput() {
        // act
        testObj.readUserInput();

        // assert
        verify(executorService).execute(consolePlayerBetRunnable);
    }
}