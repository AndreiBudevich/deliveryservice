package by.deliveryservice.repository;

import by.deliveryservice.model.Category;
import by.deliveryservice.model.Product;

import java.util.List;

public interface ProductRepository extends Repository<Product> {

    void addCategories(Integer id, Category... categories);

    List<Product> findByAttributes(String... attributes);

    List<Product> getSortPrice();
}
