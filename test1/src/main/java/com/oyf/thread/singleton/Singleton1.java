package com.oyf.thread.singleton;

/**
 * @author ouyangfei
 * @date Created in 2020/12/23
 * @description 饿汉式单例-立即加载
 */
public class Singleton1 {

    // 指向自己实例的私有静态引用，主动创建
    private static Singleton1 singleton1 = new Singleton1();

    // 私有的构造方法
    private Singleton1(){}

    // 以自己实例为返回值的静态的公有方法，静态工厂方法
    public static Singleton1 getInstance(){
        return singleton1;
    }

}
