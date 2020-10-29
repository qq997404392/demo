package com.springcloud.order.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单详情实体类
 * 订单与订单详情是一对多的关系
 */
@Getter
@Setter
public class OrderDetail {
    private String orderId;
    private Item item = new Item();

    public OrderDetail() {}

    public OrderDetail(String orderId, Item item) {
        this.orderId = orderId;
        this.item = item;
    }
}
