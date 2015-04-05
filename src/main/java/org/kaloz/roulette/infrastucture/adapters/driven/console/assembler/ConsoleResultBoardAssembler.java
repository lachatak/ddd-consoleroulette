package org.kaloz.roulette.infrastucture.adapters.driven.console.assembler;

import java.util.List;

import org.kaloz.roulette.domain.core.BetResult;
import org.kaloz.roulette.domain.core.PlayerPosition;
import org.kaloz.roulette.domain.core.Pocket;

public class ConsoleResultBoardAssembler {

    public String consoleUpdateBetResults(final Pocket pocket, final List<BetResult> betResults) {
        StringBuilder consoleBetResults = new StringBuilder("\n---")
                .append(String.format("\nNumber: %s", pocket.getNumber()))
                .append(String.format("\n%-20s%-10s%-10s%-20s", "Player", "Bet", "Outcome", "Winnings"))
                .append(String.format("\n%-20s", "---"));
        for(BetResult betResult : betResults) {
            consoleBetResults.append(String.format("\n%-20s%-10s%-10s%-20s", betResult.getPlayerBet().getPlayer().getName(), betResult.getPlayerBet().getBet().getField().bet(), betResult.getOutCome().name(), betResult.getWinnings().getAmount()));
        }
        return consoleBetResults.toString();
    }

    public String consoleUpdatePlayerPositions(final List<PlayerPosition> playerPositions) {
        StringBuilder consolePlayerPositions = new StringBuilder(String.format("\n\n%-20s%-20s%-20s", "Player", "Total Bet", "Total Win"))
                .append(String.format("\n%-20s", "---"));
        for(PlayerPosition playerPosition : playerPositions) {
            consolePlayerPositions.append(String.format("\n%-20s%-20s%-20s", playerPosition.getPlayer().getName(), playerPosition.getTotalBet().getAmount(), playerPosition.getTotalWin().getAmount()));
        }
        consolePlayerPositions.append("\n---");

        return consolePlayerPositions.toString();
    }
}
