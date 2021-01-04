package com.oyf.thread;

/**
 * @author ouyangfei
 * @date Created in 2020/11/26
 * @description 实现Runnable接口，同样的在run()方法中编写需要线程实现的操作，采用线程对象的start()方法启动，
 * 使用实现Runnable接口的方法来创建线程类时，多个线程之间可以共享线程类的实例变量，也可以共享类变量
 */
public class Thread2 implements Runnable {

    // 实例变量可以共享
    private int i1 = 0;
    // 类变量可以共享
    private static int i2 = 0;

    @Override
    public void run() {
        i1++;
        // i1会叠加
        System.out.println(Thread.currentThread().getName() + "-> 实例变量 " + i1);
        i2++;
        // i2会叠加
        System.out.println(Thread.currentThread().getName() + "-> 类变量 " + i2);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            if (i == 2) {
                Thread2 t = new Thread2();
                new Thread(t).start();
                new Thread(t).start();
            }
        }
    }

}
