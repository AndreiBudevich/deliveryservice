package by.deliveryservice.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static by.deliveryservice.util.MethodUtil.getCurrentMethod;

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
}
