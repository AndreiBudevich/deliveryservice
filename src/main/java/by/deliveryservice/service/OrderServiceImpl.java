package by.deliveryservice.service;

import by.deliveryservice.model.Order;
import by.deliveryservice.repository.OrderDetailRepository;
import by.deliveryservice.repository.OrderRepository;
import by.deliveryservice.repository.StorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.deliveryservice.util.validation.ValidationUtil.checkNotFoundWithId;
import static by.deliveryservice.util.validation.ValidationUtil.isShipped;

@Service
public class OrderServiceImpl extends AbstractOrderService implements OrderService {

    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, StorageRepository storageRepository) {
        super(orderRepository, orderDetailRepository, storageRepository);
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
        return checkNotFoundWithId(getById(id), id);
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
        super.ship(id);
    }
}