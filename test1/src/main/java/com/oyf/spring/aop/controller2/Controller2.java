package com.oyf.spring.aop.controller2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ouyangfei
 * @date Created in 2021/2/22
 * @description
 */
@RestController
@RequestMapping("/controller2")
@Slf4j
public class Controller2 {

    @PostMapping("/test1")
    public void test1() {
        log.info("POST请求 >> Controller2_test1");
    }

    @PostMapping("/test2")
    public void test2(String s) {
        log.info("POST请求 >> Controller2_test2: {}", s);
    }

    @GetMapping("/test3")
    public void test3() {
        log.info("GET请求 >> Controller2_test3");
    }

    @GetMapping("/test4")
    public void test4(String s) {
        log.info("GET请求 >> Controller2_test4: {}", s);
    }

}
