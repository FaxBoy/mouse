package cn.uicp.mouse.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import model.User;


@Repository
public interface TestDao extends SupportDao<User, String>{
	
	User getUserName(String userId);
	
	Map getUserNameByMap(String userId);
	
	String getUserNameByString(String userId);
	
}
