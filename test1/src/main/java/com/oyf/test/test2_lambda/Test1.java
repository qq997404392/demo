package com.oyf.test.test2_lambda;

public class Test1 {

    interface LambdaMath {
        int operation(int a, int b);
    }

    interface LambdaString {
        void printMessage(String message);
    }

    public static void main(String args[]) {
        // 类型声明
        LambdaMath add = (int a, int b) -> a + b;
        // 省略类型声明
        LambdaMath sub = (a, b) -> a - b;
        // 有大括号时，需要显式指定return
        LambdaMath mul = (a, b) -> { return a * b; };
        // 省略大括号可以省略return
        LambdaMath div = (a, b) -> a * b;

        System.out.println("10 + 5 = " + add.operation(10, 5));
        System.out.println("10 - 5 = " + sub.operation(10, 5));
        System.out.println("10 * 5 = " + mul.operation(10, 5));
        System.out.println("10 / 5 = " + div.operation(10, 5));


        LambdaString str1 = (message) -> System.out.println(message);
        // 仅有1个参数可以省略圆括号
        LambdaString str2 = message -> System.out.println(message);
        str1.printMessage("hello");
        str1.printMessage("world");
    }

}
