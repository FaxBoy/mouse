package cn.uicp.mouse.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.uicp.mouse.dao.UserDataDao;
import cn.uicp.mouse.service.UserDataService;
import model.UserData;


@Service
public class UserDataServiceImpl extends ServiceSupportImpl<UserData, String> implements UserDataService{

	static Logger logger = Logger.getLogger(UserDataServiceImpl.class);
	
	@Resource
	private UserDataDao userDataDao;
}
