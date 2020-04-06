package com.jjs.thread;

/**
 * @author jjs
 * @Version 1.0 2020/3/29
 */
public class Task implements Runnable, Comparable<Task>
{
    private int i;
    private String name;

    public Task(String name, int i) {
        this.i = i;
        this.name = name;
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(o.i, this.i);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
