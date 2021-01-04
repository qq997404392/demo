package com.oyf.thread;

/**
 * @author ouyangfei
 * @date Created in 2020/12/7
 * @description 死锁
 */
class ThreadA {
    public void say() {
        System.out.println("张三对李四说：“你给我画，我就把书给你。”");
    }

    public void get() {
        System.out.println("张三得到画了。");
    }
}

class ThreadB {
    public void say() {
        System.out.println("李四对张三说：“你给我书，我就把画给你”");
    }

    public void get() {
        System.out.println("李四得到书了。");
    }
}

public class Thread14 implements Runnable {
    private static ThreadA threadA = new ThreadA();
    private static ThreadB threadB = new ThreadB();
    // 声明标志位，判断那个先说话
    private boolean flag = false;

    public void run() {
        if (flag) {
            // 同步线程A
            synchronized (threadA) {
                threadA.say();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (threadB) {
                    threadA.get();
                }
            }
        } else {
            // 同步线程B
            synchronized (threadB) {
                threadB.say();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (threadA) {
                    threadB.get();
                }
            }
        }
    }

    public static void main(String args[]) {
        Thread14 t1 = new Thread14();
        Thread14 t2 = new Thread14();
        t1.flag = false;
        t2.flag = true;
        Thread tA = new Thread(t1);
        Thread tB = new Thread(t2);
        tA.start();
        tB.start();
    }

}
