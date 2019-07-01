package xstream.POJO;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.io.Serializable;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: billHead
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/6/3 10:38
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/6/3        shilun           v1.0.0               创建
 */
@XStreamAlias("billhead")
public class BillHead extends Parent{

    private String code;

    private String name;

    private String shortname = "";

    @XStreamAlias("pk_custclass")
    private String pkCustclass;

    private String issupplier;

    @XStreamAlias("pk_supplier")
    private String pkSupplier;

    private String custprop;

    @XStreamAlias("pk_financeorg")
    private String pkFinanceorg;

    private String isfreecust;

    @XStreamAlias("pk_country")
    private String pkCountry;

    @XStreamAlias("pk_timezone")
    private String pkTimezone;

    @XStreamAlias("pk_format")
    private String pkFormat;

    private String frozenflag;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getPkCustclass() {
        return pkCustclass;
    }

    public void setPkCustclass(String pkCustclass) {
        this.pkCustclass = pkCustclass;
    }

    public String getIssupplier() {
        return issupplier;
    }

    public void setIssupplier(String issupplier) {
        this.issupplier = issupplier;
    }

    public String getPkSupplier() {
        return pkSupplier;
    }

    public void setPkSupplier(String pkSupplier) {
        this.pkSupplier = pkSupplier;
    }

    public String getCustprop() {
        return custprop;
    }

    public void setCustprop(String custprop) {
        this.custprop = custprop;
    }

    public String getPkFinanceorg() {
        return pkFinanceorg;
    }

    public void setPkFinanceorg(String pkFinanceorg) {
        this.pkFinanceorg = pkFinanceorg;
    }

    public String getIsfreecust() {
        return isfreecust;
    }

    public void setIsfreecust(String isfreecust) {
        this.isfreecust = isfreecust;
    }

    public String getPkCountry() {
        return pkCountry;
    }

    public void setPkCountry(String pkCountry) {
        this.pkCountry = pkCountry;
    }

    public String getPkTimezone() {
        return pkTimezone;
    }

    public void setPkTimezone(String pkTimezone) {
        this.pkTimezone = pkTimezone;
    }

    public String getPkFormat() {
        return pkFormat;
    }

    public void setPkFormat(String pkFormat) {
        this.pkFormat = pkFormat;
    }

    public String getFrozenflag() {
        return frozenflag;
    }

    public void setFrozenflag(String frozenflag) {
        this.frozenflag = frozenflag;
    }
}
