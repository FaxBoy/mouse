package com.mouse.productservice.service;

import com.mouse.productservice.model.Product;

import java.util.List;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName ProductService
 * @Description TODO
 * @date 2018/9/14 11:02
 */
public interface ProductService {

    List<Product> listProduct();

    Product findById(int id);


}
