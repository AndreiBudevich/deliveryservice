package by.deliveryservice.service;

import by.deliveryservice.model.Order;
import by.deliveryservice.repository.OrderRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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
        orderRepository.delete(id);
    }

    @Override
    public Order save(Order order, int clientId) {
        return orderRepository.save(order, clientId);
    }

    @Override
    @Transactional
    public void ship(int id) {
        Order order = getById(id);
        if (order != null) {
            if (order.isShipped()) {
                throw new DataIntegrityViolationException("order shipped");
            }
            order.setShipped(true);
        }
    }

    private Order getById(int id) {
        return orderRepository.get(id).orElse(null);
    }
}
