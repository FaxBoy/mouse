package com.mouse.springbootshiro.test.es;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mouse.springbootshiro.SpringbootShiroApplication;
import com.mouse.springbootshiro.dao.MoXbbPo;
import com.mouse.springbootshiro.dao.MoXbbUserPo;
import com.mouse.springbootshiro.mapper.MoGpsMapper;
import com.mouse.springbootshiro.pojo.Location;
import com.mouse.springbootshiro.pojo.XbbQuery;
import com.mouse.springbootshiro.services.IMoXbbService;
import com.mouse.springbootshiro.services.IMoXbbUserService;
import com.mouse.springbootshiro.test.utils.HttpClientService;
import com.mouse.springbootshiro.test.utils.HttpClientUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: Xbb
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/8/23 13:37
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/8/23        shilun           v1.0.0               创建
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootShiroApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Xbb {

    @Autowired
    private IMoXbbService iMoXbbService;

    @Autowired
    private IMoXbbUserService iMoXbbUserService;

    final BASE64Encoder encoder = new BASE64Encoder();
    final BASE64Decoder decoder = new BASE64Decoder();

//    @Test
//    public void updateUuid(){
//        List<MoXbbUserPo> list = iMoXbbUserService.list();
//        for (MoXbbUserPo moXbbUserPo : list){
//            System.out.println(moXbbUserPo.toString());
//            moXbbUserPo.setUuid(IdUtil.simpleUUID());
//            iMoXbbUserService.saveOrUpdate(moXbbUserPo);
//        }
//        System.out.println(list.size());
//    }

    @Test
    public void xbbInit(){

        int max = 1000000;

        int threadCount = 2;
        for (int i = 0; i < max; i++) {
            String phone = getTel();

            XbbQuery xbbQuery = new XbbQuery();
            xbbQuery.setPhone(phone);

            List<MoXbbPo> list = iMoXbbService.xbbQuery(xbbQuery);
            if(null != list && list.size()>0){
                System.out.println("存在如下");
                System.out.println(list.get(0).toString());
                continue;
            }

            BASE64Encoder encoder = new BASE64Encoder();
            String data = encoder.encode(phone.getBytes());
            System.out.println("BASE64加密：" + data);

            String result = "";
            try {
                //result = HttpClientUtil.postRequest("http://xbb.huitaofuwu.com/ph?token=&phone=" + phone, "");
                result = HttpClientUtil.postRequest("http://yzm.huitaofuwu.com/index/index/set.html?phone="+data,"");
            } catch (Exception e) {
                e.printStackTrace();
            }

            JSONObject jsonObject = JSONObject.parseObject(result);
            System.out.println(phone + " 返回信息：" + result);
            if(jsonObject.containsKey("msg")){
                if(jsonObject.get("msg")!= null && jsonObject.getString("msg").indexOf("该手机号不存在")==0){
                    continue;
                }
            }

            if(jsonObject.getString("code").equals("0")){
                MoXbbPo moXbbPo = new MoXbbPo();
                moXbbPo.setInfo(jsonObject.toJSONString());
                moXbbPo.setPhone(phone);
                moXbbPo.setStatus(0);
                moXbbPo.setOwner("adminKbb");
                moXbbPo.setCreateTime(new Date());
                moXbbPo.setSuccess("success");
                try {
                    iMoXbbService.save(moXbbPo);
                    continue;
                }catch (Exception e){
                    System.out.println("该会员已经存在："+phone);
                }
            }else{
                String res = "稍后会自动释放";
                String resyue = "您的账户余额不足";
                MoXbbPo moXbbPo = new MoXbbPo();
                moXbbPo.setInfo(jsonObject.toJSONString());
                moXbbPo.setPhone(phone);
                moXbbPo.setOwner("adminKbb");
                moXbbPo.setCreateTime(new Date());
                if(jsonObject.getString("msg").indexOf(res) == 0){
                    moXbbPo.setStatus(9);//稍后会自动释放
                    moXbbPo.setSuccess("true");
                    iMoXbbService.save(moXbbPo);
                }else if(jsonObject.getString("msg").indexOf(resyue) == 0){
                    moXbbPo.setStatus(8);//您的账户余额不足
                    moXbbPo.setSuccess("false");
                    iMoXbbService.save(moXbbPo);
                }else{
                    moXbbPo.setStatus(99);//号码存在但是不能用
                    moXbbPo.setSuccess("false");
                    iMoXbbService.save(moXbbPo);
                }
                try {
                    iMoXbbService.save(moXbbPo);
                    continue;
                }catch (Exception e){
                    System.out.println("该会员已经存在："+phone);
                }
            }

        }
    }


    @Test
    public void xbbUpdate(){
        XbbQuery xbbQuery = new XbbQuery();
        xbbQuery.setSuccess("true");
        List<MoXbbPo> xbbPoList =  iMoXbbService.xbbQuery(xbbQuery);

        for(MoXbbPo xbbPo : xbbPoList){
            String result = "";
            try {
                result = HttpClientUtil.postRequest("http://xbb.huitaofuwu.com/ph?token=&phone=" + xbbPo.getPhone(), "");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if(jsonObject.getBoolean("success")){
                xbbPo.setUpdateTime(new Date());
                try {
                    iMoXbbService.saveOrUpdate(xbbPo);
                }catch (Exception e){
                    System.out.println("该会员已经存在："+xbbPo.getPhone());
                }
            }else{
                String res = "当前号已经在取码中";
                if(jsonObject.getString("msg").indexOf(res) == 0){
                    xbbPo.setSuccess("true");
                }else{
                    xbbPo.setSuccess("false");
                }
                jsonObject.put("time", DateUtil.now());
                xbbPo.setInfo(xbbPo.getInfo()+jsonObject.toJSONString());
                xbbPo.setUpdateTime(new Date());
                try {
                    iMoXbbService.saveOrUpdate(xbbPo);
                }catch (Exception e){
                    System.out.println("该会员已经存在："+xbbPo.getPhone());
                }
            }
        }
    }



    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    /**
     * 返回手机号码
     */
//    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    private static String[] telFirst="156".split(",");
    private static String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        second = "0924";
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        if(third.equals("4431")){
            third = "0000";
        }
        return first+second+third;
    }

}
