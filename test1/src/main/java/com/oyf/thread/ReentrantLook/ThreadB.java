package com.oyf.thread.ReentrantLook;

/**
 * @author ouyangfei
 * @date Created in 2020/12/10
 * @description
 */
public class ThreadB extends Thread {

    private MyService service;

    public ThreadB(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitB();
    }

}
