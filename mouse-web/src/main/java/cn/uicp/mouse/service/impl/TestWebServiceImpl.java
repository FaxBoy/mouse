package cn.uicp.mouse.service.impl;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.apache.log4j.Logger;

import cn.uicp.mouse.dao.TestDao;
import cn.uicp.mouse.pojo.Test;
import cn.uicp.mouse.service.TestWebService;

@WebService
@SOAPBinding(style=Style.RPC)
@SuppressWarnings("deprecation")
public class TestWebServiceImpl implements TestWebService{
static Logger logger = Logger.getLogger(testServiceImpl.class);
	
	@Resource
	private TestDao testDao;
	
	public Test getUserName(String userId) {
		return testDao.getUserName(userId);
	}

}
