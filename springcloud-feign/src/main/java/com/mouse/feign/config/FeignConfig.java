package com.mouse.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName FeignConfig
 * @Description TODO
 * @date 2018/9/14 11:02
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }
}

