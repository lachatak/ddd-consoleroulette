package org.kaloz.roulette.infrastucture.adapters.driving.scheduled;

import static org.mockito.Mockito.verify;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class WinningPocketAnnouncementSchedulerUTest {

    private static final int SCHEDULE_IN_SEC = 5;

    @Mock
    private ScheduledExecutorService scheduledExecutorService;

    @Mock
    private WinningPocketAnnouncerRunnable winningPocketAnnouncerRunnable;

    @InjectMocks
    private WinningPocketAnnouncementScheduler testObj;

    @Test
    public void testReadUserInput() {
        // setup
        ReflectionTestUtils.setField(testObj, "announcementPeriodInSec", SCHEDULE_IN_SEC);

        // act
        testObj.scheduleAnnouncement();

        // assert
        verify(scheduledExecutorService).scheduleWithFixedDelay(winningPocketAnnouncerRunnable, SCHEDULE_IN_SEC, SCHEDULE_IN_SEC, TimeUnit.SECONDS);
    }
}