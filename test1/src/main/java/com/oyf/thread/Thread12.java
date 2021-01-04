package com.oyf.thread;

/**
 * @author ouyangfei
 * @date Created in 2020/12/3
 * @description synchronized同步代码块
 */
public class Thread12 implements Runnable {

    // 假设一共有5张票
    private int ticket = 5;

    public void run() {
        for (int i = 0; i < 5; i++) {
            // 要对当前对象进行同步
            synchronized (this) {
                if (ticket > 0) {
                    // 还有票
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
        }
    }

    public static void main(String args[]) {
        Thread12 t = new Thread12();
        new Thread(t).start();
        new Thread(t).start();
    }

}
