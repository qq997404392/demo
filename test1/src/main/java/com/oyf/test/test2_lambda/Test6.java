package com.oyf.test.test2_lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/7/2
 * @description：
 */
public class Test6 {

    // 获取用户姓名
    public String getName1(User user) {
        if (null != user) {
            return user.getName();
        } else {
            return "Unkown";
        }
    }

    public String getName2(User user) {
        return Optional.ofNullable(user).map(u -> u.getName()).orElse("Unkown");
    }

    public static Optional<Integer> getAge(User user) {
        return Optional.ofNullable(user).map(u -> u.getAge());
    }

    public void println(String s) {
        System.out.println(s);
    }

    public static void main(String args[]) {
        /*List<User> list = new ArrayList<>();

        User user1 = new User();
        user1.setName("张三");
        user1.setAge(15);

        User user2 = new User();
        user2.setName("李四");
        user2.setAge(19);

        Optional.of(user1).ifPresent(u -> System.out.println(u.getName()));*/

        /*// 升序打印
        List<String> strList = Arrays.asList("a", "c", "b", "e");
        // 1. java8之前
        strList.stream().sorted((s1, s2) -> s1.compareToIgnoreCase(s2)).forEach(s -> System.out.println(s));

        // 2. 方法引用: String.compareToIgnoreCase、System.out.println
        strList.stream().sorted(String::compareToIgnoreCase).forEach(System.out::println);

        Supplier<List<String>> supplier1 = () -> new ArrayList<>();

        Supplier<List<String>> supplier2 = ArrayList::new;*/

/*        String[] strs = {"e", "b", "a"};
        Arrays.sort(strs, (s1, s2) -> s1.compareToIgnoreCase(s2));
        Arrays.stream(strs).forEach(System.out::println);

        // strs 为一个 String 数组，lambda表达式(s1,s2)->s1.compareToIgnoreCase(s2)实现函数式接口的是Comparator接口
        // 但是无法降序排序
        Arrays.sort(strs, String::compareToIgnoreCase);
        Arrays.stream(strs).forEach(System.out::println);*/

        String[] strs = {"e", "b", "a"};
        Test6 test = new Test6();
        Arrays.asList(strs).stream().forEach(s -> test.println(s));

        Arrays.asList(strs).stream().forEach(test::println);
        // 进一步简写
        Arrays.stream(strs).forEach(test::println);

    }

}
