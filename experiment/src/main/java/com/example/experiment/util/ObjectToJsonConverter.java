package com.example.experiment.util;

import com.example.experiment.annotation.JsonElement;
import com.example.experiment.annotation.JsonSerializable;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class ObjectToJsonConverter {

    public static String convert(Object object) throws IllegalAccessException {
        checkIfSerializable(object);
        return getJsonString(object);
    }

    private static void checkIfSerializable(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Object to convert cannot be null");
        }

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
            throw new IllegalArgumentException("The class "
                    + clazz.getSimpleName()
                    + " is not annotated with JsonSerializable");
        }
    }

    private static String getJsonString(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        Map<String, String> jsonElementMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class)) {
                jsonElementMap.put(
                        field.getAnnotation(JsonElement.class).key(), (String) field.get(object));
            }
        }

        String json = jsonElementMap.entrySet().stream()
                .map(entry -> "\"" + entry.getKey() + "\":\""
                        + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + json + "}";
    }
}
