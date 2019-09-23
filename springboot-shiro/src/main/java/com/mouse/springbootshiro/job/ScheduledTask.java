package com.mouse.springbootshiro.job;

import com.alibaba.fastjson.JSONObject;
import com.mouse.springbootshiro.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: ScheduledTask
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/9/10 16:42
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/9/10        shilun           v1.0.0               创建
 */
@EnableScheduling
@Slf4j
@Component
public class ScheduledTask {

    @Value("${feigou.host}")
    private String FEIGOU_HOST;

    @Value("${feigou.devsecretkey}")
    private String devSecretkey ;

    @Value("${feigou.userid}")
    private String userId ;

    @Value("${feigou.password}")
    private String passWord ;

    public static String token = "" ;

    private static boolean isOne = true;

    @Scheduled(cron="0 0/8 * * * ?")
    public void testOne() {
        log.info("每8分钟执行一次");
        String url = FEIGOU_HOST + "/api/refreshToken/v1?token="+token;
        token = refreshToken(url);
        System.out.println(token);
    }

    public static String getToken(String url) {

        try {
            JSONObject jsonObject = JSONObject.parseObject(HttpClientUtil.postRequest(url, ""));
            token = JSONObject.parseObject(jsonObject.getString("user")).getString("token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(token);
        return token;
    }

    public static String refreshToken(String url) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(HttpClientUtil.postRequest(url, ""));
            token = JSONObject.parseObject(jsonObject.getString("user")).getString("token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    @Scheduled(fixedRate=30000)
    public void testTwo() {
        log.info("每30秒执行一次");
        if(isOne){
            isOne = false;
            String url = FEIGOU_HOST + "/api/login/v1?loginName="+userId+"" +
                    "&password="+passWord+"&devSecretkey="+devSecretkey;
            token = getToken(url);
        }
    }

}
