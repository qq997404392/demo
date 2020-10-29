//package com.oyf.test.elasticsearch.service.impl;
//
//import com.oyf.test.elasticsearch.service.EsService;
//import org.elasticsearch.client.indices.GetIndexRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.stereotype.Service;
//
///**
// * @author ：ouyangfei
// * @date ：Created in 2020/10/26
// * @description：
// */
//@Service
//public class EsServiceImpl implements EsService {
//
//    @Autowired
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//    /**
//     * 判断index是否存在
//     * @param index
//     * @return
//     */
//    @Override
//    public boolean isExistsIndex(String index) {
//        GetIndexRequest getIndexRequest = new GetIndexRequest(index);
//        return elasticsearchRestTemplate.exists(index, getIndexRequest.getClass());
//    }
//
//    @Override
//    public void createIndex() {
//
//    }
//}
