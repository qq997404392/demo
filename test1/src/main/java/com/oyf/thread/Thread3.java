package com.oyf.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author ouyangfei
 * @date Created in 2020/11/30
 * @description Callable接口提供了一个call()方法可以作为线程执行体，这个方法具有返回值，还可以声明抛出异常
 */
public class Thread3 {

    public static void main(String[] args) {
        // 先使用Lambda表达式创建Callable<Integer>对象
        // 使用FutureTask来包装Callable对象
        FutureTask<Integer> task = new FutureTask<>((Callable<Integer>) () -> {
            int i = 0;
            for (; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "循环变量i的值：" + i);
            }
            return i;
        });

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " 的循环变量i的值：" + i);
            if (i == 2) {
                // 实质还是以Callable对象来创建并启动的
                new Thread(task, "有返回值的线程").start();
            }
        }

        try {
            // 获取返回值
            System.out.println("子线程的返回值：" + task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
