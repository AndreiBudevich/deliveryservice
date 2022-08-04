package by.deliveryservice.repository;

import by.deliveryservice.model.Order;
import by.deliveryservice.model.Product;

public interface OrderRepository extends Repository<Order> {
    void addProducts(Integer id, Product... products);
    void deleteProducts(Integer id, Product... products);
}
