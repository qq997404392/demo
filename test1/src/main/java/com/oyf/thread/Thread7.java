package com.oyf.thread;

/**
 * @author ouyangfei
 * @date Created in 2020/12/3
 * @description Thread.sleep()
 */
public class Thread7 implements Runnable {

    public void run() {
        for (int i = 0; i < 5; i++) {
            if (i > 2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " -> " + i);
        }
    }

    public static void main(String args[]) {
        Thread t = new Thread(new Thread7());
        t.start();
    }

}
