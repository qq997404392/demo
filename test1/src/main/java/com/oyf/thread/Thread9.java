package com.oyf.thread;

/**
 * @author ouyangfei
 * @date Created in 2020/12/3
 * @description setDaemon()
 * 尽管run()方法中是死循环，但是程序依然可以执行完，因为方法中死循环的线程操作已经设置成后台运行。
 */
public class Thread9 implements Runnable {

    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + "在运行");
        }
    }

    public static void main(String args[]) {
        Thread t = new Thread(new Thread9());
        t.setDaemon(true);
        t.start();
    }

}
