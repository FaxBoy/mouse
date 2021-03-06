package com.mouse.ribbon.service.impl;

import com.mouse.ribbon.model.ProductOrder;
import com.mouse.ribbon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @date 2018/9/14 11:02
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ProductOrder save(int userid, int id) {

        Map<String,Object> map = restTemplate.getForObject("http://product-service/api/v1/product/find?id="+id, Map.class);

//        System.out.println(obj);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setId(id);
        productOrder.setUserId(userid);
        productOrder.setCreateTime(new Date());
        productOrder.setOrderNo(UUID.randomUUID().toString());
        productOrder.setProductName(map.get("name").toString());
        productOrder.setPrice(Integer.valueOf(map.get("price").toString()));
        return productOrder;
    }
}
