package com.oyf.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ouyangfei
 * @date Created in 2020/12/1
 * @description 线程池：Runnable实现类
 */
public class Thread4 implements Runnable {

    // 实例变量不可以共享
    private int i1 = 0;
    // 类变量可以共享
    private static int i2 = 0;

    @Override
    public void run() {
        i1++;
        // i1不会叠加
        System.out.println(Thread.currentThread().getName() + "-> 实例变量 " + i1);
        i2++;
        // i2会叠加
        System.out.println(Thread.currentThread().getName() + "-> 类变量 " + i2);
    }

    public static void main(String args[]) {
        // 使用Executors工具类中的方法创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        // 为线程池中的线程分配任务,使用submit方法，传入的参数可以是Runnable的实现类，也可以是Callable的实现类
        for (int i = 0; i < 2; i++) {
            pool.submit(new Thread4());
            pool.submit(new Thread4());
        }

        // 关闭线程池
        pool.shutdown();
    }

}
