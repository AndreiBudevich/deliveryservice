package by.deliveryservice.service;

import by.deliveryservice.model.Product;

import java.util.List;

public interface ProductService {

    Product get(int id);

    void delete(int id);

    List<Product> getAll();

    void addCategory(int id, int categoryId);

    void deleteCategory(int id, int categoryId);

    Product save(Product product, int shopId);

    List<Product> getAllWithFilter(String nameContains, String descriptionContains, String shopNameContains, Long priceFrom, Long priceUpTo, Integer discountFrom,
                                   Integer discountUpTo, String[] idsCategories);
}
