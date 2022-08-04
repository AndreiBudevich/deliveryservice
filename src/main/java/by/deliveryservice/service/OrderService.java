package by.deliveryservice.service;

import by.deliveryservice.model.Product;

public interface OrderService {
    void addProducts(Integer id, Product... products);

    void deleteProducts(Integer id, Product... products);
}
