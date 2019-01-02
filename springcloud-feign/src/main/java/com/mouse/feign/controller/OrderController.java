package com.mouse.feign.controller;

import com.mouse.feign.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName OrderController
 * @Description TODO
 * @date 2018/9/14 11:02
 */
@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "defaultStores")
    public Object save(@RequestParam("user_id") int userId,@RequestParam("product_id") int productId){
        Map result = new HashMap();
        result.put("code",0);
        result.put("data",orderService.save(userId,productId));
        return result;
    }

    private Object defaultStores(int userId,int productId){
        Map result = new HashMap();
        result.put("code",-1);
        result.put("msg","服务节点失败");
        return result;
    }

}
