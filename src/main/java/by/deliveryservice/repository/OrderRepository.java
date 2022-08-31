package by.deliveryservice.repository;

import by.deliveryservice.model.Order;

public interface OrderRepository extends BaseRepository<Order> {

    Order save(Order order, int clientId);
}
