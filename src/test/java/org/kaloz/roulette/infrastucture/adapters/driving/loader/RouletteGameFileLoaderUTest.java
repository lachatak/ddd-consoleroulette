package org.kaloz.roulette.infrastucture.adapters.driving.loader;

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
import org.kaloz.roulette.domain.RouletteGame;
import org.kaloz.roulette.domain.RouletteGameRepository;
import org.kaloz.roulette.domain.core.PlayerPosition;
import org.kaloz.roulette.infrastucture.adapters.driving.console.ConsolePlayerBetReader;
import org.kaloz.roulette.infrastucture.adapters.driving.loader.assembler.PlayerPositionAssembler;
import org.kaloz.roulette.infrastucture.adapters.driving.scheduled.WinningPocketAnnouncementScheduler;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RouletteGameFileLoaderUTest {

    private static final String FILE_INPUT = "first line\nsecond line";

    @Mock
    private ConsolePlayerBetReader consolePlayerBetReader;

    @Mock
    private WinningPocketAnnouncementScheduler winningPocketAnnouncementScheduler;

    @Mock
    private RouletteService rouletteService;

    @Mock
    private RouletteGameRepository rouletteGameRepository;

    @Mock
    private PlayerPositionAssembler playerPositionAssembler;

    private InputStream playerFileInputStream;

    private RouletteGameFileLoader testObj;

    @Before
    public void before() {
        playerFileInputStream = IOUtils.toInputStream(FILE_INPUT);
        testObj = new RouletteGameFileLoader(consolePlayerBetReader, winningPocketAnnouncementScheduler, rouletteService, rouletteGameRepository, playerPositionAssembler, playerFileInputStream);
    }

    @Test
    public void loadConfigurationAndStartRoulette() {
        // act
        testObj.loadConfigurationAndStartRoulette();

        // assert
        verify(rouletteGameRepository).store(any(RouletteGame.class));
        verify(rouletteService, times(2)).registerPlayer(any(PlayerPosition.class));
        verify(playerPositionAssembler, times(2)).assemble(anyString());
        verify(consolePlayerBetReader).readUserInput();
        verify(winningPocketAnnouncementScheduler).scheduleAnnouncement();
    }
}