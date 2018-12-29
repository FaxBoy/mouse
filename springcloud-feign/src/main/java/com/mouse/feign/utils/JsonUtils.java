package com.mouse.feign.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName JsonUtils
 * @Description TODO
 * @date 2018/9/14 11:02
 */
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode str2JsonNode(String str){
        try {
            return objectMapper.readTree(str);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
