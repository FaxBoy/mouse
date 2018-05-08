package cn.uicp.mouse.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.uicp.mouse.dao.TestDao;
import cn.uicp.mouse.service.TestService;
import model.User;


@Service
public class testServiceImpl extends ServiceSupportImpl<User, String> implements TestService{

	static Logger logger = Logger.getLogger(testServiceImpl.class);
	
	@Resource
	private TestDao testDao;
	
	public User getUserName(String userId) {
		return testDao.getUserName(userId);
	}

	public Map getUserNameByMap(String userId) {
		// TODO Auto-generated method stub
		return this.testDao.getUserNameByMap(userId);
	}

	public String getUserNameByString(String userId) {
		// TODO Auto-generated method stub
		return this.testDao.getUserNameByString(userId);
	}

}
