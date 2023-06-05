package com.cy.platform.common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * JackJson工具类
 *
 * @author 56807
 */
@Slf4j
public final class JackJsonUtils {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    /**
     * 对象转JSON字符串
     *
     * @param obj 对象
     * @return JSON字符串
     */
    public static String toJsonString(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    /**
     * JSON支付串转对象
     *
     * @param value json字符串
     * @param cls   对象类型
     * @param <T>   T
     * @return 对象
     */
    public static <T> T readValue(String value, Class<T> cls) {
        try {
            return OBJECT_MAPPER.readValue(value, cls);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private JackJsonUtils() {
        throw new UnsupportedOperationException();
    }
}
