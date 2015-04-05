package org.kaloz.roulette.infrastucture.adapters.driven.persistence.data;

import lombok.Data;

import java.util.List;

@Data
public class PersistenceRouletteGame {

    private String id;
    private final List<String> currentBets;
    private final List<String> playerPositions;
}
