package by.deliveryservice.service;

import by.deliveryservice.model.Order;
import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.OrderDetailRepository;
import by.deliveryservice.repository.OrderRepository;
import by.deliveryservice.repository.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public List<OrderDetail> getAllByOrderIdWithProduct(int orderId) {
        return orderDetailRepository.getAllByOrderIdWithProduct(orderId);
    }

    @Override
    public OrderDetail get(int id) {
        return orderDetailRepository.get(id).orElse(null);
    }

    @Override
    public void delete(int clientId, int orderId, int id) {
        orderDetailRepository.delete(id);
        orderTotalCostRecalculation(orderDetailRepository.getAllByOrderId(orderId), orderId, clientId);
    }

    @Override
    @Transactional
    public void update(OrderDetail orderDetail, int orderId, int productId, int clientId) {
        saveOrderDetailAndOrderTotalCostRecalculation(orderDetail, orderId, productId, clientId);
    }

    @Override
    @Transactional
    public void addProduct(int clientId, int orderId, int productId) {
        OrderDetail orderDetail = orderDetailRepository.getByOrderIdByProductId(orderId, productId).orElse(null);
        if (orderDetail == null) {
            Product product = productRepository.get(productId).orElse(null);
            if (product == null) {
                throw new IllegalArgumentException();
            }
            orderDetail = new OrderDetail(null, null, product.getPrice(), 0, 0L);
        }

        int quantity = orderDetail.getQuantity();
        quantity++;
        orderDetail.setQuantity(quantity);
        saveOrderDetailAndOrderTotalCostRecalculation(orderDetail, orderId, productId, clientId);
    }

    @Override
    @Transactional
    public void deleteProduct(int clientId, int orderId, int productId) {
        OrderDetail orderDetail = orderDetailRepository.getByOrderIdByProductId(orderId, productId).orElse(null);
        if (orderDetail == null) {
            throw new DataIntegrityViolationException("product not found by order " + orderId);
        }

        int quantity = orderDetail.getQuantity();
        quantity--;
        if (quantity < 1) {
            orderDetailRepository.delete(orderDetail.getId());
        } else {
            orderDetail.setQuantity(quantity);
            orderDetailRepository.save(orderDetail, orderId, productId);
        }
        orderTotalCostRecalculation(orderDetailRepository.getAllByOrderId(orderId), orderId, clientId);
    }

    private Long getActualTotalCostOrder(List<OrderDetail> orderDetails) {
        return orderDetails.stream().mapToLong(OrderDetail::getAmount).sum();
    }

    private void orderTotalCostRecalculation(List<OrderDetail> orderDetails, int orderId, int clientId) {
        Order order = orderRepository.get(orderId).orElse(null);
        if (order != null) {
            order.setTotalCost(getActualTotalCostOrder(orderDetails));
            orderRepository.save(order, clientId);
        }
    }

    private void saveOrderDetailAndOrderTotalCostRecalculation(OrderDetail orderDetail, int orderId, int productId, int clientId) {
        orderDetailRepository.save(orderDetail, orderId, productId);
        orderTotalCostRecalculation(orderDetailRepository.getAllByOrderId(orderId), orderId, clientId);
    }
}




