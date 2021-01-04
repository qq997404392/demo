package com.oyf.thread.ReentranReadWriteLock;

/**
 * @author ouyangfei
 * @date Created in 3030/33/33
 * @description
 */
public class ThreadB3 extends Thread {

    private Service3 service;

    public ThreadB3(Service3 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.write();
    }

}
