package com.jjs.thread;

import org.junit.Before;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jjs
 * @Version 1.0 2020/4/4
 */
public class UnsafeTest {
    private static Unsafe unsafe;
    static int count;
    static long countOffset;

    @Before
    public void before() throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        unsafe = (Unsafe)theUnsafe.get(null);

        Field countFiled = UnsafeTest.class.getDeclaredField("count");

        countOffset = unsafe.staticFieldOffset(countFiled);
    }

    public void add() throws InterruptedException {
        // TimeUnit.SECONDS.sleep(1);
        unsafe.getAndAddInt(UnsafeTest.class, countOffset, 1);
    }

    @Test
    public void test() throws InterruptedException {
        long l = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                try {
                    for (int i1 = 0; i1 < 10; i1++) {
                      add();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
        System.out.println("count = " + count);
    }

}
