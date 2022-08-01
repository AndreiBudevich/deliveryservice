package by.deliveryservice.util;

import by.deliveryservice.repository.infile.*;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Locale;

@UtilityClass
public class RepositoryUtil {

    private static final Class<?>[] classes = {InFileCategoryRepository.class, InFileClientRepository.class,
            InFileOrderRepository.class, InFileProductRepository.class, InFileShopRepository.class};

    public static Class<?> getRepositoryClass(String className) {
        return Arrays.stream(classes).filter(c -> compareClassName(className, c)).findFirst().orElse(null);
    }

    private static boolean compareClassName(String expectedClassName, Class<?> clazz) {
        String actualClassName = clazz.getSimpleName().toLowerCase(Locale.ROOT);
        return actualClassName.contains(expectedClassName.toLowerCase(Locale.ROOT));
    }
}
