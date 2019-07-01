package xstream.POJO;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: Parent
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/6/3 16:30
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/6/3        shilun           v1.0.0               创建
 */
public class Parent {

    @XStreamAlias("pk_group")
    private String pkGroup;

    @XStreamAlias("pk_org")
    private String pkOrg;

    private String enabletate;

    public String getPkGroup() {
        return pkGroup;
    }

    public void setPkGroup(String pkGroup) {
        this.pkGroup = pkGroup;
    }

    public String getPkOrg() {
        return pkOrg;
    }

    public void setPkOrg(String pkOrg) {
        this.pkOrg = pkOrg;
    }

    public String getEnabletate() {
        return enabletate;
    }

    public void setEnabletate(String enabletate) {
        this.enabletate = enabletate;
    }
}
