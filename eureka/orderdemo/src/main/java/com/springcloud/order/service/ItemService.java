package com.springcloud.order.service;

import com.springcloud.order.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ItemService {

    // Spring框架对RESTful方式的http请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;

    @Value("${item.url}")
    private String itemUrl;

    public Item queryItemById(Long id) {
        // 该方法走eureka注册中心调用(去注册中心根据app-item查找服务，这种方式必须先开启负载均衡@LoadBalanced)
        Item result = restTemplate.getForObject(itemUrl + id, Item.class);
        log.info("订单系统调用商品服务: {}", result);
        return result;
    }

}
