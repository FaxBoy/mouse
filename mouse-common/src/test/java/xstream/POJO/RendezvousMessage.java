package xstream.POJO;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import com.thoughtworks.xstream.converters.basic.StringConverter;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: RendezvousMessage
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/6/3 10:08
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/6/3        shilun           v1.0.0               创建
 */

@XStreamAlias("ufinterface")
public class RendezvousMessage {

    public static final String headInfo = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

    @XStreamAsAttribute
    private String account;

    @XStreamAlias("billtype")
    @XStreamAsAttribute
    @XStreamConverter(value= StringConverter.class,ints = {0},strings={"Y", "custsaleclass"})
    private Integer billType;

    @XStreamAlias("businessunitcode")
    @XStreamAsAttribute
    private String businessunitCode;

    @XStreamAlias("filename")
    @XStreamAsAttribute
    private String fileName;

    @XStreamAlias("groupcode")
    @XStreamAsAttribute
    private String groupCode;

    @XStreamAlias("orgcode")
    @XStreamAsAttribute
    private String orgCode;

    @XStreamAsAttribute
    private String receiver;

    @XStreamAlias("roottag")
    @XStreamAsAttribute
    private String rootTag;

    @XStreamAsAttribute
    private String sender;

    @XStreamAsAttribute
    @XStreamConverter(value= BooleanConverter.class, booleans={false}, strings={"Y", "N"})
    private boolean replace;

    @XStreamAsAttribute
    @XStreamConverter(value= BooleanConverter.class, booleans={false}, strings={"Y", "N"})
    private boolean isexchange;

    @XStreamAlias("bill")
    private Bill bill;



    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public static String getHeadInfo() {
        return headInfo;
    }

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBusinessunitCode() {
        return businessunitCode;
    }

    public void setBusinessunitCode(String businessunitCode) {
        this.businessunitCode = businessunitCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getRootTag() {
        return rootTag;
    }

    public void setRootTag(String rootTag) {
        this.rootTag = rootTag;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isReplace() {
        return replace;
    }

    public void setReplace(boolean replace) {
        this.replace = replace;
    }

    public boolean isIsexchange() {
        return isexchange;
    }

    public void setIsexchange(boolean isexchange) {
        this.isexchange = isexchange;
    }
}
