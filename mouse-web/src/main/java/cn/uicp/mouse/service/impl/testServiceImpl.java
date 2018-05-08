package cn.uicp.mouse.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.uicp.mouse.dao.TestDao;
import cn.uicp.mouse.pojo.Test;
import cn.uicp.mouse.service.TestService;
import model.User;


@Service
public class testServiceImpl extends ServiceSupportImpl<Test, String> implements TestService{

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

	@Transactional(rollbackFor=Exception.class)
	public void insertList(List<Test> list) {
		try {
		for (int i = 0; i < list.size(); i++) {
			this.testDao.insertList(list.get(i));
		}
		} catch (Exception e) {
			logger.info("异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
	}
	
}
