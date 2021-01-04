package com.oyf.thread.singleton;

/**
 * @author ouyangfei
 * @date Created in 2020/12/23
 * @description
 */
public class Run1 extends Thread {

    @Override
    public void run() {
        System.out.println(Singleton1.getInstance().hashCode());
    }

    public static void main(String[] args) {
        Run1 run1 = new Run1();
        Run1 run2 = new Run1();
        Run1 run3 = new Run1();
        run1.start();
        run2.start();
        run3.start();
    }

}
