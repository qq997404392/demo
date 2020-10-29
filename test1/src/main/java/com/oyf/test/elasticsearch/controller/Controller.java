//package com.oyf.test.elasticsearch.controller;
//
//import com.oyf.test.elasticsearch.service.EsService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author ：ouyangfei
// * @date ：Created in 2020/10/26
// * @description：
// */
//@RestController
//@RequestMapping("/es")
//@Slf4j
//public class Controller {
//
//    @Autowired
//    private EsService esService;
//
//    @GetMapping("/isExistsIndex")
//    public void isExistsIndex(String index) {
//        boolean result = esService.isExistsIndex(index);
//        log.info("result: {}", result);
//    }
//
//
//}
