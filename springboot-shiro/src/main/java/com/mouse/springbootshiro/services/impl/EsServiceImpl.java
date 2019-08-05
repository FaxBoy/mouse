package com.mouse.springbootshiro.services.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mouse.springbootshiro.config.ElasticsearchRestClient;
import com.mouse.springbootshiro.pojo.Location;
import com.mouse.springbootshiro.services.EsService;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: EsServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/7/31 18:24
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/7/31        shilun           v1.0.0               创建
 */
@Service
public class EsServiceImpl implements EsService {

    @Autowired
    RestHighLevelClient highLevelClient;


    @Override
    public void addLocation(Location location) {
//        restClientProperties
        SearchRequest searchRequest = new SearchRequest("search_index");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("amount", "210"));
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//            Arrays.stream(response.getHits().getHits())
//                    .forEach(i -> {
//                        System.out.println(i.getIndex());
//                        System.out.println(i.getSource());
//                    });

            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHits = hits.getHits();
            for(SearchHit hit : searchHits) {
                System.out.println(hit.getSourceAsString());
            }

            System.out.println(searchResponse.getHits());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONArray search(String storeName){

        JSONArray jsonArray = new JSONArray();
        SearchRequest searchRequest = new SearchRequest("search_index");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("amount", "210"));
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHits = hits.getHits();
            for(SearchHit hit : searchHits) {
                jsonArray.add(JSONObject.parseObject(hit.getSourceAsString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    public void create(String index, String id, String jsonStr){
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.source(jsonStr, XContentType.JSON);
//        indexRequest.id(id);
        try {
            System.out.println(highLevelClient.index(indexRequest,RequestOptions.DEFAULT));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
