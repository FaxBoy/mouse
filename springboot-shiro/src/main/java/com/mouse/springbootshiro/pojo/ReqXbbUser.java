package com.mouse.springbootshiro.pojo;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: ReqXbbUser
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/9/19 13:49
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/9/19        shilun           v1.0.0               创建
 */
public class ReqXbbUser extends Ipage{

    private String ownerId;

    private Integer statusCd;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(Integer statusCd) {
        this.statusCd = statusCd;
    }

    @Override
    public String toString() {
        return "ReqXbbUser{" +
                "ownerId='" + ownerId + '\'' +
                ", statusCd=" + statusCd +
                '}';
    }
}
