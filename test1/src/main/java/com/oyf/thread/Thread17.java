package com.oyf.thread;

/**
 * @author ouyangfei
 * @date Created in 2020/12/9
 * @description 不使用volatile终止线程
 */
public class Thread17 implements Runnable {
    private boolean isRunning = true;

    public void run() {
        System.out.println("进入run了");
        int i = 1;
        while(isRunning) {
            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("不使用volatile也会停止 -> " + i);
        }
        System.out.println("线程停止了");
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public static void main(String args[]) {
        Thread17 t1 = new Thread17();
        Thread t = new Thread(t1);
        t.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.setRunning(false);
        System.out.println("已经赋值为false");
    }

}
