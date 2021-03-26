package com.oyf.spring.aop.controller1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author ouyangfei
 * @date Created in 2021/2/22
 * @description
 */
@RestController
@RequestMapping("/controller1")
@Slf4j
public class Controller1 {

    @PostMapping("/test1")
    public void test1() {
        log.info("POST请求 >> Controller1_test1");
    }

    @PostMapping("/test2")
    public void test2(String s, Integer i) {
        log.info("POST请求 >> Controller1_test2: {}, {}", s, i);
    }

    @GetMapping("/test3")
    public void test3() {
        log.info("GET请求 >> Controller1_test3");
    }

    @GetMapping("/test4")
    public void test4(String s, Integer i) {
        log.info("GET请求 >> Controller1_test4: {}", s);
    }

}
