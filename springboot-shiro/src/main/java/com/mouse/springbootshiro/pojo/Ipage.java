package com.mouse.springbootshiro.pojo;

import java.io.Serializable;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: Ipage
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/9/19 13:50
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/9/19        shilun           v1.0.0               创建
 */
public class Ipage implements Serializable {

    private Integer limit;

    private Integer page;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Ipage{" +
                "limit=" + limit +
                ", page=" + page +
                '}';
    }
}
