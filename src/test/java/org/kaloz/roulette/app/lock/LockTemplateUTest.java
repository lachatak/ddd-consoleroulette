package org.kaloz.roulette.app.lock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kaloz.roulette.domain.RouletteGame;
import org.kaloz.roulette.domain.RouletteGameRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LockTemplateUTest {

    @Mock
    private RouletteGameRepository rouletteGameRepository;

    @Mock
    private RouletteGame rouletteGame;

    @Mock
    private Lock lock;

    @InjectMocks
    private LockTemplate testObj;

    private AtomicBoolean atomicBoolean;

    private LockAction<Integer> lockAction;

    @Before
    public void before() {
        atomicBoolean = new AtomicBoolean();
        when(rouletteGameRepository.load()).thenReturn(rouletteGame);
        lockAction = new LockAction<Integer>() {
            @Override
            protected Integer apply(final RouletteGame rouletteGame) {
                rouletteGame.playerPositions();
                atomicBoolean.set(true);
                return Integer.valueOf(1);
            }
        };
    }

    @Test
    public void withSuccessfulLockTheApplyShouldBeCalled() throws InterruptedException {
        // setup
        when(lock.tryLock(2000, TimeUnit.MILLISECONDS)).thenReturn(true);

        // act
        Integer result = testObj.perform(lockAction);

        // assert
        assertThat(result.intValue(), equalTo(1));
        assertThat(atomicBoolean.get(), equalTo(true));
        verify(lock).unlock();
        verify(rouletteGameRepository).load();
        verify(rouletteGame).playerPositions();
        verify(rouletteGameRepository).update(rouletteGame);
    }

    @Test
    public void withUnsuccessfulLockTheApplyShouldNotBeCalled() throws InterruptedException {
        // setup
        when(lock.tryLock(2000, TimeUnit.MILLISECONDS)).thenReturn(false);

        // act
        Integer result = testObj.perform(lockAction);

        // assert
        assertNull(result);
        assertThat(atomicBoolean.get(), equalTo(false));
        verify(lock, never()).unlock();
    }

    @Test
    public void withExceptionAtLockTryApplyShouldNotBeCalled() throws InterruptedException {
        // setup
        when(lock.tryLock(2000, TimeUnit.MILLISECONDS)).thenThrow(new RuntimeException());

        // act
        Integer result = testObj.perform(lockAction);

        // assert
        assertNull(result);
        assertThat(atomicBoolean.get(), equalTo(false));
        verify(lock, never()).unlock();
    }

    @Test
    public void withExceptionAtUnlockTryApplyShouldReturnTheValidResult() throws InterruptedException {
        // setup
        when(lock.tryLock(2000, TimeUnit.MILLISECONDS)).thenReturn(true);
        doThrow(new RuntimeException()).when(lock).unlock();

        // act
        Integer result = testObj.perform(lockAction);

        // assert
        assertThat(result.intValue(), equalTo(1));
        assertThat(atomicBoolean.get(), equalTo(true));
        verify(lock).unlock();
    }

    @Test
    public void withExceptionAtApplyShouldReturnTheValidResult() throws InterruptedException {
        // setup
        when(lock.tryLock(2000, TimeUnit.MILLISECONDS)).thenReturn(true);

        // act
        Integer result = testObj.perform(new LockAction<Integer>() {
            @Override
            protected Integer apply(final RouletteGame rouletteGame) {
                throw new RuntimeException();
            }
        });

        // assert
        assertNull(result);
        verify(lock).unlock();
    }
}