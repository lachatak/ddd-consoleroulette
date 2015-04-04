package org.kaloz.roulette.app;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Slf4j
public abstract class LockTemplate<T> {

    private static final int WAIT_TIME_IN_MILLIES = 2000;

    private final Lock lock;

    public LockTemplate(final Lock lock) {
        this.lock = lock;
    }

    public T perform() {
        try {
            if (lock.tryLock(WAIT_TIME_IN_MILLIES, TimeUnit.MILLISECONDS)) {
                T result;
                try {
                    result = apply();
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

    protected abstract T apply();
}
