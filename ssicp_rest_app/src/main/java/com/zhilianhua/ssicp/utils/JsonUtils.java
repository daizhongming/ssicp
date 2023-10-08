package com.zhilianhua.ssicp.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {
    public static JSONObject parse(String filename) {
        try (InputStream resourceAsStream = JsonUtils.class.getResourceAsStream("/json/" + filename)) {
            return JSON.parseObject(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONArray parseArray(String filename) {
        try (InputStream resourceAsStream = JsonUtils.class.getResourceAsStream("/json/" + filename)) {
            return JSON.parseArray(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
