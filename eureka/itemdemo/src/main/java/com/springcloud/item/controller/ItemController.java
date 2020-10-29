package com.springcloud.item.controller;

import com.springcloud.item.entity.Item;
import com.springcloud.item.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "item/{id}")
    public Item queryItemById(@PathVariable("id") Long id) {
        log.info("Server port: {}", port);
        return this.itemService.queryItemById(id);
    }

}
