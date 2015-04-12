package org.kaloz.roulette.app.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import javax.inject.Inject;
import javax.inject.Named;

import org.kaloz.roulette.domain.RouletteGame;
import org.kaloz.roulette.domain.RouletteGameRepository;

@Slf4j
@Named
public class LockTemplate {

    private static final int WAIT_TIME_IN_MILLIES = 2000;

    private final RouletteGameRepository rouletteGameRepository;
    private final Lock lock;

    @Inject
    public LockTemplate(final RouletteGameRepository rouletteGameRepository, final Lock lock) {
        this.rouletteGameRepository = rouletteGameRepository;
        this.lock = lock;
    }

    public <T> T performReturn(final ReturnLockAction<T> returnLockAction) {
        try {
            if (lock.tryLock(WAIT_TIME_IN_MILLIES, TimeUnit.MILLISECONDS)) {
                T result;
                try {
                    RouletteGame rouletteGame = rouletteGameRepository.load();
                    result = returnLockAction.apply(rouletteGame);
                    rouletteGameRepository.update(rouletteGame);
                } finally {
                    try {
                        lock.unlock();
                    } catch (Exception e) {
                        log.error("Error unlocking lock!", e);
                    }
                }
                return result;
            }
        } catch (Exception e) {
            log.error("Error:", e);
        }
        return null;
    }

    public void performVoid(final VoidLockAction voidLockAction) {
        performReturn(new ReturnLockAction<Void>() {
            @Override
            protected Void apply(RouletteGame rouletteGame) {
                voidLockAction.apply(rouletteGame);
                return null;
            }
        });
    }
}
