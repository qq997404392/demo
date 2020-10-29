package com.oyf.test.mongodb.service;

import com.oyf.test.mongodb.entity.User;

import java.util.List;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/10/15
 * @description：
 */
public interface MongodbService {

    void insert(User user);

    void update(User user);

    void delete(String id);

    List<User> find();

    User findById(String id);

}
