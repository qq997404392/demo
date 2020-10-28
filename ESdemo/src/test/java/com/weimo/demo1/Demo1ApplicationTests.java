package com.weimo.demo1;

import com.weimo.demo1.elasticsearch.entity.Item;
import com.weimo.demo1.elasticsearch.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilterBuilder;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class Demo1ApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void createIndex() {
        // 设置索引信息(绑定实体类)，返回IndexOperations
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(Item.class);
        log.info(indexOperations.getSettings().toString());

        // 判断索引库是否存在
        boolean index = indexOperations.exists();
        if (!index) {
            // 创建索引库
            indexOperations.create();
        }
        // 为该IndexOperations绑定到的实体创建索引映射。有一个为给定类创建索引的重载,需要类的字节码文件
        Document mapping = indexOperations.createMapping();
        // 将刚刚通过类创建的映射写入索引
        indexOperations.putMapping(mapping);
    }

    @Test
    void deleteIndex() {
        // 设置索引信息(绑定实体类)，返回IndexOperations
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(Item.class);
        log.info(indexOperations.getSettings().toString());

        // 判断索引库是否存在
        boolean index = indexOperations.exists();
        if (index) {
            // 删除索引库
            indexOperations.delete();
        }
    }

    @Test
    void saveAll() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "OPPOFindX2", "手机", "OPPO", 4999d, "http://image.weimo.com/1.jpg"));
        list.add(new Item(2L, "OPPOFindX", "手机", "OPPO", 3999d, "http://image.weimo.com/2.jpg"));
        list.add(new Item(3L, "OPPORENO", "手机", "OPPO", 2999d, "http://image.weimo.com/3.jpg"));
        list.add(new Item(4L, "小米手机7", "手机", "小米", 3299.00, "http://image.weimo.com/4.jpg"));
        list.add(new Item(5L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.weimo.com/5.jpg"));
        list.add(new Item(6L, "华为META10", "手机", "华为", 4499.00, "http://image.weimo.com/6.jpg"));
        list.add(new Item(7L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.weimo.com/7.jpg"));
        list.add(new Item(8L, "荣耀V10", "手机", "华为", 2799.00, "http://image.weimo.com/8.jpg"));

        // 通过传入对象列表的方式保存多个文档
        itemRepository.saveAll(list);

        // 查询索引库中所有文档
        Iterable<Item> all = itemRepository.findAll();
        all.forEach(System.out::println);
    }

    @Test
    void save() {
        Item item = new Item(9L, "iPhone12", "手机", "Apple", 6299.00, "http://image.weimo.com/9.jpg");
        // id不存在则添加，存在则更新
        Item saveItem = itemRepository.save(item);
        System.out.println(saveItem);
    }

    @Test
    void find() {
        List<Item> list = itemRepository.findByPriceBetween(3000.00, 4200.00);
        for (Item item : list) {
            System.out.println(item);
        }

        Item item2 = itemRepository.findByTitleOperator("小米手机7", "and");
        System.out.println(item2);
    }

    @Test
    void delete() {
        // 通过id，索引库名删除，返回删除条数
        String count = elasticsearchRestTemplate.delete("1", IndexCoordinates.of("goods"));
        System.out.println(count);

        // 通过query删除
        elasticsearchRestTemplate.delete(
                new NativeSearchQueryBuilder().withQuery(
                        QueryBuilders.matchQuery("title", "OPPOFindX")
                ).build(), Item.class, IndexCoordinates.of("goods"));
    }

    @Test
    void find2() {
        // 通过Query查询
        SearchHits<Item> search1 = elasticsearchRestTemplate.search(new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery()).build(), Item.class);
        search1.forEach(System.out::println);

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "OPPOFindX");
        NativeSearchQuery query = nativeSearchQueryBuilder.withQuery(matchQueryBuilder).build();
        SearchHits<Item> search2 = elasticsearchRestTemplate.search(query, Item.class);
        search2.forEach(System.out::println);
    }

    @Test
    void testSearch() {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("title", "OPPOFindX"))
                .build();
        // 参数一：本机查询的构造；参数二：index的类；可选参数三：再次声明库名(可以多个)
        SearchHits<Item> search = elasticsearchRestTemplate.search(query, Item.class);
        search.forEach(searchHit -> System.out.println(searchHit.getContent()));
    }

    @Test
    void testNativeSearchQuery() {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("category", "手机"))
                // 添加分页，页码是从0开始的
                // pageable的实现类PageRequest的静态方法of
                // 要排序就增加参数三：升序-Sort.Direction.ASC  降序-Sort.Direction.DESC
                .withPageable(PageRequest.of(1, 4))
                // 排序，根据字段排序fieldSort("字段名")
                .withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC))
                .build();

        SearchHits<Item> search = elasticsearchRestTemplate.search(query, Item.class);
        search.forEach(searchHit -> System.out.println(searchHit.getContent()));
    }

    @Test
    void testAgg() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // 聚合可以有多个，所以add
        // terms词条聚合，传入聚合名称 field("聚合字段") size(结果集大小)
        NativeSearchQuery query = nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("brands").field("brand"))
                // 结果集过滤，这里设置不需要结果集(不添加包含与不包含，会自动生成length为0数组)
                .withSourceFilter(new FetchSourceFilterBuilder().build())
                .build();
        SearchHits<Item> hits = elasticsearchRestTemplate.search(query, Item.class);
        System.out.println(hits);
        // 获取聚合结果集
        Aggregations aggregations = hits.getAggregations();
        assert aggregations != null;
        // 因为结果为字符串类型，所以用ParsedStringTerms接收。还有ParsedLongTerms、ParsedDoubleTerms等
        ParsedStringTerms brands = aggregations.get("brands");
        // 获取桶
        brands.getBuckets().forEach(bucket -> {
            // 获取桶中的key与记录数
            System.out.println(bucket.getKeyAsString() + " " + bucket.getDocCount());
        });
    }

    @Test
    void testAgg2() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery query = nativeSearchQueryBuilder
                .addAggregation(
                        AggregationBuilders.terms("brands").field("brand")
                                // 添加子聚合(添加方式是一样的)，值为桶中品牌均价
                                .subAggregation(AggregationBuilders.avg("price_avg").field("price"))
                )
                // 结果集过滤
                .withSourceFilter(new FetchSourceFilterBuilder().build())
                .build();
        SearchHits<Item> hits = elasticsearchRestTemplate.search(query, Item.class);
        System.out.println(hits);
        // 获取聚合结果集
        Aggregations aggregations = hits.getAggregations();
        assert aggregations != null;
        ParsedStringTerms brands = aggregations.get("brands");
        // 获取桶brands
        brands.getBuckets().forEach(bucket -> {
            // 获取桶中的key与记录数 以及 嵌套的桶price_avg
            ParsedAvg price_avg = bucket.getAggregations().get("price_avg");
            System.out.println(bucket.getKeyAsString() + " " + bucket.getDocCount() + " " + price_avg.getValue());
        });
    }

}
