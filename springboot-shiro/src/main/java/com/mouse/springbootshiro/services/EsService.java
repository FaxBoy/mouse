package com.mouse.springbootshiro.services;

import com.alibaba.fastjson.JSONArray;
import com.mouse.springbootshiro.pojo.Location;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: EsService
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/7/31 18:23
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/7/31        shilun           v1.0.0               创建
 */
public interface EsService {

    void addLocation(Location location);

    JSONArray search(String storeName);

    void create(String index, String id, String jsonStr);

}
