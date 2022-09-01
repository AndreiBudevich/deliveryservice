package by.deliveryservice.util;

import by.deliveryservice.dto.ProductDto;
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

    public static List<ProductDto> getDtos(Collection<Product> products) {
        return products.stream()
                .map(ProductUtil::createDto)
                .toList();
    }

    public static ProductDto createDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getShop().getName(),
                product.getPrice(), product.getDiscount());
    }
}