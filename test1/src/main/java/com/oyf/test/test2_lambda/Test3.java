package com.oyf.test.test2_lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/7/1
 * @description：
 */
public class Test3 {

    // 过滤正数
    public static void filter1(List<Integer> numbers, Predicate<Integer> condition) {
        for (Integer number : numbers) {
            if (condition.test(number)) {
                System.out.println("Eligible number: " + number);
            }
        }
    }

    public static void filter2(List<Integer> numbers, Predicate<Integer> first, Predicate<Integer> second) {
        for (Integer number : numbers) {
            if (first.and(second).test(number)) {
                System.out.println("Eligible number: " + number);
            }
        }
    }

    public static void main(String args[]) {
        // 1. Predicate接口
        List<Integer> numbers = Arrays.asList(-3, 1, 0, -5, 5);
        filter1(numbers, n -> n > 0);

        // 2. Stream API + Predicate接口 + Consumer接口
        numbers.stream().filter(n -> n > 0).forEach(n -> System.out.println("Eligible number: " + n));

        // 过滤大于0并且小于5的数
        filter2(numbers, n -> n > 0, n -> n < 5);
    }

}
