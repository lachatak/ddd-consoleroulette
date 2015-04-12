package org.kaloz.roulette.infrastucture.adapters.driving.scheduled;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;

@Named
@Slf4j
@DependsOn("rouletteGameFileLoader")
public class WinningPocketAnnouncementScheduler {

    private final ScheduledExecutorService scheduledExecutorService;
    private final WinningPocketAnnouncerRunnable winningPocketAnnouncerRunnable;

    @Value("${winning.pocket.announcement.period.sec}")
    private int announcementPeriodInSec;

    @Inject
    public WinningPocketAnnouncementScheduler(final ScheduledExecutorService scheduledExecutorService, final WinningPocketAnnouncerRunnable winningPocketAnnouncerRunnable) {
        this.scheduledExecutorService = scheduledExecutorService;
        this.winningPocketAnnouncerRunnable = winningPocketAnnouncerRunnable;
    }

    @PostConstruct
    public void scheduleAnnouncement() {
        scheduledExecutorService.scheduleWithFixedDelay(winningPocketAnnouncerRunnable, announcementPeriodInSec, announcementPeriodInSec, TimeUnit.SECONDS);
    }
}
