package com.mouse.springbootshiro.dao;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author shilun
 * @since 2019-08-02
 */
@TableName("mo_gps")
@ApiModel(value="MoGpsPo对象", description="")
public class MoGpsPo extends Model<MoGpsPo> {

    private static final long serialVersionUID = 1L;

    @TableId("uuid")
    private String uuid;

    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "经度")
    @TableField("lng")
    private BigDecimal lng;

    @ApiModelProperty(value = "纬度")
    @TableField("lat")
    private BigDecimal lat;

    @ApiModelProperty(value = "方位")
    @TableField("position")
    private String position;

    @ApiModelProperty(value = "信号量")
    @TableField("mutex")
    private String mutex;

    @ApiModelProperty(value = "地址信息")
    @TableField("address")
    private String address;

    @TableField("type_cd")
    private String typeCd;

    @ApiModelProperty(value = "创建时间")
    @TableField("regist_date")
    private LocalDateTime registDate;

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

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMutex() {
        return mutex;
    }

    public void setMutex(String mutex) {
        this.mutex = mutex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypeCd() {
        return typeCd;
    }

    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    public LocalDateTime getRegistDate() {
        return registDate;
    }

    public void setRegistDate(LocalDateTime registDate) {
        this.registDate = registDate;
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
        return "MoGpsPo{" +
        "uuid=" + uuid +
        ", userId=" + userId +
        ", lng=" + lng +
        ", lat=" + lat +
        ", position=" + position +
        ", mutex=" + mutex +
        ", address=" + address +
        ", typeCd=" + typeCd +
        ", registDate=" + registDate +
        ", version=" + version +
        ", deleted=" + deleted +
        "}";
    }
}
