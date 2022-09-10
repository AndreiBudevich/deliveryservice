package by.deliveryservice.service;

import by.deliveryservice.model.Order;
import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.OrderDetailRepository;
import by.deliveryservice.repository.OrderRepository;
import by.deliveryservice.repository.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static by.deliveryservice.util.validation.ValidationUtil.isShipped;

public class AbstractOrderDetailService {

    protected OrderDetailRepository orderDetailRepository;
    protected ProductRepository productRepository;
    protected OrderRepository orderRepository;

    public AbstractOrderDetailService(OrderDetailRepository orderDetailRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    protected void addProduct(int clientId, int orderId, int productId) {
        isShipped(getById(orderId));
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

    protected void deleteProduct(int clientId, int orderId, int productId) {
        isShipped(getById(orderId));
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

    protected void orderTotalCostRecalculation(List<OrderDetail> orderDetails, int orderId, int clientId) {
        Order order = orderRepository.get(orderId).orElse(null);
        if (order != null) {
            order.setTotalCost(getActualTotalCostOrder(orderDetails));
            orderRepository.save(order, clientId);
        }
    }

    protected void saveOrderDetailAndOrderTotalCostRecalculation(OrderDetail orderDetail, int orderId, int productId, int clientId) {
        orderDetailRepository.save(orderDetail, orderId, productId);
        orderTotalCostRecalculation(orderDetailRepository.getAllByOrderId(orderId), orderId, clientId);
    }

    protected Order getById(int id) {
        return orderRepository.get(id).orElse(null);
    }
}
