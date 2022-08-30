package by.deliveryservice.service;

import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.repository.OrderDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDetail create(OrderDetail orderDetail, int orderId, int productId) {
        OrderDetail orderDetailBD = orderDetailRepository.getAllByOrderIdWithProduct(orderId).stream()
                .filter(od -> od.getProduct().getId() == productId)
                .findFirst()
                .orElse(null);
        if (orderDetailBD == null) {
            return orderDetailRepository.save(orderDetail, orderId, productId);
        }
        int quantity = orderDetailBD.getQuantity();
        quantity++;
        orderDetailBD.setQuantity(quantity);
        return orderDetailRepository.save(orderDetailBD, orderId, productId);
    }
}
