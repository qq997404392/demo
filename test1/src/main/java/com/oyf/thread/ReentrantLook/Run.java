package com.oyf.thread.ReentrantLook;

/**
 * @author ouyangfei
 * @date Created in 2020/12/10
 * @description
 */
public class Run {

    public static void main(String[] args) {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.signalAll_A();
    }

}
