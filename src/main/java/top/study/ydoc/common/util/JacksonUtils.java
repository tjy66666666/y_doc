package top.study.ydoc.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author tjy
 * @date 2024/04/14
 */
public class JacksonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转换为 JSON 字符串
     */
    public static String toJSONString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            // 处理异常，这里简单地打印错误日志
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将 JSON 字符串转换为指定类型的对象
     */
    public static <T> T toObject(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (IOException e) {
            // 处理异常，这里简单地打印错误日志
            e.printStackTrace();
            return null;
        }
    }
}
