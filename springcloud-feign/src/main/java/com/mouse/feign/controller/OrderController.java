package com.mouse.feign.controller;

import com.mouse.feign.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Object save(@RequestParam("user_id") int userId,@RequestParam("product_id") int productId){
        return orderService.save(userId,productId);
    }

}
