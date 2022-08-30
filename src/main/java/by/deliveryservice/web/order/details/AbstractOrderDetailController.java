package by.deliveryservice.web.order.details;

import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.repository.OrderDetailRepository;
import by.deliveryservice.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.deliveryservice.util.validation.ValidationUtil.assureIdConsistent;
import static by.deliveryservice.util.validation.ValidationUtil.checkNew;

@Slf4j
public class AbstractOrderDetailController {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    List<OrderDetail> getAllByOrderIdWithProduct(int clientId, int orderId) {
        log.info("get all order details by order {} by client id {}", orderId, clientId);
        return orderDetailRepository.getAllByOrderIdWithProduct(orderId);
    }

    public OrderDetail get(int clientId, int orderId, int id) {
        log.info("get order details {} by order {} by client id {}", id, orderId, clientId);
        return orderDetailRepository.get(id).orElse(null);
    }

    public void update(OrderDetail orderDetail, int id, int clientId, int orderId, int productId) {
        assureIdConsistent(orderDetail, id);
        log.info("update order details {} by product {} by order {} by client id {}", orderDetail, productId, orderId, clientId);
        orderDetailRepository.save(orderDetail, orderId, productId);
    }

    public OrderDetail create(OrderDetail orderDetail, int clientId, int orderId, int productId) {
        checkNew(orderDetail);
        log.info("create order details by product {} by order {} by client id {}", productId, orderId, clientId);
        return orderDetailService.create(orderDetail, orderId, productId);
    }

    public void delete(int clientId, int orderId, int id) {
        log.info("delete order details {} by order {} by client id {}", id, orderId, clientId);
        orderDetailRepository.delete(id);
    }
}