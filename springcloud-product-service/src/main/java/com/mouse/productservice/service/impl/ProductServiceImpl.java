package com.mouse.productservice.service.impl;

import com.mouse.productservice.model.Product;
import com.mouse.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName ProductServiceImpl
 * @Description TODO
 * @date 2018/9/14 11:02
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final Map<Integer,Product> prodouctMap = new HashMap<>();

    static {
        Product product1 = new Product(1,"iphonex",8899,10);
        Product product2 = new Product(2,"华为p20",7699,10);
        Product product3 = new Product(3,"小米",8899,10);
        Product product4 = new Product(4,"中心",8899,10);
        Product product5 = new Product(5,"iphone5s",8899,10);
        Product product6 = new Product(6,"iphone6s",8899,10);
        Product product7 = new Product(7,"iphone7s",8899,10);
        Product product8 = new Product(8,"iphone8 plus",8899,10);

        prodouctMap.put(1,product1);
        prodouctMap.put(2,product2);
        prodouctMap.put(3,product3);
        prodouctMap.put(4,product4);
        prodouctMap.put(5,product5);
        prodouctMap.put(6,product6);
        prodouctMap.put(7,product7);
        prodouctMap.put(8,product8);


    }


    @Override
    public List<Product> listProduct() {
        Collection<Product> collection = prodouctMap.values();
        List<Product> list = new ArrayList<>(collection);
        return list;
    }

    @Override
    public Product findById(int id) {
        logger.info("ProductServiceImpl findById");
        return prodouctMap.get(id);
    }
}
