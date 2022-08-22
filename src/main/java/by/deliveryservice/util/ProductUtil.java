package by.deliveryservice.util;

import by.deliveryservice.model.BaseEntity;
import by.deliveryservice.model.Category;
import by.deliveryservice.model.Product;
import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class ProductUtil {

    public static List<Product> filteringByCategory(Collection<Product> products, String[] actualStringIdsCategories) {
        return products.stream()
                .filter(product -> containsCategories(product.getCategories(), actualStringIdsCategories))
                .toList();
    }

    public static boolean containsCategories(Set<Category> expectedCategories, String[] actualStringIdsCategories) {
        List<Integer> expectedIdsCategories = expectedCategories.stream().map(BaseEntity::getId).toList();
        List<Integer> actualIdsCategories = Arrays.stream(actualStringIdsCategories).map(Integer::parseInt).toList();
        return !Collections.disjoint(expectedIdsCategories, actualIdsCategories);
    }
}

