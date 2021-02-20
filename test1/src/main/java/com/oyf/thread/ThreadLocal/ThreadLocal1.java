package com.oyf.thread.ThreadLocal;

/**
 * @author ouyangfei
 * @date Created in 2021/1/12
 * @description
 */
public class ThreadLocal1 {

    public static void main(String args[]) {
        ThreadLocal<String> localName = new ThreadLocal();
        localName.set("张三");
        System.out.println(localName.get());
        localName.remove();
        System.out.println(localName.get());
    }

}
