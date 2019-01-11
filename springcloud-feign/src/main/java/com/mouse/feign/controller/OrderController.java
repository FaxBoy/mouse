package com.mouse.feign.controller;

import com.mouse.feign.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "defaultStores")
    public Object save(@RequestParam("user_id") int userId, @RequestParam("product_id") int productId, HttpServletRequest request){

        String token = request.getHeader("token");
        System.out.println(token);


        Map result = new HashMap();
        result.put("code",0);
        result.put("data",orderService.save(userId,productId));
        return result;
    }

    private Object defaultStores(int userId,int productId,HttpServletRequest request){

        //监控报警
        String orderKey = "order-save";
        String sendValue = redisTemplate.opsForValue().get(orderKey);
        new Thread(()->{
            if (StringUtils.isBlank(sendValue)){
                System.out.println("发生短信，用户下单失败,ip地址为："+request.getRemoteAddr());
                redisTemplate.opsForValue().set(orderKey,"order-save-fail",20, TimeUnit.SECONDS);
            }else{
                System.out.println("20秒内不重复发送");
            }
        }).start();


        System.out.println("服务节点调用失败");
        Map result = new HashMap();
        result.put("code",-1);
        result.put("msg","服务节点失败");
        return result;
    }

}
