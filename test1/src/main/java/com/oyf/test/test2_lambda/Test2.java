package com.oyf.test.test2_lambda;

public class Test2 {

    interface LambdaString {
        void printMessage(String message);
    }

    final static String str = "hello";

    interface Convert<T1, T2> {
        void convert(int i);
    }

    public static void main(String args[]) {
        LambdaString str1 = message -> {
            System.out.println(str + message);
        };
        str1.printMessage("world");

        final int num1 = 1;
        Convert<Integer, String> con = num2 -> System.out.println(String.valueOf(num1 + num2));
        con.convert(2);

    }
}
