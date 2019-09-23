package com.mouse.springbootshiro.services.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mouse.springbootshiro.dao.MoXbbPo;
import com.mouse.springbootshiro.dao.MoXbbUserPo;
import com.mouse.springbootshiro.job.ScheduledTask;
import com.mouse.springbootshiro.mapper.MoXbbMapper;
import com.mouse.springbootshiro.mapper.MoXbbUserMapper;
import com.mouse.springbootshiro.services.IMoXbbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mouse.springbootshiro.util.HttpClientUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shilun
 * @since 2019-09-04
 */
@Service
public class MoXbbUserServiceImpl extends ServiceImpl<MoXbbUserMapper, MoXbbUserPo> implements IMoXbbUserService {

    private static final String PROJECT_ID ="204e7b83b044483ab05255e9e1dd5cbb";

    @Value("${feigou.host}")
    private String FEIGOU_HOST;

    private static String token = "" ;

    public static Logger logger = LoggerFactory.getLogger(MoXbbServiceImpl.class);

    @Autowired
    private MoXbbUserMapper xbbUserMapper;

    @Override
    public List<MoXbbUserPo> list(MoXbbUserPo xbbUserPo) {
        return xbbUserMapper.selectListByDao(xbbUserPo);
    }

    @Override
    public IPage<MoXbbUserPo> selectPageVo(Page<MoXbbUserPo> page,@Param(Constants.WRAPPER) Wrapper<MoXbbUserPo> queryWrapper) {
        return xbbUserMapper.selectPageVo(page,queryWrapper);
    }

    @Override
    public String getCodeByUuid(String uuid) {

        MoXbbUserPo moXbbUserPo = getByUuid(uuid);

        String phone = moXbbUserPo.getPhone();

        String result = "";
        String url = FEIGOU_HOST+"/api/getSms/v1?token="+ ScheduledTask.token+"&projectId="+PROJECT_ID+"&phoneNo="+phone+"&msgType=1";
        try {
            logger.info(url);
            result = HttpClientUtil.postRequest(url, "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String appointPhone(String uuid) {
        String phone = getByUuid(uuid).getPhone();

        String result = "";
        String url = FEIGOU_HOST+"/api/getPhoneNo/v1?token="+ ScheduledTask.token+"&msgOpType=zdsjh&projectId="+PROJECT_ID+"&phoneNo="+phone+"&msgType=1";
        try {
            logger.info(url);
            result = HttpClientUtil.postRequest(url, "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public MoXbbUserPo getByUuid(String Uuid) {
        return xbbUserMapper.getByUuid(Uuid);
    }

    @Override
    public List<MoXbbUserPo> getByOwnerId(String ownerId) {
        return xbbUserMapper.getByOwnerId(ownerId);
    }

    @Override
    public String createBatch(String content, String ownerId) {
        MoXbbUserPo moXbbUserPo1 = new MoXbbUserPo();
        moXbbUserPo1.setOwnerId(ownerId);
        moXbbUserPo1.setStatusCd(0);
        List<MoXbbUserPo> list = this.list(moXbbUserPo1);
        int max = 200;
        if(list.size()>=max){
            return "当前key中白号数量超过"+max+"，无法导入！";
        }

        int count = 0;
        String [] strs = content.split(",");
        for (String phone:strs){

            MoXbbUserPo moXbbUserPo = new MoXbbUserPo();

            String [] userids = phone.split("-");
            if(userids.length > 1){
                moXbbUserPo.setUserId(userids[1]);
                phone = userids[0];
            }else {
                moXbbUserPo.setUserId(phone);
            }
            if(phone.length() != 11){
                continue;
            }

            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("time", DateUtil.now());
            jsonObject.put("info","初次导入");
            jsonObject.put("operator",ownerId);
            jsonArray.add(jsonObject);
            moXbbUserPo.setInfo(jsonArray.toJSONString());

            moXbbUserPo.setPhone(phone);
            moXbbUserPo.setStatusCd(0);
            moXbbUserPo.setOwnerId(ownerId);
            moXbbUserPo.setUuid(IdUtil.simpleUUID());
            moXbbUserPo.setCdkey(IdUtil.simpleUUID());
            moXbbUserPo.setCreateTime(new Date());
            moXbbUserPo.setPassWord("-");
            moXbbUserPo.setYzmTotal(0);
            try {
                xbbUserMapper.insert(moXbbUserPo);
                count++;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "本次成功导入："+count+"条，失败："+(strs.length-count)+"条";
    }

}
