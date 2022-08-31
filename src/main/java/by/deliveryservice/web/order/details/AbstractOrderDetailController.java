package by.deliveryservice.web.order.details;

import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.deliveryservice.util.validation.ValidationUtil.assureIdConsistent;

@Slf4j
public class AbstractOrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    public List<OrderDetail> getAllByOrderIdWithProduct(int clientId, int orderId) {
        log.info("get all order details by order {} by client id {}", orderId, clientId);
        return orderDetailService.getAllByOrderIdWithProduct(orderId);
    }

    public OrderDetail get(int clientId, int orderId, int id) {
        log.info("get order details {} by order {} by client id {}", id, orderId, clientId);
        return orderDetailService.get(id);
    }

    public void delete(int clientId, int orderId, int id) {
        log.info("delete order details {} by order {} by client id {}", id, orderId, clientId);
        orderDetailService.delete(id);
    }

    public void update(OrderDetail orderDetail, int id, int clientId, int orderId, int productId) {
        assureIdConsistent(orderDetail, id);
        log.info("update order details {} by product {} by order {} by client id {}", orderDetail, productId, orderId, clientId);
        orderDetailService.update(orderDetail, orderId, productId, clientId);
    }

    public void addProduct(int clientId, int orderId, int productId) {
        log.info("add product {} by order {} by client id {}", productId, orderId, clientId);
        orderDetailService.addProduct(orderId, productId, clientId);
    }
}