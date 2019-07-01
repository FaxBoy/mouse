package com.mouse.springbootshiro.controller;


import com.mouse.springbootshiro.dao.TestCodePo;
import com.mouse.springbootshiro.mapper.TestCodeMapper;
import com.mouse.springbootshiro.services.ITestCodeService;
import com.mouse.springbootshiro.util.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shilun
 * @since 2019-07-01
 */
@RequestMapping("/testCode")
@Api(value = "onlyInterface", description = "OnlyAPI", produces = "application/json;charset=UTF-8")
@RestController
public class TestCodeController {

    @Autowired
    private ITestCodeService iTestCodeService;

    @ApiOperation("简单测试")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "hello world";
    }

    @ApiOperation("查询集合")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO queryList() {
        return new ResultVO(200,"success",iTestCodeService.list());
    }

    @ApiOperation("事务提交")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultVO save(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true)TestCodePo testCodePo) {

        try {
            iTestCodeService.saveTestCode(testCodePo);
        } catch (Exception e) {
            return ResultVO.fail(500,e.getMessage());
        }

        return new ResultVO(200,"success",iTestCodeService.list());
    }




}

