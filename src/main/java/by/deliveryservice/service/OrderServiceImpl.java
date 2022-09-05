package by.deliveryservice.service;

import by.deliveryservice.model.Order;
import by.deliveryservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static by.deliveryservice.util.validation.ValidationUtil.isShipped;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public List<Order> getAllByClientId(int clientId) {
        return orderRepository.getAllByClientId(clientId);
    }

    @Override
    public Order get(int id) {
        return Objects.requireNonNull(getById(id));
    }

    @Override
    public void delete(int id) {
        isShipped(getById(id));
        orderRepository.delete(id);
    }

    @Override
    @Transactional
    public Order save(Order order, int clientId) {
        isShipped(order);
        return orderRepository.save(order, clientId);
    }

    @Override
    @Transactional
    public void ship(int id) {
        Order order = getById(id);
        isShipped(order);
        Objects.requireNonNull(order).setShipped(true);
    }

    private Order getById(int id) {
        return orderRepository.get(id).orElse(null);
    }
}
