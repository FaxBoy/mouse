package com.mouse.productservice.controller;

import com.mouse.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("list")
    public Object list(){
        return productService.listProduct();
    }

    @RequestMapping("find")
    public Object find(@RequestParam("id") int id){
        return productService.findById(id);
    }

}
