package by.deliveryservice.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;

@UtilityClass
public class MethodUtil {

    public static Method getCurrentMethod(Class<?> clazz, String nameMethod) {
        return Arrays.stream(clazz.getMethods()).filter(m -> compareMethodName(nameMethod, m)).findFirst().orElse(null);
    }

    private static boolean compareMethodName(String expectedNameMethod, Method method) {
        String actualMethodName = method.getName().toLowerCase(Locale.ROOT);
        return actualMethodName.contains(expectedNameMethod.toLowerCase(Locale.ROOT));
    }
}
