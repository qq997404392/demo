package com.oyf.test.test2_lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/7/1
 * @description：
 */
public class Test5 {

    public static void main(String args[]) {
        List<Integer> numbers = Arrays.asList(-1, -2, 0, 4, 5);
        numbers.forEach(n ->  System.out.println("List element: " + n));

        numbers.stream().map(n -> Math.abs(n)).forEach(n -> System.out.println("Element abs: " + n));


        // 将集合中的每个元素分割再打印
        List<String> list = Arrays.asList("1 2", "3 4", "5 6");
        // 1. flatMap()方法
        list.stream().flatMap(i -> Arrays.stream(i.split(" "))).forEach(i -> System.out.println("Element1: " + i));
        // 2. map()方法，返回了一个“流中流”，需要在每个Stream元素遍历时，再加一层forEach进行遍历
        list.stream().map(i -> Arrays.stream(i.split(" "))).forEach(i -> i.forEach(n -> System.out.println("Element2: " + n)));

        // 打印正数
        long count = numbers.parallelStream().filter(i -> i > 0).count();
        System.out.println("Positive count: " + count);

        // 求和
        Integer total = numbers.stream().reduce((t, n) -> t + n).get();
        System.out.println("Total: " + total);

        // 绝对值集合
        List<Integer> abs = numbers.stream().map( n -> Math.abs(n)).collect(Collectors.toList());
        System.out.println("Abs list: " + abs);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Max : " + stats.getMax());
        System.out.println("Min : " + stats.getMin());
        System.out.println("Sum : " + stats.getSum());
        System.out.println("Average : " + stats.getAverage());
        System.out.println("Count : " + stats.getCount());

    }
}
