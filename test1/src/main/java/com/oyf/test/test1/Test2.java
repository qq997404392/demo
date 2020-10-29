package com.oyf.test.test1;

import java.util.*;

public class Test2 {
    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);list.add(4);list.add(5);list.add(6);
        list.add(7);list.add(8);
        list.add(1);list.add(2);list.add(3);list.add(4);

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>(3);
        /*for (int i = 1; i <= list.size() ; i++) {
            tmp.add(list.get(i - 1));
            System.out.println(i + " " + tmp);
            if (i%3 == 0 || i == list.size()) {
                List<Integer> copy = new ArrayList<>(3);
                copy.addAll(tmp);
                result.add(copy);
                System.out.println("result=" + result);
                tmp.clear();
            }
        }

        for (List<Integer> i : result) {
            System.out.println("结果=" + i);
        }

*/
        Set<Integer> result2 = new HashSet<Integer>(6);
        while (result2.size() < 6) {
            result2.add(list.get(new Random().nextInt(list.size())));
        }

        for (Integer i : result2) {
            System.out.println("结果=" + i);
        }

    }
}
