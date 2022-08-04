package by.deliveryservice.view;

import java.util.List;
import java.util.Map;

public class EntityPrint {

    public static void print(Object object) {
        if (object instanceof List) {
            List<Object> objects = (List<Object>) object;
            objects.forEach(System.out::println);
        }
        if (object instanceof Map) {
            Map<Object, Object> objects = (Map<Object, Object>) object;
            objects.entrySet().forEach(System.out::println);
        }
    }
}
