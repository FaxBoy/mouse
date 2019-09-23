package com.mouse.springbootshiro.controller;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mouse.springbootshiro.dao.MoXbbPo;
import com.mouse.springbootshiro.dao.MoXbbUserPo;
import com.mouse.springbootshiro.dao.TestCodePo;
import com.mouse.springbootshiro.mapper.MoXbbUserMapper;
import com.mouse.springbootshiro.pojo.ReqXbbUser;
import com.mouse.springbootshiro.pojo.XbbQuery;
import com.mouse.springbootshiro.services.IMoXbbService;
import com.mouse.springbootshiro.services.IMoXbbUserService;
import com.mouse.springbootshiro.util.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shilun
 * @since 2019-09-04
 */
@RequestMapping("/moXbbUserPo")
@Api(value = "xbbUserInterface", description = "用户", produces = "application/json;charset=UTF-8")
@RestController
public class MoXbbUserController {

    @Autowired
    private IMoXbbUserService iMoXbbUserService;

    @Autowired
    private MoXbbUserMapper xbbUserMapper;

    @ApiOperation(value = "我的账号",hidden = false)
    @RequestMapping(value = "/myAccount", method = RequestMethod.POST)
    public ResultVO myAccountList(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) ReqXbbUser reqXbbUser) {
        Page<MoXbbUserPo> page = new Page<>();
        page.setSize(reqXbbUser.getLimit());
        page.setCurrent(reqXbbUser.getPage());
        QueryWrapper<MoXbbUserPo> moXbbUserPoWrapper = new QueryWrapper<>();
        moXbbUserPoWrapper.lambda().eq(MoXbbUserPo::getOwnerId,reqXbbUser.getOwnerId());
        moXbbUserPoWrapper.lambda().eq(MoXbbUserPo::getStatusCd,reqXbbUser.getStatusCd());
        moXbbUserPoWrapper.lambda().orderByDesc(MoXbbUserPo::getCreateTime);
        IPage<MoXbbUserPo> poIPage =  iMoXbbUserService.selectPageVo(page,moXbbUserPoWrapper);
        return new ResultVO(200,"success",poIPage);
    }

    @ApiOperation(value = "获取验证码",hidden = false)
    @RequestMapping(value = "/getCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultVO getCode(@RequestParam @ApiParam(name="uuid",value="手机号",required=true)String uuid) {
        String result = iMoXbbUserService.getCodeByUuid(uuid);
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(result);
        if(jsonObject.getString("code").equals("-1")){

            createInfo(uuid, "取码："+jsonObject.getString("msg"));
            return new ResultVO(200,"获取验证码",jsonObject.getString("msg"));
        }else{
            JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.getString("msgInfo"));

            createInfo(uuid, "取码："+jsonObject1.getString("msgTextContent"));

            return new ResultVO(200,"获取验证码",jsonObject1.getString("msgTextContent"));
        }
    }

    private void createInfo(String uuid, String msg) {
        MoXbbUserPo moXbbUserPo1 = iMoXbbUserService.getByUuid(uuid);
        JSONArray jsonArray = new JSONArray();
        if (StringUtils.isNotEmpty(moXbbUserPo1.getInfo())) {
            jsonArray = JSONArray.parseArray(moXbbUserPo1.getInfo());
        }
        JSONObject info = new JSONObject();
        info.put("time", DateUtil.now());
        info.put("info", msg);
        info.put("operator", moXbbUserPo1.getOwnerId());
        jsonArray.add(info);
        moXbbUserPo1.setInfo(jsonArray.toJSONString());

        iMoXbbUserService.saveOrUpdate(moXbbUserPo1);
    }

    @ApiOperation(value = "指定手机号",hidden = false)
    @RequestMapping(value = "/appointPhone", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultVO appointPhone(@RequestParam @ApiParam(name="uuid",value="uuid",required=true)String uuid) {
        String result = iMoXbbUserService.appointPhone(uuid);
        JSONObject jsonObject = JSONObject.parseObject(result);

        createInfo(uuid, "指定："+jsonObject.getString("msg"));

        return new ResultVO(200,"指定手机号",jsonObject.getString("msg"));
    }

    @ApiOperation(value = "修改账号",hidden = false)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultVO update(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) MoXbbUserPo moXbbUserPo) {
        MoXbbUserPo moXbbUserPo1=iMoXbbUserService.getByUuid(moXbbUserPo.getUuid());
        JSONObject jsonObject = new JSONObject();
        if(moXbbUserPo.getField1().equals("statusCd")){
            moXbbUserPo1.setStatusCd(Integer.valueOf(moXbbUserPo.getField2()));
            if((moXbbUserPo1.getStatusCd()==2 || moXbbUserPo1.getStatusCd()==3) && moXbbUserPo1.getCzTime() == null){
                moXbbUserPo1.setCzTime(LocalDateTime.now());
            }
            jsonObject.put("info","状态变更："+moXbbUserPo1.getStatusCd());
        }else if(moXbbUserPo.getField1().equals("remarks")){
            moXbbUserPo1.setRemarks(moXbbUserPo.getField2());
            jsonObject.put("info","描述："+moXbbUserPo1.getRemarks());
        }else {
            return new ResultVO(200,"[暂时仅支持修改状态和备注]","[暂时仅支持修改状态和备注]");
        }

        JSONArray jsonArray = new JSONArray();
        if(StringUtils.isNotEmpty(moXbbUserPo1.getInfo())){
            jsonArray = JSONArray.parseArray(moXbbUserPo1.getInfo());
        }
        jsonObject.put("time", DateUtil.now());
        jsonObject.put("operator",moXbbUserPo1.getOwnerId());
        jsonArray.add(jsonObject);
        moXbbUserPo1.setInfo(jsonArray.toJSONString());
        iMoXbbUserService.saveOrUpdate(moXbbUserPo1);
        return new ResultVO(200,"修改成功","修改成功");
    }

    @ApiOperation(value = "数据导入",hidden = false)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultVO create(@RequestParam @ApiParam(name="content",value="商品名称",required=true)String content,
                           @RequestParam @ApiParam(name="ownerId",value="所有者",required=true)String ownerId) {

        if(StringUtils.isBlank(content) || StringUtils.isBlank(ownerId)){
            return new ResultVO(200,"缺少参数","");
        }

        List<MoXbbUserPo> list = iMoXbbUserService.getByOwnerId(ownerId);

        if(list == null || list.size()==0){
            return new ResultVO(200,"key不存在","");
        }

        String msg = iMoXbbUserService.createBatch(content,ownerId);
        return new ResultVO(200,msg,"");
    }

    @ApiOperation(value = "queryByuuid",hidden = false)
    @RequestMapping(value = "/queryByuuid", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultVO queryByuuid(@RequestParam @ApiParam(name="uuid",value="uuid",required=true)String uuid) {
        MoXbbUserPo moXbbUserPo=iMoXbbUserService.getByUuid(uuid);
        return new ResultVO(200,"success",moXbbUserPo);
    }

}

