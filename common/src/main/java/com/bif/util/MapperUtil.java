package com.bif.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapperUtil {

    private static final Logger log = LoggerFactory.getLogger(MapperUtil.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T mapper(Object from, Class<? extends T> tClass) {
        return MAPPER.convertValue(from, tClass);
    }

    public static String toJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("cant transfer to json str", e);
            throw new RuntimeException();
        }
    }
}
