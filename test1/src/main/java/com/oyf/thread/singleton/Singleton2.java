package com.oyf.thread.singleton;

/**
 * @author ouyangfei
 * @date Created in 2020/12/23
 * @description 懒汉式单例-延迟加载
 */
public class Singleton2 {

    // 私有内部类，按需加载，用时加载，也就是延迟加载
    private static class Holder {
        private static Singleton2 singleton2 = new Singleton2();
    }

    private Singleton2() {}

    public static Singleton2 getInstance() {
        return Holder.singleton2;
    }
}
