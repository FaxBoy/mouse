package com.mouse.springbootshiro.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.elasticsearch.common.geo.GeoPoint;
import org.jboss.logging.Field;

import java.util.Date;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: Location
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/7/31 18:16
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/7/31        shilun           v1.0.0               创建
 */
@Data
public class Location {

//    "address" : {
//        "type" : "keyword",
//                "ignore_above" : 200
//    },
//            "create_date" : {
//        "type" : "date",
//                "format" : "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis"
//    },
//            "images" : {
//        "type" : "text"
//    },
//            "location" : {
//        "type" : "geo_point"
//    },
//            "type_cd" : {
//        "type" : "text"
//    },
//            "type_name" : {
//        "type" : "keyword",
//                "ignore_above" : 20
//    },
//            "user_id" : {
//        "type" : "text"
//    },
//            "user_name" : {
//        "type" : "keyword",
//                "ignore_above" : 50
//    }

    private String uuid;

    private String address;

    private Date create_date;

    private String images;

    private JSONObject location;

    private String type_cd;

    private String type_name;

    private String user_id;

    private String user_name;

}
