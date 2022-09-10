package by.deliveryservice.service;

import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.repository.OrderDetailRepository;
import by.deliveryservice.repository.OrderRepository;
import by.deliveryservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.deliveryservice.util.validation.ValidationUtil.checkNotFoundWithId;
import static by.deliveryservice.util.validation.ValidationUtil.isShipped;

@Service
public class OrderDetailServiceImpl extends AbstractOrderDetailService implements OrderDetailService {

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        super(orderDetailRepository, productRepository, orderRepository);
    }

    public List<OrderDetail> getAllByOrderIdWithProduct(int orderId) {
        return orderDetailRepository.getAllByOrderIdWithProduct(orderId);
    }

    @Override
    public OrderDetail get(int id) {
        return checkNotFoundWithId(orderDetailRepository.get(id).orElse(null), id);
    }

    @Override
    public void delete(int clientId, int orderId, int id) {
        isShipped(getById(orderId));
        orderDetailRepository.delete(id);
        orderTotalCostRecalculation(orderDetailRepository.getAllByOrderId(orderId), orderId, clientId);
    }

    @Override
    @Transactional
    public void update(OrderDetail orderDetail, int orderId, int productId, int clientId) {
        isShipped(getById(orderId));
        saveOrderDetailAndOrderTotalCostRecalculation(orderDetail, orderId, productId, clientId);
    }

    @Override
    @Transactional
    public void addProduct(int clientId, int orderId, int productId) {
        super.addProduct(clientId, orderId, productId);
    }

    @Override
    @Transactional
    public void deleteProduct(int clientId, int orderId, int productId) {
        super.deleteProduct(clientId, orderId, productId);
    }
}




