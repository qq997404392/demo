package com.oyf.thread.ReentranReadWriteLock;

/**
 * @author ouyangfei
 * @date Created in 2020/12/21
 * @description
 */
public class ThreadB1 extends Thread {

    private Service1 service;

    public ThreadB1(Service1 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.read();
    }

}
