package com.mouse.springbootshiro.controller;


import cn.hutool.Hutool;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mouse.springbootshiro.dao.TestCodePo;
import com.mouse.springbootshiro.mapper.MoGpsMapper;
import com.mouse.springbootshiro.mapper.TestCodeMapper;
import com.mouse.springbootshiro.pojo.Location;
import com.mouse.springbootshiro.services.EsService;
import com.mouse.springbootshiro.services.ITestCodeService;
import com.mouse.springbootshiro.util.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shilun
 * @since 2019-07-01
 */
@RequestMapping("/testCode")
@Api(value = "onlyInterface", description = "OnlyAPI", produces = "application/json;charset=UTF-8")
@RestController
public class TestCodeController {

    @Autowired
    private ITestCodeService iTestCodeService;

    @Autowired
    private EsService esService;

    @Autowired
    private MoGpsMapper moGpsMapper;

    @ApiOperation("简单测试")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return " 😄 hello world！ 当前时间："+ DateUtil.now();
    }

    @ApiOperation("查询集合")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO queryList() {
        return new ResultVO(200,"success",iTestCodeService.list());
    }

    @ApiOperation("事务提交")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultVO save(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true)TestCodePo testCodePo) {

        try {
            iTestCodeService.saveTestCode(testCodePo);
        } catch (Exception e) {
            return ResultVO.fail(500,e.getMessage());
        }

        return new ResultVO(200,"success",iTestCodeService.list());
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResultVO create(){
//        Location location = new Location();
//        location.setFoo("哈哈");
//        location.setBar("书法的飞机撒了绝路逢生");
//        location.setLocation(new GeoPoint(123, 31));
//        esService.addLocation(location);

//        Random random = new Random();
//        for(int i = 0 ; i < 1200; i++){
//            Order order = new Order();
//            int j = random.nextInt(20) % 20 + 1;
//            order.setId(i);
//            order.setStoreId(j);
//            order.setStoreName("旗舰店"+ j);
//            order.setCategoryId(j);
//            order.setCategoryCode("shirt_"+j);
//            order.setProductCode("product_"+i);
//            order.setQuantity(random.nextInt(20) % 20 + 1);
//            order.setAmount(200 + (random.nextInt(20) % 20 + 1));
//            order.setPayDate(new Date());
//            String jsonStr = JSON.toJSONString(order, SerializerFeature.WriteDateUseDateFormat);
//            bulkProcessorService.insertById("search_index",i+"",jsonStr);
//        }


//        Random random = new Random();
//        for(int i = 0 ; i < 1200; i++){
//            Order order = new Order();
//            int j = random.nextInt(20) % 20 + 1;
//            order.setId(i);
//            order.setStoreId(j);
//            order.setStoreName("旗舰店"+ j);
//            order.setCategoryId(j);
//            order.setCategoryCode("shirt_"+j);
//            order.setProductCode("product_"+i);
//            order.setQuantity(random.nextInt(20) % 20 + 1);
//            order.setAmount(200 + (random.nextInt(20) % 20 + 1));
//            order.setPayDate(new Date());
//            String jsonStr = JSON.toJSONString(order, SerializerFeature.WriteDateUseDateFormat);
//            bulkProcessorService.insertById("search_index",i+"",jsonStr);
//        }

            String uuid= "000002604e6b4ca5bd56946fcb7bc170";

            for(int i =100 ;i<=7830;i++){
                List<Map<String,Object>> list = moGpsMapper.queryLocationByUuid(i);

                for(Map<String,Object> map:list){
                    Location location = new Location();
                    location.setUuid(uuid);
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

                    esService.create("demo_index_gps",uuid,jsonStr);
                }
            }



        return new ResultVO(200,"success",null);
    }

    @ApiOperation("esSerach")
    @RequestMapping(value = "/esSerach", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultVO search(@RequestParam @ApiParam(name="storeName",value="商品名称",required=true)String storeName){
        return new ResultVO(200,"success",esService.search(storeName));
    }



}

