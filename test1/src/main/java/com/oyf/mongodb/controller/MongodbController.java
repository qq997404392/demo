package com.oyf.mongodb.controller;

import com.oyf.mongodb.entity.User;
import com.oyf.mongodb.service.MongodbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/10/15
 * @description：
 */
@RestController
@RequestMapping("/mongodb")
public class MongodbController {

    @Autowired
    private MongodbService mongodbService;

    @PostMapping("/insert")
    public void insert(@RequestBody User user) { mongodbService.insert(user);}

    @PostMapping("/update")
    public void update(@RequestBody User user) { mongodbService.update(user);}

    @GetMapping("/delete")
    public void delete(@RequestParam String id) { mongodbService.delete(id);}

    @GetMapping("/find")
    public List<User> findAll() {return mongodbService.find();}

    @GetMapping("/findById")
    public User findOne(@RequestParam String id) {return mongodbService.findById(id);}

}
