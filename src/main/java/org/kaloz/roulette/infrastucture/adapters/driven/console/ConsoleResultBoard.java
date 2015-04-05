package org.kaloz.roulette.infrastucture.adapters.driven.console;

import java.io.PrintStream;
import java.util.List;

import org.kaloz.roulette.domain.ResultBoard;
import org.kaloz.roulette.domain.core.BetResult;
import org.kaloz.roulette.domain.core.PlayerPosition;
import org.kaloz.roulette.domain.core.Pocket;
import org.kaloz.roulette.infrastucture.adapters.driven.console.assembler.ConsoleResultBoardAssembler;

public class ConsoleResultBoard implements ResultBoard {

    private final ConsoleResultBoardAssembler consoleResultBoardAssembler;
    private final PrintStream consoleOutputStream;

    public ConsoleResultBoard(final ConsoleResultBoardAssembler consoleResultBoardAssembler, final PrintStream consoleOutputStream) {
        this.consoleResultBoardAssembler = consoleResultBoardAssembler;
        this.consoleOutputStream = consoleOutputStream;
    }

    public void updateBetResults(final Pocket pocket, final List<BetResult> betResults) {
        consoleOutputStream.println(consoleResultBoardAssembler.consoleUpdateBetResults(pocket, betResults));
    }

    public void updatePlayerPositions(final List<PlayerPosition> playerPositions) {
        consoleOutputStream.println(consoleResultBoardAssembler.consoleUpdatePlayerPositions(playerPositions));
    }
}
