package com.oyf.thread.ReentranReadWriteLock;

/**
 * @author ouyangfei
 * @date Created in 2020/12/21
 * @description
 */
public class ThreadA1 extends Thread {

    private Service1 service;

    public ThreadA1(Service1 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.read();
    }

}
