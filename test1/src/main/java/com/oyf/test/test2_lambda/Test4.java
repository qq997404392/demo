package com.oyf.test.test2_lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/7/1
 * @description：
 */
public class Test4 {

    public static void main(String args[]) {
        List<Integer> numbers = Arrays.asList(-1, -2, 0, 4, 5);
        long count = 0;
        for (Integer number : numbers) {
            if (number > 0) {
                count++;
            }
        }
        System.out.println("Positive count: " + count);

        long count2 = numbers.parallelStream().filter(n -> n > 0).count();
        System.out.println("Positive count: " + count2);
    }

}
