package com.oyf.mongodb.service.impl;

import com.oyf.mongodb.entity.User;
import com.oyf.mongodb.service.MongodbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/10/15
 * @description：
 */
@Service
public class MongodbServiceImpl implements MongodbService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public void update(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update().set("name", user.getName())
                .set("age", user.getAge());
        // 更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, User.class);
    }

    @Override
    public void delete(String id) {
        User user = findById(id);
        mongoTemplate.remove(user);
    }

    @Override
    public List<User> find() {
        return mongoTemplate.findAll(User.class);
    }

    public User findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }
}
