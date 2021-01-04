package com.oyf.thread;

/**
 * @author ouyangfei
 * @date Created in 2020/12/3
 * @description interrupt()
 */
public class Thread8 implements Runnable {

    public void run() {
        System.out.println("1、进入run()方法");
        try {
            Thread.sleep(5000);
            System.out.println("2、已经完成了休眠");
        } catch (InterruptedException e) {
            System.out.println("3、休眠被终止");
            return;
        }
        System.out.println("4、run()方法正常结束");
    }

    public static void main(String args[]) {
        Thread t = new Thread(new Thread8());
        t.start();
        // 中断线程执行
        t.interrupt();
    }

}
