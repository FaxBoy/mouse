package com.mouse.springbootsecurityjwt.model;

import java.io.Serializable;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName AjaxResponseBody
 * @Description TODO
 * @date 2018/9/14 11:02
 */
public class AjaxResponseBody implements Serializable {
    private String status;
    private String msg;
    private Object result;
    private String jwtToken;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
