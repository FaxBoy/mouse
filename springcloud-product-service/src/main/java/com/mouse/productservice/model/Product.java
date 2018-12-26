package com.mouse.productservice.model;

import java.io.Serializable;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName Product
 * @Description TODO
 * @date 2018/9/14 11:02
 */
public class Product implements Serializable {

    private int id;

    private String name;

    private int price;

    private int store;

    public Product(int id,String name,int price,int store){
        this.id = id;
        this.name = name;
        this.price = price;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }
}
