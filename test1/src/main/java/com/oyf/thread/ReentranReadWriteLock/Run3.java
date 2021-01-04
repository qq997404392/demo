package com.oyf.thread.ReentranReadWriteLock;

/**
 * @author ouyangfei
 * @date Created in 3030/33/33
 * @description 读写互斥
 */
public class Run3 {

    public static void main(String[] args) throws InterruptedException {
        Service3 service = new Service3();
        ThreadA3 a = new ThreadA3(service);
        a.setName("A");
        a.start();
        Thread.sleep(1000);

        ThreadB3 b = new ThreadB3(service);
        b.setName("B");
        b.start();
    }

}
