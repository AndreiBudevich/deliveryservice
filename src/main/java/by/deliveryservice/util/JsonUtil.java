package by.deliveryservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.deliveryservice.util.FileUtil.getFileInResource;

@UtilityClass
public class JsonUtil {

    private static final ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    public static <K, V> void writeEntity(Map<K, V> map, String nameFile) {
        try {
            mapper.writeValue(getFileInResource(nameFile), map);
        } catch (IOException e) {
            throw new IllegalStateException("Invalid write file " + nameFile + " to JSON:\n", e);
        }
    }

    public static <K, V> Map<K, V> readValues(String nameFile, Class<K> clazzKey, Class<V> clazzValue) {
        try {
            TypeFactory typeFactory = mapper.getTypeFactory();
            MapType mapType = typeFactory.constructMapType(HashMap.class, clazzKey, clazzValue);
            return mapper.readValue(getFileInResource(nameFile), mapType);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read file " + nameFile + " from JSON:\n", e);
        }
    }
}


