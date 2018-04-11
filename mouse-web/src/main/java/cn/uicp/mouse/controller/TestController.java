package cn.uicp.mouse.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.uicp.mouse.service.RedisTestService;



@Controller
@RequestMapping("/t")
public class TestController {
	
	@Resource  
    private RedisTestService redisTestService;
	
	@RequestMapping(value = "/getTime",method=RequestMethod.GET)  
	@ResponseBody
	public JSONObject getTime(){
		JSONObject json = new JSONObject();
		json.put("time", redisTestService.getTimestamp("foo"));
		return json;
	}
	
}
