package cn.uicp.mouse.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.uicp.mouse.pojo.Test;
import model.User;


@Repository
public interface TestDao extends SupportDao<Test, String>{
	
	User getUserName(String userId);
	
	Map getUserNameByMap(String userId);
	
	String getUserNameByString(String userId);
	
	Integer insertList(Test test);
}
