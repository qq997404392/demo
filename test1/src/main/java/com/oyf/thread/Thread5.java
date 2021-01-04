package com.oyf.thread;

import java.util.concurrent.*;

/**
 * @author ouyangfei
 * @date Created in 2020/12/1
 * @description 线程池：Callable实现类
 */
public class Thread5 {

    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 2; i++) {
            Future<Integer> future = executorService.submit(new Callable<Integer>() {

                @Override
                public Integer call() {
                    int result = 0;
                    for (int i = 0; i < 3; i++) {
                        result += i;
                    }
                    return result;
                }
            });

            try {
                System.out.println(Thread.currentThread().getName() + ":" + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }

}
