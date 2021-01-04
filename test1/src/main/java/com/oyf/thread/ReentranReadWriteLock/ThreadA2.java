package com.oyf.thread.ReentranReadWriteLock;

/**
 * @author ouyangfei
 * @date Created in 2020/12/21
 * @description
 */
public class ThreadA2 extends Thread {

    private Service2 service;

    public ThreadA2(Service2 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.write();
    }

}
