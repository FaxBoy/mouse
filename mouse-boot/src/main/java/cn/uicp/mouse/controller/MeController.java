package cn.uicp.mouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.uicp.mouse.jwt.JWT;
import model.ResponseData;
import model.User;


@Controller
@RequestMapping("/me")
public class MeController {

	@GetMapping("/get_info")
	@ResponseBody
	public ResponseData getInfo(@RequestParam String token) {
		User user = JWT.unsign(token, User.class);
		if (user != null) {
			return ResponseData.ok().putDataValue("user", user);
		}
		return ResponseData.customerError().putDataValue(ResponseData.ERRORS_KEY, new String[] { "token验证失败" });
	}
}
