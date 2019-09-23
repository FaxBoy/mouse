package com.mouse.springbootshiro.controller.views;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mouse.springbootshiro.dao.TestCodePo;
import com.mouse.springbootshiro.mapper.MoGpsMapper;
import com.mouse.springbootshiro.pojo.Location;
import com.mouse.springbootshiro.services.EsService;
import com.mouse.springbootshiro.services.ITestCodeService;
import com.mouse.springbootshiro.util.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shilun
 * @since 2019-07-01
 */
@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    private ITestCodeService iTestCodeService;

    @Autowired
    private EsService esService;

    @RequestMapping("")
    public String test(HttpServletRequest request) {
        //逻辑处理
        request.setAttribute("key", "hello world");
        return "index";
    }



}

