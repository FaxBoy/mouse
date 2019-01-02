package com.mouse.productservice.controller;

import com.mouse.productservice.model.Product;
import com.mouse.productservice.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName ProductController
 * @Description TODO
 * @date 2018/9/14 11:02
 */
@RequestMapping("/api/v1/product")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Value("${server.port}")
    private String port;

    @RequestMapping("list")
    public Object list(){
        return productService.listProduct();
    }

    @RequestMapping("find")
    public Object find(@RequestParam("id") int id){

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Product product = productService.findById(id);
        Product result = new Product();
        BeanUtils.copyProperties(product,result);
        result.setName(product.getName()+" data from prot : "+port);
        return result;
    }

    @RequestMapping("getPort")
    public String getPort(){
        return port;
    }

}
