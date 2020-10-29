package com.springcloud.item.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品实体类
 */
@Getter
@Setter
@ToString
public class Item {
    private Long id;
    private String title;
    private String pic;
    private String desc;
    private Long price;

    public Item(){}

    public Item(long id, String title, String pic, String desc, Long price) {
        this.id=id;
        this.title=title;
        this.pic=pic;
        this.desc=desc;
        this.price=price;
    }
}
