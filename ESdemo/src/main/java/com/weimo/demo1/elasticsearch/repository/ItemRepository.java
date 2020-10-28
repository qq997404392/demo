package com.weimo.demo1.elasticsearch.repository;

import com.weimo.demo1.elasticsearch.entity.Item;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.util.Streamable;

import java.util.List;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/10/27
 * @description：JPA查询
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

    /**
     * findBy模式不需要实现
     * 根据title查询
     */
    List<Item> findByTitle(String title);

    /**
     * 范围查item
     * @param left
     * @param right
     * @return
     */
    List<Item> findByPriceBetween(Double left, Double right);

    /**
     * 也可以返回stream对象
     * @param title
     * @return
     */
    Streamable<Item> findByTitleContaining(String title);

    /**
     * Query：自定义查询
     * match查询并设置operator
     * @param title
     * @param operator
     * @return
     */
    @Query("{\"match\": {\"title\":{ \"query\": \"?0\",\"operator\":\"?1\"}}}")
    Item findByTitleOperator(String title, String operator);

}
