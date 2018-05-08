package cn.uicp.mouse.service.impl;

import org.springframework.stereotype.Service;

import cn.uicp.mouse.service.RedisTestService;


@Service
public class RedisTestServiceImpl implements RedisTestService {
	
	public String getTimestamp(String param) {
		Long timestamp = System.currentTimeMillis();
		return timestamp.toString();
	}

}
