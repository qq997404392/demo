package com.oyf.thread.ReentranReadWriteLock;

/**
 * @author ouyangfei
 * @date Created in 2020/12/21
 * @description 两个线程几乎同时进入lock（）后的代码
 */
public class Run1 {

    public static void main(String[] args) {
        Service1 service = new Service1();
        ThreadA1 a = new ThreadA1(service);
        a.setName("A");
        ThreadB1 b = new ThreadB1(service);
        b.setName("B");
        a.start();
        b.start();
    }

}
