package cn.uicp.mouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.uicp.mouse.jwt.JWT;
import model.ResponseData;
import model.User;

@Controller
@RequestMapping("/users")
public class UsersController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public ResponseData login(String username, String password) {
		if ("admin".equals(username) && "123456".equals(password)) {
			ResponseData responseData = ResponseData.ok();
			User user = new User();
			user.setId(1);
			user.setUsername(username);
			user.setPassword(password);
			responseData.putDataValue("user", user);
			String token = JWT.sign(user, 30L * 24L * 3600L * 1000L);
			if (token != null) {
				responseData.putDataValue("token", token);
			}
			return responseData;
		}
		return ResponseData.customerError().putDataValue(ResponseData.ERRORS_KEY, new String[] { "登录失败" });
	}
}
