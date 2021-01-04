package com.oyf.thread;

/**
 * @author ouyangfei
 * @date Created in 2020/12/3
 * @description synchronized同步方法
 */
public class Thread13 implements Runnable {

    private int ticket = 5;

    public void run() {
        for (int i = 0; i < 5; i++) {
            // 调用同步方法
            this.sale();
        }
    }

    // 声明同步方法
    private synchronized void sale() {
        if (ticket > 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " -> ticket = " + ticket--);
        } else {
            System.out.println(Thread.currentThread().getName() + " -> 卖完了");
        }
    }

    public static void main(String args[]) {
        Thread13 t = new Thread13();
        new Thread(t).start();
        new Thread(t).start();
    }

}
