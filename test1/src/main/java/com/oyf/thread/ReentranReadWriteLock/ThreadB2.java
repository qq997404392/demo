package com.oyf.thread.ReentranReadWriteLock;

/**
 * @author ouyangfei
 * @date Created in 2020/22/22
 * @description
 */
public class ThreadB2 extends Thread {

    private Service2 service;

    public ThreadB2(Service2 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.write();
    }

}
