package by.deliveryservice.service;

import by.deliveryservice.model.Order;
import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.model.Storage;
import by.deliveryservice.repository.OrderDetailRepository;
import by.deliveryservice.repository.OrderRepository;
import by.deliveryservice.repository.StorageRepository;

import java.util.List;
import java.util.Objects;

import static by.deliveryservice.util.validation.ValidationUtil.isShipped;

public class AbstractOrderService {

    protected OrderRepository orderRepository;
    protected OrderDetailRepository orderDetailRepository;
    protected StorageRepository storageRepository;

    public AbstractOrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, StorageRepository storageRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.storageRepository = storageRepository;
    }

    protected void ship(int id) {
        Order order = getById(id);
        isShipped(order);
        List<OrderDetail> all = orderDetailRepository.getAllByOrderIdWithProduct(id);
        all.forEach(oD -> recalculationStorage(oD.getQuantity(), oD.getProduct().getId()));
        Objects.requireNonNull(order).setShipped(true);
    }

    protected Order getById(int id) {
        return orderRepository.get(id).orElse(null);
    }

    private void recalculationStorage(int shippedQuantity, int productId) {
        Storage storage = storageRepository.getByProductId(productId).orElse(null);
        assert storage != null;
        setQuantity(storage, shippedQuantity);
    }

    protected void setQuantity(Storage storage, int shippedQuantity) {
        Integer oldQuantity = storage.getQuantity();
        Integer newQuantity = oldQuantity - shippedQuantity;
        storage.setQuantity(newQuantity);
    }
}
