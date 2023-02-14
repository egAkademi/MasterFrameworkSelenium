package org.selenium.pom.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {
    @Step
    public static <T> T deserializeJson(String fileName, Class<T> T) throws IOException {
        InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is, T);
    }
}
