package com.oyf.thread;

/**
 * @author ouyangfei
 * @date Created in 2020/12/3
 * @description Thread.yield()
 */
public class Thread11 implements Runnable {

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 2) {
                System.out.print("执行yield()...");
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + " -> " + i);
        }
    }

    public static void main(String args[]) {
        Thread11 t = new Thread11();
        new Thread(t).start();
        new Thread(t).start();
    }

}
