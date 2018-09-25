package cn.uicp.mouse.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.uicp.mouse.dao.RiverDao;
import cn.uicp.mouse.dao.TestDao;
import cn.uicp.mouse.service.RiverService;
import cn.uicp.mouse.service.TestService;
import model.River;
import model.User;


@Service
public class RiverServiceImpl extends ServiceSupportImpl<River, String> implements RiverService{

	static Logger logger = Logger.getLogger(RiverServiceImpl.class);
	
	@Resource
	private RiverDao riverDao;
	
	@Override
	public Integer insert(River entity) {
		// TODO Auto-generated method stub
		return riverDao.insert(entity);
	}
	
}
