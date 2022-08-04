package by.deliveryservice.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;

@UtilityClass
public class ProxyUtil {

    public static Object getInstance(Class <?> clazz, String nameMethod, Object... args) {
        try {
            Method currentMethod = getCurrentMethod(clazz, nameMethod);
            return currentMethod.invoke(clazz.newInstance(), args);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Method getCurrentMethod(Class<?> clazz, String nameMethod) {
        return Arrays.stream(clazz.getMethods()).filter(m -> compareMethodName(nameMethod, m)).findFirst().orElse(null);
    }

    private static boolean compareMethodName(String expectedNameMethod, Method method) {
        String actualMethodName = method.getName().toLowerCase(Locale.ROOT);
        return actualMethodName.contains(expectedNameMethod.toLowerCase(Locale.ROOT));
    }
}
