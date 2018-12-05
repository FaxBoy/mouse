package com.mouse.swaggerApi;

import io.swagger.annotations.*;
import model.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName UserController
 * @Description TODO
 * @date 2018/9/14 11:02
 */
@RestController
@RequestMapping("/user")
@Api("userController相关api")
public class UserController {
    @ApiOperation("获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="username",dataType="String",required=true,value="用户的姓名",defaultValue="zhaojigang"),
            @ApiImplicitParam(paramType="query",name="password",dataType="String",required=true,value="用户的密码",defaultValue="wangna")
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value="/getUser",method= RequestMethod.GET)
    public User getUser(@RequestHeader("username") String username, @RequestParam("password") String password) {
        User user = new User();
        user.setMobile("17501620818");
        return user;
    }
}
