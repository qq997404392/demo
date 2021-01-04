package com.oyf.thread;

/**
 * @author ouyangfei
 * @date Created in 2020/12/3
 * @description setPriority()
 */
public class Thread10 implements Runnable {

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " -> " + i);
        }
    }

    public static void main(String args[]) {
        Thread10 t = new Thread10();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t3.setPriority(Thread.NORM_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
    }

}
