package com.oyf.thread.ReentranReadWriteLock;

/**
 * @author ouyangfei
 * @date Created in 3030/13/31
 * @description
 */
public class ThreadA3 extends Thread {

    private Service3 service;

    public ThreadA3(Service3 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.read();
    }

}
