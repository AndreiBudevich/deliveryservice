package by.deliveryservice.repository;

import by.deliveryservice.model.Order;
import by.deliveryservice.model.Product;

public interface OrderRepository extends BaseRepository<Order> {

    Order save(Order order, int clientId);

    void addProducts(Integer id, Product... products);

    void deleteProducts(Integer id, Product... products);

    void setAddress(Integer id, String deliveryAddress);
}
