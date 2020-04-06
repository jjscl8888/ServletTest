package com.jjs.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jjs
 * @Version 1.0 2020/3/29
 */
public class ThreadTest {

    private volatile static boolean flag = true;

    @Test
    public void VolitatileTest() throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(flag) {
                    System.out.println("======");
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();
        System.out.println("--------");
        Thread.sleep(5000);
        flag = false;

        t.join();
        System.out.println("+++++++++++++++");
    }

    @Test
    public void grounpTest() throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("thread-group-1");
        Thread t1 = new Thread(threadGroup, new R1(), "t1");
        Thread t2 = new Thread(threadGroup, new R1(), "t2");

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(1);

        System.out.println(threadGroup.activeCount());
        System.out.println(threadGroup.activeGroupCount());
        System.out.println(threadGroup.getName());

        ThreadGroup threadGroup1 = new ThreadGroup(threadGroup, "thread-group-2");
        Thread t3 = new Thread(threadGroup1, new R1(), "t3");
        Thread t4 = new Thread(threadGroup1, new R1(), "t4");
        t3.start();
        t4.start();


        TimeUnit.SECONDS.sleep(3);
        threadGroup1.interrupt();

        System.out.println(threadGroup1.activeCount());
        System.out.println(threadGroup1.activeGroupCount());
        System.out.println(threadGroup1.getName());

        threadGroup1.list();
    }

    @Test
    public void test1() throws InterruptedException {
        Thread thread = new Thread(new T1());
        thread.start();

        Thread thread1 = new Thread(new T1());
        thread1.setDaemon(true);
        thread1.start();

        thread.join();
        thread1.join();

        TimeUnit.SECONDS.sleep(3);
    }

    @Test
    public void test2() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        // 抛出该异常时候，会清除中断标志位
                        Thread.currentThread().interrupt();

                    }
                    if (Thread.currentThread().isInterrupted()) {
                        //该函数只会判断是否中断，但是不会清楚中断标志位
                        System.out.println("=========");
                        break;
                    }
                }
            }
        });

        thread.start();
        thread.interrupt();

        thread.join();
    }

    private static int sum = 0;
    private static ReentrantLock lock = new ReentrantLock(false);
    @Test
    public void test3() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    add(i);
                }
            }
        });
        thread.start();
        thread.join();

        System.out.println("sum: " + sum);
    }

    private void add(int i) {
        try {
            lock.lock();
            sum += i;
        } finally {

            lock.unlock();
        }
    }

    @Test
    public void test4() throws InterruptedException {
        Runnable t1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "获得锁");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {

                    } finally {
                        lock.unlock();
                    }
                }
            }
        };
        Thread thread = new Thread(t1, "t1");
        Thread thread1 = new Thread(t1, "t2");

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();
    }

    @Test
    public void test5() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("++++++");
            LockSupport.park();
            System.out.println("======");
        });
        thread.setName("t1");
        thread.start();

        TimeUnit.SECONDS.sleep(5);
        LockSupport.unpark(thread);
        System.out.println("0000");
    }

    private static Semaphore semaphore = new Semaphore(2);
    @Test
    public void test6() throws InterruptedException {

        Runnable runnable = () -> {
            boolean flag = false;
            try {
                semaphore.acquire();
                flag =true;
                System.out.println("======");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (false) {
                    semaphore.release();
                }
                System.out.println("----------");
            }
        };
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Test
    public void test7() throws InterruptedException {
        List<Integer> list = Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList());
        TaskDisposeUtils.dispose(list, item -> {
            System.out.println("=======");
        });

        System.out.println("---------");
    }

    @Test
    public void test8() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
            System.out.println(Thread.currentThread().getName() + "迟到了");
        });
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "已经到了");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }


            System.out.println(Thread.currentThread().getName() + "开始吃饭");
        };
        List<Thread> lists = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
            lists.add(thread);
        }
        for (Thread list : lists) {
            list.join();
        }
    }

}

class R1 implements Runnable {

    @Override
    public void run() {
        System.out.println("threadName: " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread = Thread.currentThread();
        while(!thread.isInterrupted()) {
            ;
        }
        System.out.println("线程：" + thread.getName() + "停止了");
    }
}

class T1 implements Runnable {

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + ": " + thread.isDaemon());
    }
}