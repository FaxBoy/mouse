package com.mouse.feign.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName ProductOrder
 * @Description TODO
 * @date 2018/9/14 11:02
 */
public class ProductOrder implements Serializable {

    private int id;

    private String productName;

    private String orderNo;

    private int price;

    private Date createTime;

    private int userId;

    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
