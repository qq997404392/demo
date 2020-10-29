package com.oyf.test.test3_default;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/7/6
 * @description：
 */
public interface DefaultInterface1 {

    default void test() {
        System.out.println("DefaultInterface1");
    }

}
