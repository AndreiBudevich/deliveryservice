package by.deliveryservice.repository;

import by.deliveryservice.model.Order;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {

    List<Order> getAllByClientId(int clientId);

    Order save(Order order, int clientId);
}
