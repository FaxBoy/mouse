package cn.uicp.mouse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.ResponseData;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@GetMapping("/error")
	@ResponseBody
	public Map<String, Object> index() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("code", 200);
		response.put("message", "Ok");
		response.put("data", "token失效");
		return response;
	}
	
	@GetMapping("/test_response_data")
	@ResponseBody
	public ResponseData testResponseData() {
		return ResponseData.ok().putDataValue("token", "XXXXXXXXXXXXXXXXXXXXXXX");
	}
}
