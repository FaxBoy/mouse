package com.mouse.feign.fallback;

import com.mouse.feign.service.ClientProductService;
import org.springframework.stereotype.Component;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName ClientProductFallback 针对 productService 降级处理
 * @Description TODO
 * @date 2018/9/14 11:02
 */
@Component
public class ClientProductFallback implements ClientProductService {

    @Override
    public String find(int id) {
        System.out.println("fegin 调用 productService 发生异常");
        return null;
    }
}
