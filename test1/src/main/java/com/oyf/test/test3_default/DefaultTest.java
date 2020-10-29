package com.oyf.test.test3_default;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/7/6
 * @description：
 */
public class DefaultTest implements DefaultInterface1, DefaultInterface2 {

    @Override
    public void test() {
        System.out.println("第一层");
        DefaultInterface1.super.test();
        DefaultInterface2.super.test();
    }

}
