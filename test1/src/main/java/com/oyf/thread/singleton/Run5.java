package com.oyf.thread.singleton;

/**
 * @author ouyangfei
 * @date Created in 2020/12/23
 * @description
 */
public class Run5 extends Thread {

    @Override
    public void run() {
        System.out.println(Singleton5.getInstance().hashCode());
    }

    public static void main(String[] args) {
        Run5 run1 = new Run5();
        Run5 run2 = new Run5();
        Run5 run3 = new Run5();
        run1.start();
        run2.start();
        run3.start();
    }

}
