package com.mouse.springbootshiro.services.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.mouse.springbootshiro.dao.MoXbbPo;
import com.mouse.springbootshiro.mapper.MoXbbMapper;
import com.mouse.springbootshiro.pojo.XbbQuery;
import com.mouse.springbootshiro.services.IMoXbbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mouse.springbootshiro.util.HttpClientUtil;
import com.mouse.springbootshiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shilun
 * @since 2019-08-26
 */
@Service
public class MoXbbServiceImpl extends ServiceImpl<MoXbbMapper, MoXbbPo> implements IMoXbbService {

    public static Logger logger = LoggerFactory.getLogger(MoXbbServiceImpl.class);

    @Autowired
    private MoXbbMapper moXbbMapper;

    @Override
    public List<MoXbbPo> xbbQuery(XbbQuery xbbQuery){
        return moXbbMapper.xbbQuery(xbbQuery);
    }

    @Override
    public List<MoXbbPo> successList(XbbQuery xbbQuery){
        return moXbbMapper.successList(xbbQuery);
    }

    @Override
    public String updateList(List<MoXbbPo> xbbPoList) {
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
                    this.saveOrUpdate(xbbPo);
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
                    this.saveOrUpdate(xbbPo);
                }catch (Exception e){
                    System.out.println("该会员已经存在："+xbbPo.getPhone());
                }
            }
        }
        return "";
    }

    @Override
    public boolean dbReptile(String telFirst,String telSecond,boolean isRandom) {

        String phone = getTel(telFirst,telSecond,isRandom);

        XbbQuery xbbQuery = new XbbQuery();
        xbbQuery.setPhone(phone);

        List<MoXbbPo> list = this.xbbQuery(xbbQuery);
        if(null != list && list.size()>0){
            System.out.println("存在如下");
            System.out.println(list.get(0).toString());
            return false;
        }


        String result = "";
        try {
            result = HttpClientUtil.postRequest("http://xbb.huitaofuwu.com/ph?token=&phone=" + phone, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        logger.info(jsonObject.toJSONString());

        if(jsonObject.containsKey("msg")){
            if(jsonObject.get("msg")!= null && jsonObject.getString("msg").indexOf("该手机号不存在")==0){
                return false;
            }
        }

        if(jsonObject.getBoolean("success")){
            MoXbbPo moXbbPo = new MoXbbPo();
            moXbbPo.setInfo(jsonObject.toJSONString());
            moXbbPo.setPhone(phone);
            moXbbPo.setStatus(0);
            moXbbPo.setOwner("adminKbb");
            moXbbPo.setCreateTime(new Date());
            moXbbPo.setSuccess(jsonObject.get("success").toString());
            try {
                this.save(moXbbPo);
                return true;
            }catch (Exception e){
                logger.error(e.getMessage(),e);
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
            }else if(jsonObject.getString("msg").indexOf(resyue) == 0){
                moXbbPo.setStatus(8);//您的账户余额不足
                moXbbPo.setSuccess("false");
            }else{
                moXbbPo.setStatus(99);//号码存在但是不能用
                moXbbPo.setSuccess("false");
            }
            try {
                this.save(moXbbPo);
                return true;
            }catch (Exception e){
                logger.error(e.getMessage(),e);
                System.out.println("该会员已经存在："+phone);
            }
        }
        return false;
    }


    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    /**
     * 返回手机号码
     */
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    private static String getTel(String first,String second,boolean isRandom) {
        int index=getNum(0,telFirst.length-1);
        if(StringUtils.isEmpty(first)){
            first=telFirst[index];
        }
        if(StringUtils.isEmpty(second)){
            second=String.valueOf(getNum(1,888)+10000).substring(1);
        }
        String third = "";
        if(isRandom){
            third=String.valueOf(getNum(1,9100)+10000).substring(1);
        }else{
            third=String.valueOf(getNum(1,9100)+10000).substring(1);
        }
        return first+second+third;
    }

}
