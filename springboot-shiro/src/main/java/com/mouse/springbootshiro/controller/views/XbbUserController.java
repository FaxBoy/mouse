package com.mouse.springbootshiro.controller.views;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mouse.springbootshiro.dao.MoXbbUserPo;
import com.mouse.springbootshiro.services.IMoXbbUserService;
import com.mouse.springbootshiro.util.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shilun
 * @since 2019-09-04
 */
@RequestMapping("/xbbUser")
@Controller
public class XbbUserController {

    @Autowired
    private IMoXbbUserService iMoXbbUserService;

    @ApiOperation(value = "我的账号",hidden = false)
    @RequestMapping(value = "/myAccountList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultVO myAccountList(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) MoXbbUserPo moXbbUserPo) {
        List<MoXbbUserPo> list = iMoXbbUserService.list(moXbbUserPo);
        System.out.println(moXbbUserPo.toString());
        JSONArray jsonArray = new JSONArray();
        for (MoXbbUserPo moXbbUserPo1 : list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",moXbbUserPo1.getId());
            jsonObject.put("phone",moXbbUserPo1.getPhone());
            jsonObject.put("remarks",moXbbUserPo1.getRemarks());
            jsonArray.add(jsonObject);
        }
        return new ResultVO(200,"success",jsonArray);
    }

    @RequestMapping("/myIndex")
    public String test(HttpServletRequest request) {
        //逻辑处理
        request.setAttribute("key", "hello world");
        return "xbbUser/index";
    }

}

