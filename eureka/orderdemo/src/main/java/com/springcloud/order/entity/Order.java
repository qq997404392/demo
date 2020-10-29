package com.springcloud.order.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 */
@Getter
@Setter
public class Order {
    private String orderId;
    private Long userId;
    private Date createDate;
    private Date updateDate;
    private List<OrderDetail> orderDetails;
}
