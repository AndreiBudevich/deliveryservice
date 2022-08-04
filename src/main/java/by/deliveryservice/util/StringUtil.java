package by.deliveryservice.util;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Locale;

@UtilityClass
public class StringUtil {
    public static String[] getSplit(String stringEntity, String regex) {
        return stringEntity.split(regex);
    }

    public static boolean contains(String actualString, String expectedString) {
        return actualString.toLowerCase(Locale.ROOT).contains(expectedString.toLowerCase(Locale.ROOT));
    }

    public static boolean equals(String actualString, String expectedString) {
        return actualString.toLowerCase(Locale.ROOT).equals(expectedString.toLowerCase(Locale.ROOT));
    }

    public static <T> String stringBuilderCollection(Collection<T> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        collection.forEach(o ->
                stringBuilder.append("\n").append(o.toString()));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
