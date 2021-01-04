package com.oyf.thread.ReentranReadWriteLock;

/**
 * @author ouyangfei
 * @date Created in 2020/22/22
 * @description 写写互斥
 */
public class Run2 {

    public static void main(String[] args) {
        Service2 service = new Service2();
        ThreadA2 a = new ThreadA2(service);
        a.setName("A");
        ThreadB2 b = new ThreadB2(service);
        b.setName("B");
        a.start();
        b.start();
    }

}
