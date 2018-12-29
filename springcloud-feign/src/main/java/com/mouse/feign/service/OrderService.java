package com.mouse.feign.service;

import com.mouse.feign.model.ProductOrder;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName OrderService
 * @Description TODO
 * @date 2018/9/14 11:02
 */
public interface OrderService {

    ProductOrder save(int userid, int id);

}
