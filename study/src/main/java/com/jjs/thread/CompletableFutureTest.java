package com.jjs.thread;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author jjs
 * @Version 1.0 2020/4/4
 */
public class CompletableFutureTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {

            }
            System.out.println("run end ...");
        });

        future.get();
    }


    @Test
    public void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {

            }
            System.out.println("run end ...");
            return "1";
        });

        future.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println("=======");
            }
        });

        System.out.println(future.get());
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {

            @Override
            public Long get() {
                return 100L;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long aLong) {
                return 200L + aLong;
            }
        });
        System.out.println(future.get());
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> handle = CompletableFuture.supplyAsync(() -> {
            return "123";
        }).handle(new BiFunction<String, Throwable, Integer>() {
            @Override
            public Integer apply(String s, Throwable throwable) {
                return Integer.valueOf(s);
            }
        });

        System.out.println(handle.get());
    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "12";
        });
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "345";
        });

        CompletableFuture<Integer> future2 = future.thenCombine(future1, new BiFunction<String, String, Integer>() {
            @Override
            public Integer apply(String s, String s2) {
                return Integer.valueOf(s + s2);
            }
        });

        System.out.println(future2.get());
    }

}
