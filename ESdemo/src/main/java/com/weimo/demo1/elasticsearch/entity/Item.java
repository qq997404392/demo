package com.weimo.demo1.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/10/27
 * @description：
 */

// indexName：索引库名，shards：分片数量（默认1），replicas：副本数量（默认1）
@Document(indexName = "goods", shards = 1, replicas = 0)
@Data
@AllArgsConstructor
public class Item implements Serializable {
    @Id
    private Long id;

    /**
     * type：指定字段类型，Text分词，Keyword不分词
     * analyzer：分词器
     * index：是否创建索引（默认true）
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", index = true)
    private String title;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private String brand;

    @Field(type = FieldType.Double)
    private Double price;

    /**
     * 不会对图片地址查询,指定为false不作为索引
     */
    @Field(type = FieldType.Keyword, index = false)
    private String images;
}
