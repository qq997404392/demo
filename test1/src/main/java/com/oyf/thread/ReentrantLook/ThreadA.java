package com.oyf.thread.ReentrantLook;

/**
 * @author ouyangfei
 * @date Created in 2020/12/10
 * @description
 */
public class ThreadA extends Thread {

    private MyService service;

    public ThreadA(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitA();
    }

}
