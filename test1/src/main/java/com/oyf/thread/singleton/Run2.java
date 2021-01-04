package com.oyf.thread.singleton;

/**
 * @author ouyangfei
 * @date Created in 2020/12/23
 * @description
 */
public class Run2 extends Thread {

    @Override
    public void run() {
        System.out.println(Singleton2.getInstance().hashCode());
    }

    public static void main(String[] args) {
        Run2 run1 = new Run2();
        Run2 run2 = new Run2();
        Run2 run3 = new Run2();
        run1.start();
        run2.start();
        run3.start();
    }

}
