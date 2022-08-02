package by.deliveryservice.view;

import java.util.List;

public class EntityPrint {

    public static void print(Object object) {
        if (object instanceof List) {
            List<Object> list = (List<Object>) object;
            for (Object o : list) {
                System.out.println(o);
            }
        }
    }
}
