package com.jjs.thread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jjs
 * @Version 1.0 2020/3/29
 */
public class BlockingQueue<T> {
    private int size;

    LinkedList<T> list = new LinkedList<>();

    private ReentrantLock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public BlockingQueue(int size) {
        this.size = size;
    }

    public void enqueue(T e) throws InterruptedException {
        lock.unlock();
        try {
            while (list.size() == size) {
                notFull.await();
            }
            list.add(e);
            System.out.println("==");
            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    public T dequeue() throws InterruptedException {
        T e;
        lock.lock();
        try {
            if (list.size() == 0) {
                notEmpty.await();
            }
            e = list.removeFirst();
            System.out.println("--------");
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }
}
