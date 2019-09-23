package com.mouse.springbootshiro.test.es;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mouse.springbootshiro.SpringbootShiroApplication;
import com.mouse.springbootshiro.dao.MoXbbUserPo;
import com.mouse.springbootshiro.mapper.MoXbbUserMapper;
import com.mouse.springbootshiro.services.IMoXbbService;
import com.mouse.springbootshiro.services.IMoXbbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: Code
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/9/10 16:57
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/9/10        shilun           v1.0.0               创建
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootShiroApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Code {

    @Autowired
    private IMoXbbUserService iMoXbbUserService;

    @Autowired
    private MoXbbUserMapper xbbUserMapper;

    @Test
    public void getCode(){
        String result = iMoXbbUserService.getCodeByUuid("16740550984");
        System.out.println(result);
    }

    @Test
    public void i(){

        Page<MoXbbUserPo> page = new Page<>();
        page.setSize(10);
        page.setPages(1);
        MoXbbUserPo moXbbUserPo = new MoXbbUserPo();
        moXbbUserPo.setOwnerId("shil");
//
//        IPage<MoXbbUserPo> poIPage = iMoXbbUserService.selectPageVo(page,moXbbUserPo);

        QueryWrapper<MoXbbUserPo> moXbbUserPoWrapper = new QueryWrapper<>();
        moXbbUserPoWrapper.lambda().eq(MoXbbUserPo::getOwnerId,"shil");
        IPage<MoXbbUserPo> poIPage =  xbbUserMapper.selectPage(page,moXbbUserPoWrapper);

//        MoXbbUserPo moXbbUserPo = new MoXbbUserPo();
//        moXbbUserPo.setUserId("123");
//        System.out.println(moXbbUserPo.toString());
//        geti(moXbbUserPo);
        System.out.println(poIPage.toString());
    }

    private void geti(MoXbbUserPo moXbbUserPo) {
        moXbbUserPo.setPassWord("123456");
        moXbbUserPo.setRemarks("哈哈");
    }



}
