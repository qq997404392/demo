package com.oyf.thread;


/**
 * @author ouyangfei
 * @date Created in 2020/11/26
 * @description 继承Thread类，重写该类的run()方法，也就是这个线程需要完成的任务，线程对象的start()方法可以用来启动该线程
 * 使用继承Thread类的方法来创建线程类时，多个线程之间无法共享线程类的实例变量，但是可以共享类变量
 */
public class Thread1 extends Thread {

    // 实例变量不共享
    private int i1 = 0;
    // 类变量可以共享
    private static int i2 = 0;

    @Override
    public void run() {
        i1++;
        // 实例变量的值一直为1
        System.out.println(getName() + "-> 实例变量 " + i1);
        i2++;
        // i2会累加
        System.out.println(getName() + "-> 类变量 " + i2);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            if (i == 2) {
                new Thread1().start();
                new Thread1().start();
            }
        }
    }

}
