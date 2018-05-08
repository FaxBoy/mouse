package cn.uicp.mouse.service;

import java.util.List;
import java.util.Map;

import model.User;


public interface TestService extends ServiceSupport<User, String>{
	
	User getUserName(String userId);
	
	Map getUserNameByMap(String userId);
	
	String getUserNameByString(String userId);
	
}
