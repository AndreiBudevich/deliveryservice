package by.deliveryservice.repository;

import by.deliveryservice.model.Product;

import java.util.List;

public interface ProductRepository extends Repository<Product> {
    List<Product> getSortPrice();
}
