package com.zhilianhua.ssicp.redis;

import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RedisBaseService {

    private static final String BASE_PREFIX = "ssicp";
    public static final String KEY_SPLITTER = "::";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setValue(String key, Object value) {
        if (StringUtils.isNoneBlank(key) && Objects.nonNull(value))
            redisTemplate.opsForValue().set(getRedisKey(key), JSON.toJSONString(value));
    }

    public <T> T getValue(String key, Class<T> tClass) {
        String s = redisTemplate.opsForValue().get(getRedisKey(key));
        if (StringUtils.isNoneBlank(s)) {
            return JSON.parseObject(s, tClass);
        }
        return null;
    }

    public void deleteValue(String key) {
        redisTemplate.delete(getRedisKey(key));
    }

    public void setHashValue(String key, String field, Object value) {
        if (StringUtils.isNoneBlank(key) && StringUtils.isNoneBlank(field) && Objects.nonNull(value))
            redisTemplate.opsForHash().put(getRedisKey(key), field, JSON.toJSONString(value));
    }

    public <T> T getHashValue(String key, String field, Class<T> tClass) {
        HashOperations<String, String, String> stringStringObjectHashOperations = redisTemplate.opsForHash();
        String s = stringStringObjectHashOperations.get(getRedisKey(key), field);
        if (StringUtils.isNoneBlank(s)) {
            return JSON.parseObject(s, tClass);
        }
        return null;
    }

    public void deleteHashValue(String key, String field) {
        HashOperations<String, String, String> stringStringObjectHashOperations = redisTemplate.opsForHash();
        stringStringObjectHashOperations.delete(getRedisKey(key), field);
    }

    public void setHash(String key, String field, Object value) {
        if (StringUtils.isNoneBlank(key) && StringUtils.isNoneBlank(field) && Objects.nonNull(value))
            redisTemplate.opsForHash().put(getRedisKey(key), field, JSON.toJSONString(value));
    }

    public <T> Map<String, T> getHash(String key, Class<T> tClass) {
        HashOperations<String, String, String> stringObjectObjectHashOperations = redisTemplate.opsForHash();
        Map<String, String> entries = stringObjectObjectHashOperations.entries(getRedisKey(key));
        Map<String, T> result = new HashMap();
        entries.forEach((key1, value1) -> {
            result.put(key1, JSON.parseObject(value1, tClass));
        });
        return result;
    }

    public <T> Map<String, T> getHashWithFieldPrefix(String key, String fieldPrefix, Class<T> tClass) {
        HashOperations<String, String, String> stringObjectObjectHashOperations = redisTemplate.opsForHash();

        ScanOptions scanOptions = ScanOptions.scanOptions()
                .match(fieldPrefix + "*") // 匹配以特定前缀开头的键
                .count(1000)         // 返回的最大键数量
                .build();
        Cursor<Map.Entry<String, String>> cursor = stringObjectObjectHashOperations.scan(getRedisKey(key), scanOptions);
        Map<String, T> result = new HashMap();
        while (cursor.hasNext()) {
            Map.Entry<String, String> entry = cursor.next();
            String field = entry.getKey();
            String value = entry.getValue();
            String[] split = field.split(KEY_SPLITTER);
            result.put(split[1], JSON.parseObject(value, tClass));
        }
        cursor.close();
        return result;
    }

    public String getRedisKey(String key) {
        return BASE_PREFIX + KEY_SPLITTER + key;
    }

    public Object scan() {
        Cursor<String> scan = redisTemplate.scan(ScanOptions.scanOptions().build());
        return null;

    }
}
