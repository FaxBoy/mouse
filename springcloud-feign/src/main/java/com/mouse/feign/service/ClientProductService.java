package com.mouse.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author shilun
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@FeignClient(name="product-service")
public interface ClientProductService {

    @GetMapping("/api/v1/product/find")
    String find(@RequestParam("id") int id);

}
