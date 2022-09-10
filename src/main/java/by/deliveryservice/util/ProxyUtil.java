package by.deliveryservice.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@UtilityClass
public class ProxyUtil {

    public static Object getInstance(Class<?> clazz, String nameMethod, Object... args) {
        try {
            Method currentMethod = getCurrentMethod(clazz, nameMethod);
            assert currentMethod != null;
            return currentMethod.invoke(clazz.getDeclaredConstructor().newInstance(), args);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Method getCurrentMethod(Class<?> clazz, String nameMethod) {
        return Arrays.stream(clazz.getMethods()).filter(m -> m.getName().equalsIgnoreCase(nameMethod)).findFirst().orElse(null);
    }
}
