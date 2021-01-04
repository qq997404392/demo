package com.oyf.thread;

/**
 * @author ouyangfei
 * @date Created in 2020/12/9
 * @description volatile
 */
public class Thread16 implements Runnable {
    volatile private boolean isRunning = true;

    public void run() {
        System.out.println("进入run了");
        while(isRunning) { }
        System.out.println("线程停止了");
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public static void main(String args[]) {
        Thread16 t1 = new Thread16();
        Thread t = new Thread(t1);
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.setRunning(false);
        System.out.println("已经赋值为false");
    }

}
