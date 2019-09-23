package com.mouse.springbootshiro.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author shilun
 * @since 2019-09-04
 */
@TableName("mo_xbb_user")
@ApiModel(value="MoXbbUserPo对象", description="")
public class MoXbbUserPo extends Model<MoXbbUserPo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id",hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "昵称",hidden = true)
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "uuid",hidden = true)
    @TableField("uuid")
    private String uuid;

    @ApiModelProperty(value = "密码",hidden = true)
    @TableField("pass_word")
    private String passWord;

    @ApiModelProperty(value = "手机号",hidden = true)
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "描述",hidden = true)
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty(value = "参数表901")
    @TableField("status_cd")
    private Integer statusCd;

    @ApiModelProperty(value = "所有者",allowableValues = "我的名字")
    @TableField("owner_id")
    private String ownerId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "充值时间",hidden = true)
    @TableField("cz_time")
    private LocalDateTime czTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "生日",hidden = true)
    @TableField("bas_time")
    private LocalDateTime basTime;

    @ApiModelProperty(value = "接码平台",hidden = true)
    @TableField("platform")
    private String platform;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    @TableField("create_time")
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "cdkey")
    @TableField("cdkey")
    private String cdkey;

    @ApiModelProperty(value = "取码次数")
    @TableField("yzm_total")
    private Integer yzmTotal;

    @ApiModelProperty(value = "field1",hidden = true)
    @TableField("field1")
    private String field1;

    @ApiModelProperty(value = "field2",hidden = true)
    @TableField("field2")
    private String field2;

    @ApiModelProperty(value = "info",hidden = true)
    @TableField("info")
    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(Integer statusCd) {
        this.statusCd = statusCd;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDateTime getCzTime() {
        return czTime;
    }

    public void setCzTime(LocalDateTime czTime) {
        this.czTime = czTime;
    }

    public LocalDateTime getBasTime() {
        return basTime;
    }

    public void setBasTime(LocalDateTime basTime) {
        this.basTime = basTime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getCdkey() {
        return cdkey;
    }

    public void setCdkey(String cdkey) {
        this.cdkey = cdkey;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public Integer getYzmTotal() {
        return yzmTotal;
    }

    public void setYzmTotal(Integer yzmTotal) {
        this.yzmTotal = yzmTotal;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "MoXbbUserPo{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", uuid='" + uuid + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phone='" + phone + '\'' +
                ", remarks='" + remarks + '\'' +
                ", statusCd=" + statusCd +
                ", ownerId='" + ownerId + '\'' +
                ", czTime=" + czTime +
                ", basTime=" + basTime +
                ", platform='" + platform + '\'' +
                ", createTime=" + createTime +
                ", cdkey='" + cdkey + '\'' +
                ", yzmTotal=" + yzmTotal +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                '}';
    }
}
