package by.deliveryservice.util;

import lombok.experimental.UtilityClass;

import java.util.Locale;

@UtilityClass
public class StringUtil {
    public static String[] getSplit(String stringEntity, String regex) {
        return stringEntity.split(regex);
    }

    public static boolean contains(String actualString, String expectedString) {
        return actualString.toLowerCase(Locale.ROOT).contains(expectedString.toLowerCase(Locale.ROOT));
    }
}
