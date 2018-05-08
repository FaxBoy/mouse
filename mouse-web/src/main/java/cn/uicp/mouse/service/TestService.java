package cn.uicp.mouse.service;

import java.util.List;
import java.util.Map;

import cn.uicp.mouse.pojo.Test;
import model.User;


public interface TestService extends ServiceSupport<Test, String>{
	
	User getUserName(String userId);
	
	Map getUserNameByMap(String userId);
	
	String getUserNameByString(String userId);
	
	void insertList(List<Test> list);
}
