package com.jjs.thread;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jjs
 * @Version 1.0 2020/3/29
 */
public class ThreadPoolTest {
    @Test
    public void test1() throws InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                3,
                5,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int j = i;
            String taskName = "任务" + j;
            poolExecutor.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        poolExecutor.shutdown();
    }

    @Test
    public void test2() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            int j = i;
            String taskName = "任务" + j;
            executorService.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        executorService.shutdown();
    }

    @Test
    public void test3() throws InterruptedException {
        AtomicInteger threadNum = new AtomicInteger(1);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                5,
                5,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("自定义线程-" + threadNum.getAndIncrement());
                    return thread;
                },
                new ThreadPoolExecutor.AbortPolicy()
        );
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            poolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName());
            });
        }
        countDownLatch.await();

        poolExecutor.shutdown();
    }

    @Test
    public void test4() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                1,
                1,
                60L,
                TimeUnit.SECONDS,
                new PriorityBlockingQueue()
        );
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(new Task(i + "", i));
        }

        poolExecutor.shutdown();
    }

    @Test
    public void test5() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        int core = Runtime.getRuntime().availableProcessors();
        scheduledExecutorService.schedule(() -> {
            System.out.println(System.currentTimeMillis() + "start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "end");
        }, 2, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(20);
    }

    @Test
    public void test6() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        int core = Runtime.getRuntime().availableProcessors();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println(System.currentTimeMillis() + "start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "end");
        }, 1, 2, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(20);
    }

    @Test
    public void test7() throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(() -> {
            System.out.println(System.currentTimeMillis() + "start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "end");
            return "123";
        });
        new Thread(task).start();
        System.out.println(task.get());
    }

    @Test
    public void test8() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorCompletionService<String> service = new ExecutorCompletionService<>(executorService);
        service.submit(() -> {
            System.out.println(System.currentTimeMillis() + "start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "end");
            return
                    "123";
        });
        String res = service.take().get();
        System.out.println(res);

    }

}
