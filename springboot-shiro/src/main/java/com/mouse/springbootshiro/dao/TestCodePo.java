package com.mouse.springbootshiro.dao;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2019-07-01
 */
@TableName("test_code")
@ApiModel(value="TestCodePo对象", description="")
public class TestCodePo extends Model<TestCodePo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;

    @TableField("userid")
    private Integer userid;

    @TableField("subject_name")
    private String subjectName;

    @ApiModelProperty(value="删除状态",name="deleted",required=true)
    private Integer deleted;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("version")
    private Integer version;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TestCodePo{" +
        "id=" + id +
        ", userid=" + userid +
        ", subjectName=" + subjectName +
        ", deleted=" + deleted +
        ", updateTime=" + updateTime +
        ", version=" + version +
        "}";
    }
}
