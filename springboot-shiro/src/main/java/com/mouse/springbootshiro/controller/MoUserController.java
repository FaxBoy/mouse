package com.mouse.springbootshiro.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mouse.springbootshiro.dao.MoUserPo;
import com.mouse.springbootshiro.services.IMoUserService;
import com.mouse.springbootshiro.util.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2019-07-15
 */
@RequestMapping("/moUserPo")
@Api(value = "用户接口", description = "moUserPo", produces = "application/json;charset=UTF-8")
@RestController
public class MoUserController {

    @Autowired
    IMoUserService iMoUserService;

    @ApiOperation("用户列表")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResultVO<Page<MoUserPo>> page() {
        Page<MoUserPo> page = new Page<>(1,20);
        QueryWrapper<MoUserPo> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("regist_date");
        queryWrapper.eq("deleted","0");
        return new ResultVO(200,"success",iMoUserService.page(page,queryWrapper));
    }

}

