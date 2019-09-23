package com.mouse.springbootshiro.test;

import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: InsertData
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/7/11 14:58
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/7/11        shilun           v1.0.0               创建
 */
public class InsertData {

    public static void main(String[] args) {
//        for (int i = 1; i <=200; i++) {
//            new MyThread().start();
//        }

        BigDecimal a = new BigDecimal(321);
        BigDecimal b = new BigDecimal(100);

        System.out.println(a.subtract(b)==BigDecimal.ZERO);

        System.out.println(a.compareTo(b));

    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * @Title: randomLonLat
     * @Description: 在矩形内随机生成经纬度
     * @param MinLon：最新经度  MaxLon： 最大经度   MinLat：最新纬度   MaxLat：最大纬度    type：设置返回经度还是纬度
     * @return
     * @throws
     */
    public static String randomLonLat(double MinLon, double MaxLon, double MinLat, double MaxLat, String type) {
        Random random = new Random();
        BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
        String lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();// 小数后6位
        db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
        String lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();
        if (type.equals("Lon")) {
            return lon;
        } else {
            return lat;
        }
    }

}
