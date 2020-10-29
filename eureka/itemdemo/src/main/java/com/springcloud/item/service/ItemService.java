package com.springcloud.item.service;

import com.springcloud.item.entity.Item;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemService {
    private static final Map<Long, Item> ITEM_MAP = new HashMap<Long, Item>();

    static {
        // 准备一些静态数据,模拟数据库
        ITEM_MAP.put(1L, new Item(1L, "商品1", "http://图片1", "商品描述1", 1000L));
        ITEM_MAP.put(2L, new Item(2L, "商品2", "http://图片2", "商品描述2", 2000L));
        ITEM_MAP.put(3L, new Item(3L, "商品3", "http://图片3", "商品描述3", 3000L));
    }

    /**
     * 模拟实现商品查询
     *
     * @param id
     * @return
     */
    public Item queryItemById(Long id) {
        return ITEM_MAP.get(id);
    }
}
