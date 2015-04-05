package org.kaloz.roulette.infrastucture.adapters.driven.console;

import static org.kaloz.roulette.domain.TestFixtures.VALID_PLAYER_BET_RESULTS_PLAYER1_LIST;
import static org.kaloz.roulette.domain.TestFixtures.VALID_PLAYER_POSITION_LIST_PLAYER1_WIN;
import static org.kaloz.roulette.domain.TestFixtures.VALID_POCKET_1;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kaloz.roulette.infrastucture.adapters.driven.console.assembler.ConsoleResultBoardAssembler;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleResultBoardUTest {

    public static final String ASSEMBLER_RESULT = "RESULT";
    @Mock
    private ConsoleResultBoardAssembler consoleResultBoardAssembler;

    @Mock
    private PrintStream consoleOutputStream;

    @InjectMocks
    private ConsoleResultBoard consoleResultBoard;

    @Test
    public void updateBetResults() {
        // setup
        when(consoleResultBoardAssembler.consoleUpdateBetResults(VALID_POCKET_1, VALID_PLAYER_BET_RESULTS_PLAYER1_LIST)).thenReturn(ASSEMBLER_RESULT);

        // act
        consoleResultBoard.updateBetResults(VALID_POCKET_1, VALID_PLAYER_BET_RESULTS_PLAYER1_LIST);

        // assert
        verify(consoleOutputStream).println(ASSEMBLER_RESULT);
    }

    @Test
    public void updatePlayerPositions() {
        // setup
        when(consoleResultBoardAssembler.consoleUpdatePlayerPositions(VALID_PLAYER_POSITION_LIST_PLAYER1_WIN)).thenReturn(ASSEMBLER_RESULT);

        // act
        consoleResultBoard.updatePlayerPositions(VALID_PLAYER_POSITION_LIST_PLAYER1_WIN);

        // assert
        verify(consoleOutputStream).println(ASSEMBLER_RESULT);
    }

}