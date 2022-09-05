package by.deliveryservice.service;

import by.deliveryservice.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAll();

    List<Order> getAllByClientId(int clientId);

    Order get(int id);

    void delete(int id);

    Order save(Order order, int clientId);

    void ship(int id);
}
