package by.deliveryservice.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.deliveryservice.util.FileUtil.getFileInResource;

@UtilityClass
public class JsonUtil {

    private static ObjectMapper mapper;

    public static void setMapper(ObjectMapper mapper) {
        JsonUtil.mapper = mapper;
    }

    public static <K, V> void writeEntity(String nameFile, Map<K, V> map) {
        try {
            mapper.writeValue(getFileInResource(nameFile), map);
        } catch (IOException e) {
            throw new IllegalStateException("Invalid write file " + nameFile + " to JSON:\n", e);
        }
    }

    public static <K, V> Map<K, V> mapReadValues(String nameFile, Class<K> clazzKey, Class<V> clazzValue) {
        try {
            TypeFactory typeFactory = mapper.getTypeFactory();
            MapType mapType = typeFactory.constructMapType(HashMap.class, clazzKey, clazzValue);
            return mapper.readValue(getFileInResource(nameFile), mapType);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read file " + nameFile + " from JSON:\n", e);
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> List<T> readValues(String json, Class<T> clazz) {
        ObjectReader reader = mapper.readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> String writeValue(T obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }
}


