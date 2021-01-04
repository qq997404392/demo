package com.oyf.thread.singleton;

/**
 * @author ouyangfei
 * @date Created in 2020/12/23
 * @description 懒汉式单例-枚举类作为内部类
 */
public class Singleton5 {

    private enum EnumSingleton {
        INSTANCE;
        private Singleton5 singleton5;

        private EnumSingleton() {
            singleton5 = new Singleton5();
        }

        public Singleton5 getSingleton5() {
            return singleton5;
        }
    }

    public static Singleton5 getInstance() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return EnumSingleton.INSTANCE.getSingleton5();
    }

}
