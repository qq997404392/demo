package com.oyf.thread.ReentranReadWriteLock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ouyangfei
 * @date Created in 2020/12/21
 * @description 读读锁
 */
public class Service1 {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            try {
                lock.readLock().lock();
                System.out.println(Thread.currentThread().getName() + " -> 获得读锁，时间：" + new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
                Thread.sleep(10000);
            } finally {
                lock.readLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
