package com.oyf.test.test2_lambda;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/7/3
 * @description：
 */
public class Student {
    private String name;

    private Integer score;

    public void setNameAndScore(String name, Integer score) {
        this.name = name;
        this.score = score;
        System.out.println("name: " + name + ", score: " + score);
    }

    @FunctionalInterface
    interface TestInterface {
        // 注意：入参比Student类的setNameAndScore方法多1个Student对象，除第一个外其它入参类型一致
        void set(Student d, String name, Integer score);
    }

    public static void main(String[] args) {
        TestInterface testInterface1 = (student, name, score) -> student.setNameAndScore(name, score);
        testInterface1.set(new Student(), "张三", 80);

        // 方法引用
        TestInterface testInterface2 = Student::setNameAndScore;
        System.out.println(testInterface2);
        testInterface2.set(new Student(), "李四", 100);
    }
}
