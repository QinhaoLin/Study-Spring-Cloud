package top.linqinhao.order_service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

    private final static  ObjectMapper objectMapper = new ObjectMapper();

    /**
     * JSON字符串转JsonNode对象的方法
     * @param str
     * @return
     */
    public static JsonNode str2JsonNode(String str){
        try {
            return objectMapper.readTree(str);
        } catch (IOException e) {
            return null;
        }
    }
}
