package com.mouse.springbootshiro.test.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mouse.springbootshiro.SpringbootShiroApplication;
import com.mouse.springbootshiro.config.ElasticsearchRestClient;
import com.mouse.springbootshiro.mapper.MoGpsMapper;
import com.mouse.springbootshiro.pojo.Location;
import com.mouse.springbootshiro.services.EsService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: IndexDemo
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/7/31 17:58
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/7/31        shilun           v1.0.0               创建
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootShiroApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexDemo {

    @Autowired
    RestHighLevelClient highLevelClient;

    @Autowired
    private MoGpsMapper moGpsMapper;

    @Autowired
    private EsService esService;

    @Test
    public void queryIndex(){
        SearchRequest searchRequest = new SearchRequest("my_index");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("my_text", "械"));
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHits = hits.getHits();
            for(SearchHit hit : searchHits) {
                System.out.println(hit.getSourceAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryGps(){
        SearchRequest searchRequest = new SearchRequest("demo_index_location2");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.from(0);
        sourceBuilder.size(25);

        double lat = 30.23283;
        double lon = 112.823504;


        QueryBuilder geoQuery = new GeoDistanceQueryBuilder("location")
                .point(lat, lon)
                .distance(50, DistanceUnit.KILOMETERS) ////指定位置为中心的圆的半径，100km
                .geoDistance(GeoDistance.ARC); //按平面计算距离，平面(更快，但在长距离和靠近极点的地方是不准确的)而立方(default)


        GeoDistanceSortBuilder geoSort = SortBuilders.geoDistanceSort("location", lat, lon)
                .order(SortOrder.ASC) //最近的排在最前面
                .unit(DistanceUnit.KILOMETERS);
//
        sourceBuilder.sort(geoSort);

        sourceBuilder.query(geoQuery);
        searchRequest.source(sourceBuilder);


        try {
            SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHits = hits.getHits();
            for(SearchHit hit : searchHits) {
                System.out.println(hit.getSourceAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void creatIndex(){
        for(int i =97 ;i<=100;i++){

            List<Map<String,Object>> list = moGpsMapper.queryLocationByUuid((i-1)*1000);
            for(Map<String,Object> map:list){
                Location location = new Location();
                location.setUuid(map.get("uuid").toString());
                location.setAddress(map.get("city").toString());
                SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date date = format.parse(map.get("regist_date").toString());
                    location.setCreate_date(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                location.setUser_id(map.get("user_id").toString());
                location.setUser_name(map.get("user_name").toString());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("lat",Double.valueOf(map.get("lat").toString()));
                jsonObject.put("lon",Double.valueOf(map.get("lon").toString()));
                location.setLocation(jsonObject);
                String jsonStr = JSON.toJSONString(location, SerializerFeature.WriteDateUseDateFormat);
                esService.create("demo_index_location2",location.getUuid(),jsonStr);
            }
        }
    }

    @Test
    public void explain(){
        List<Map<String, Object>> mapList = moGpsMapper.explain();
        System.out.println("总行数："+mapList.get(0).get("rows"));
    }

    @Test
    public void timeOut(){
        List<Map<String, Object>> mapList = moGpsMapper.timeOut();
        System.out.println("总行数："+mapList.get(0).get("rows"));
    }

    @Test
    public void searchNear() throws ExecutionException, InterruptedException {
        SearchRequest searchRequest = new SearchRequest("demo_index_location2");

        double lat = 30.23283;
        double lon = 112.823504;


        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(0);
        sourceBuilder.size(15);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        QueryBuilder geoQuery = new GeoDistanceQueryBuilder("location")
                .point(lat, lon)
                .distance(50, DistanceUnit.KILOMETERS) ////指定位置为中心的圆的半径，100km
                .geoDistance(GeoDistance.ARC); //按平面计算距离，平面(更快，但在长距离和靠近极点的地方是不准确的)而立方(default)
        sourceBuilder.query(geoQuery);
        GeoDistanceSortBuilder geoSort = SortBuilders.geoDistanceSort("location", lat, lon)
                .order(SortOrder.ASC) //最近的排在最前面
                .unit(DistanceUnit.KILOMETERS);

        sourceBuilder.sort(geoSort);

        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        System.out.println("共计：["+searchHits.length+"]");
        System.out.println("小明，您当前位置为：[" + lat + "," + lon + "]，开始搜索附近 " + 50 + "KM 以内的朋友...");
        System.out.println("检索完成!总耗时:" + searchResponse.getTook().getMillis() + "毫秒,符合条件的有 " + searchHits.length + " 个!");
        for (SearchHit hit : searchHits) {
            String sourceAsString = hit.getSourceAsString();
            BigDecimal geoDistance = new BigDecimal((double) hit.getSortValues()[0])
                    .setScale(0, BigDecimal.ROUND_HALF_DOWN);//四舍五入
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap.get("user_name") + " 距您 " + geoDistance + "KM，source:" + sourceAsString);
        }
    }


}
