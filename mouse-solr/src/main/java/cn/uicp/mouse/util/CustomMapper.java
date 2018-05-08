package cn.uicp.mouse.util;

import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.SerializationFeature;

public class CustomMapper extends ObjectMapper{
	public CustomMapper() {
       // this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置 SerializationFeature.FAIL_ON_EMPTY_BEANS 为 false
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}
