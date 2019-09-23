package com.mouse.springbootshiro.controller;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mouse.springbootshiro.dao.MoXbbPo;
import com.mouse.springbootshiro.pojo.XbbQuery;
import com.mouse.springbootshiro.services.IMoXbbService;
import com.mouse.springbootshiro.util.HttpClientUtil;
import com.mouse.springbootshiro.util.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shilun
 * @since 2019-08-26
 */
@RequestMapping("/xbb")
@Api(value = "xbbInterface", description = "xbbAPI", produces = "application/json;charset=UTF-8",hidden = true)
@RestController
public class MoXbbController {

    @Autowired
    private IMoXbbService iMoXbbService;


    @ApiOperation(value = "最新卡密",hidden = true)
    @RequestMapping(value = "/todayList", method = RequestMethod.GET)
    public ResultVO todayList() {
        XbbQuery xbbQuery = new XbbQuery();
        xbbQuery.setSuccess("true");
        xbbQuery.setStatus(0);
        xbbQuery.setBeginDate(DateUtil.formatDateTime(DateUtil.beginOfDay(new Date())));
        xbbQuery.setEndDate(DateUtil.formatDateTime(DateUtil.endOfDay(new Date())));
        List<MoXbbPo> list = iMoXbbService.xbbQuery(xbbQuery);
        JSONArray jsonArray = new JSONArray();
        for (MoXbbPo moXbbPo : list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",moXbbPo.getId());
            jsonObject.put("phone",moXbbPo.getPhone());
            jsonArray.add(jsonObject);
        }
        return new ResultVO(200,"success",jsonArray);
    }

    @ApiOperation(value = "可用卡密",hidden = true)
    @RequestMapping(value = "/successList", method = RequestMethod.GET)
    public ResultVO successList() {
        XbbQuery xbbQuery = new XbbQuery();
        xbbQuery.setSuccess("true");
        xbbQuery.setStatus(0);
        JSONArray jsonArray = new JSONArray();
        List<MoXbbPo> list = iMoXbbService.successList(xbbQuery);
        for (MoXbbPo moXbbPo : list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",moXbbPo.getId());
            jsonObject.put("phone",moXbbPo.getPhone());
            jsonArray.add(jsonObject);
        }
        return new ResultVO(200,"success",jsonArray);
    }

    @ApiOperation(value = "智能更新",hidden = true)
    @RequestMapping(value = "/updateList", method = RequestMethod.GET)
    public ResultVO updateList(@RequestParam @ApiParam(name="success",value="数据类型（true/fasle）",required=true)String success) {
        XbbQuery xbbQuery = new XbbQuery();
//        xbbQuery.setBeginDate(DateUtil.formatDateTime(DateUtil.beginOfDay(new Date())));
//        xbbQuery.setEndDate(DateUtil.formatDateTime(DateUtil.endOfDay(new Date())));
        xbbQuery.setSuccess(success);
        List<MoXbbPo> xbbPoList =  iMoXbbService.xbbQuery(xbbQuery);
        iMoXbbService.updateList(xbbPoList);
        return new ResultVO(200,"success","");
    }


    @ApiOperation(value = "卡密爬取",hidden = true)
    @RequestMapping(value = "/dbReptile", method = RequestMethod.POST)
    public ResultVO dbReptile(@RequestParam @ApiParam(name="num",value="爬去次数")String num,
                              @RequestParam @ApiParam(name="telFirst",value="开头3")String telFirst,
                              @RequestParam @ApiParam(name="telSecond",value="中间4")String telSecond) {
        int max = 1000;
        if(StringUtils.isNotEmpty(num)){
            if(Integer.valueOf(num)>0 && Integer.valueOf(num) < 999999){
                max = Integer.valueOf(num);
            }
        }
        int resultNum = 0;
        for (int i = 0; i < max; i++) {
            if(iMoXbbService.dbReptile(telFirst,telSecond,true)){
                resultNum++;
            }
        }
        return new ResultVO(200,"success",resultNum) ;
    }



}

