package com.mouse.springbootshiro.test;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: InsertData
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/7/11 14:58
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/7/11        shilun           v1.0.0               创建
 */
public class InsertData {

    public static void main(String[] args) {
        for (int i = 1; i <=20; i++) {
            new MyThread().start();
        }

    }
}
