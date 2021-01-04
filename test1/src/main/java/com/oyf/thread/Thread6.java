package com.oyf.thread;

/**
 * @author ouyangfei
 * @date Created in 2020/12/2
 * @description
 */
public class Thread6 implements Runnable {
    private int i = 0;

    public void run() {
        i++;
        System.out.println(Thread.currentThread().getName() + "-> " + i);
    }

    public static void main(String args[]) {
        Thread t = new Thread(new Thread6());
        t.start();
        for (int i = 0; i < 5; i++) {
            if (i > 2) {
                System.out.println("线程强制执行");
                try {
                    // 线程强制运行
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

}

