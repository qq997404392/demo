package com.oyf.thread;

/**
 * @author ouyangfei
 * @date Created in 2020/12/7
 * @description interrupt()
 */
public class Thread15 implements Runnable {

    public void run() {
    }

    public static void main(String args[]) {
        try {
            Thread t = new Thread(new Thread15());
            t.start();
            // 中断线程对象为main
            Thread.currentThread().interrupt();
            // Thread.interrupted()判断线程对象为main，第一次调用后重置中断状态，所以第二次结果为false
            System.out.println("是否停止 -> "+ Thread.interrupted()); // true
            System.out.println("是否停止 -> "+ Thread.interrupted()); // false


            // 中断线程对象为t
//            t.interrupt();
//            // t.isInterrupted()判断线程对象为t
//            System.out.println("是否停止 -> "+ t.isInterrupted()); // true
//            System.out.println("是否停止 -> "+ t.isInterrupted()); // true

//            // 中断线程对象为t
//            t.interrupt();
//            // Thread.interrupted()判断线程对象为main
//            System.out.println("是否停止 -> "+ Thread.interrupted()); // false
//            System.out.println("是否停止 -> "+ Thread.interrupted()); // false
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
