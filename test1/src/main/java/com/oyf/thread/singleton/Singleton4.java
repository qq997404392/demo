package com.oyf.thread.singleton;

/**
 * @author ouyangfei
 * @date Created in 2020/12/23
 * @description 懒汉式单例
 */
public class Singleton4 {

    private static Singleton4 instance = null;
    static {
        instance = new Singleton4();
    }

    private Singleton4() {}

    public static Singleton4 getInstance() {
        return instance;
    }

}
