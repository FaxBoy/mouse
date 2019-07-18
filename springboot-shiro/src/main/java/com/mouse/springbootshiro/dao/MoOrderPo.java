package com.mouse.springbootshiro.dao;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author shilun
 * @since 2019-07-15
 */
@TableName("mo_order")
@ApiModel(value="MoOrderPo对象", description="")
public class MoOrderPo extends Model<MoOrderPo> {

    private static final long serialVersionUID = 1L;

    @TableId("uuid")
    private String uuid;

    @TableField("user_id")
    private String userId;

    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty(value = "金额")
    @TableField("amount")
    private BigDecimal amount;

    @TableField("type_cd")
    private String typeCd;

    @TableField("status_cd")
    private String statusCd;

    @TableField("payment_type_cd")
    private String paymentTypeCd;

    @ApiModelProperty(value = "完成时间")
    @TableField("complete_time")
    private LocalDateTime completeTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    @TableField("regist_date")
    private LocalDateTime registDate;

    @ApiModelProperty(value = "描述")
    @TableField("remark")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("version")
    private String version;

    @TableField("deleted")
    private Integer deleted;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTypeCd() {
        return typeCd;
    }

    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public String getPaymentTypeCd() {
        return paymentTypeCd;
    }

    public void setPaymentTypeCd(String paymentTypeCd) {
        this.paymentTypeCd = paymentTypeCd;
    }

    public LocalDateTime getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(LocalDateTime completeTime) {
        this.completeTime = completeTime;
    }

    public LocalDateTime getRegistDate() {
        return registDate;
    }

    public void setRegistDate(LocalDateTime registDate) {
        this.registDate = registDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    protected Serializable pkVal() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return "MoOrderPo{" +
        "uuid=" + uuid +
        ", userId=" + userId +
        ", orderNo=" + orderNo +
        ", amount=" + amount +
        ", typeCd=" + typeCd +
        ", statusCd=" + statusCd +
        ", paymentTypeCd=" + paymentTypeCd +
        ", completeTime=" + completeTime +
        ", registDate=" + registDate +
        ", remark=" + remark +
        ", updateTime=" + updateTime +
        ", version=" + version +
        ", deleted=" + deleted +
        "}";
    }
}
