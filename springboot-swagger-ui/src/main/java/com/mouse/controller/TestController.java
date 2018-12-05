package com.mouse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName TestController
 * @Description TODO
 * @date 2018/9/14 11:02
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/")
    public String index()
    {
        return "访问了test";
    }

}
