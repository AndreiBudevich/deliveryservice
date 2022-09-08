package by.deliveryservice.service;

import by.deliveryservice.model.Order;
import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.model.Storage;
import by.deliveryservice.repository.OrderDetailRepository;
import by.deliveryservice.repository.OrderRepository;
import by.deliveryservice.repository.StorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static by.deliveryservice.util.validation.ValidationUtil.checkNotFoundWithId;
import static by.deliveryservice.util.validation.ValidationUtil.isShipped;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final StorageRepository storageRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, StorageRepository storageRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.storageRepository = storageRepository;
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
        Order order = getById(id);
        isShipped(order);
        List<OrderDetail> all = orderDetailRepository.getAllByOrderIdWithProduct(id);
        all.forEach(oD -> recalculationStorage(oD.getQuantity(), oD.getProduct().getId()));
        Objects.requireNonNull(order).setShipped(true);
    }

    private Order getById(int id) {
        return orderRepository.get(id).orElse(null);
    }

    private void recalculationStorage(int shippedQuantity, int productId) {
        Storage storage = storageRepository.getByProductId(productId).orElse(null);
        assert storage != null;
        Integer oldQuantity = storage.getQuantity();
        Integer newQuantity = oldQuantity - shippedQuantity;
        storage.setQuantity(newQuantity);
    }
}