package com.oyf.thread.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @author ouyangfei
 * @date Created in 2020/12/23
 * @description 懒汉式单例-序列化
 */
public class Singleton3 implements Serializable {

    private static final long serialVersionUID = 888L;

    private static class Holder {
        private static Singleton3 singleton3 = new Singleton3();
    }

    private Singleton3() {}

    public static Singleton3 getInstance() {
        return Holder.singleton3;
    }

    protected Object readResolve() throws ObjectStreamException {
        System.out.println("调用readResolve方法");
        return Holder.singleton3;
    }

}
