package com.oyf.thread.singleton;

/**
 * @author ouyangfei
 * @date Created in 2020/12/23
 * @description
 */
public class Run4 extends Thread {

    @Override
    public void run() {
        System.out.println(Singleton4.getInstance().hashCode());
    }

    public static void main(String[] args) {
        Run4 run1 = new Run4();
        Run4 run2 = new Run4();
        Run4 run3 = new Run4();
        run1.start();
        run2.start();
        run3.start();
    }

}
