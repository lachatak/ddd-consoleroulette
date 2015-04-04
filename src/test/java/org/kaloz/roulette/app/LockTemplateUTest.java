package org.kaloz.roulette.app;

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
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LockTemplateUTest {

    @Mock
    private Lock lock;

    private LockTemplate<Integer> testObj;

    private AtomicBoolean atomicBoolean;

    @Before
    public void before() {
        atomicBoolean = new AtomicBoolean();
        testObj = new LockTemplate<Integer>(lock) {
            @Override
            public Integer apply() {
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
        Integer result = testObj.perform();

        // assert
        assertThat(result.intValue(), equalTo(1));
        assertThat(atomicBoolean.get(), equalTo(true));
        verify(lock).unlock();
    }

    @Test
    public void withUnsuccessfulLockTheApplyShouldNotBeCalled() throws InterruptedException {
        // setup
        when(lock.tryLock(2000, TimeUnit.MILLISECONDS)).thenReturn(false);

        // act
        Integer result = testObj.perform();

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
        Integer result = testObj.perform();

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
        Integer result = testObj.perform();

        // assert
        assertThat(result.intValue(), equalTo(1));
        assertThat(atomicBoolean.get(), equalTo(true));
        verify(lock).unlock();
    }

    @Test
    public void withExceptionAtApplyShouldReturnTheValidResult() throws InterruptedException {
        // setup
        when(lock.tryLock(2000, TimeUnit.MILLISECONDS)).thenReturn(true);
        testObj = new LockTemplate<Integer>(lock) {
            @Override
            public Integer apply() {
                throw new RuntimeException();
            }
        };

        // act
        Integer result = testObj.perform();

        // assert
        assertNull(result);
        verify(lock).unlock();
    }
}