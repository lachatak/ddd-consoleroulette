package org.kaloz.roulette.infrastucture.adapters.driven.loader;

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
import org.kaloz.roulette.domain.core.PlayerPosition;
import org.kaloz.roulette.infrastucture.adapters.driven.console.ConsolePlayerBetReader;
import org.kaloz.roulette.infrastucture.adapters.driven.loader.assembler.PlayerPositionAssembler;
import org.kaloz.roulette.infrastucture.adapters.driven.scheduled.WinningPocketAnnouncementScheduler;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RouletteFileLoaderUTest {

    private static final String FILE_INPUT = "first line\nsecond line";

    @Mock
    private ConsolePlayerBetReader consolePlayerBetReader;

    @Mock
    private WinningPocketAnnouncementScheduler winningPocketAnnouncementScheduler;

    @Mock
    private RouletteService rouletteService;

    @Mock
    private PlayerPositionAssembler playerPositionAssembler;

    private InputStream playerFileInputStream;

    private RouletteFileLoader testObj;

    @Before
    public void before() {
        playerFileInputStream = IOUtils.toInputStream(FILE_INPUT);
        testObj = new RouletteFileLoader(consolePlayerBetReader, winningPocketAnnouncementScheduler, rouletteService, playerPositionAssembler, playerFileInputStream);
    }

    @Test
    public void loadConfigurationAndStartRoulette() {
        // act
        testObj.loadConfigurationAndStartRoulette();

        // assert
        verify(rouletteService, times(2)).registerPlayer(any(PlayerPosition.class));
        verify(playerPositionAssembler, times(2)).assemble(anyString());
        verify(consolePlayerBetReader).readUserInput();
        verify(winningPocketAnnouncementScheduler).scheduleAnnouncement();
    }
}