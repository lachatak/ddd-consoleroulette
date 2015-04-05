package org.kaloz.roulette.config.adapters;

import java.io.PrintStream;

import org.kaloz.roulette.domain.ResultBoard;
import org.kaloz.roulette.infrastucture.adapters.driven.console.ConsoleResultBoard;
import org.kaloz.roulette.infrastucture.adapters.driven.console.assembler.ConsoleResultBoardAssembler;
import org.kaloz.roulette.infrastucture.adapters.driven.persistence.RouletteGameStore;
import org.kaloz.roulette.infrastucture.adapters.driven.persistence.inmemory.InMemoryRouletteGameStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.kaloz.roulette.infrastucture.adapters.driven")
public class DrivenInfrastructureLayerConfig {

    @Bean
    public RouletteGameStore inMemoryRouletteStore() {
        return new InMemoryRouletteGameStore();
    }

    @Bean
    public PrintStream consoleOutputStream() {
        return System.out;
    }

    @Bean
    public ConsoleResultBoardAssembler consoleResultBoardAssembler() {
        return new ConsoleResultBoardAssembler();
    }

    @Bean
    public ResultBoard consoleResultBoard() {
        return new ConsoleResultBoard(consoleResultBoardAssembler(), consoleOutputStream());
    }
}
