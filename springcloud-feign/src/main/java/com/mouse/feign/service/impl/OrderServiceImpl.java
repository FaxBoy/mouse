package com.mouse.feign.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.mouse.feign.model.ProductOrder;
import com.mouse.feign.service.ClientProductService;
import com.mouse.feign.service.OrderService;
import com.mouse.feign.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    ClientProductService clientProductService;

    @Override
    public ProductOrder save(int userid, int id) {

        //Map<String,Object> map = restTemplate.getForObject("http://product-service/api/v1/product/find?id="+id, Map.class);

        String data = clientProductService.find(id);

        JsonNode jsonNode = JsonUtils.str2JsonNode(data);


//        System.out.println(obj);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setId(id);
        productOrder.setUserId(userid);
        productOrder.setCreateTime(new Date());
        productOrder.setOrderNo(UUID.randomUUID().toString());
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.valueOf(jsonNode.get("price").toString()));
        return productOrder;
    }
}
