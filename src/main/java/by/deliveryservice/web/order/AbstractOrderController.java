package by.deliveryservice.web.order;

import by.deliveryservice.dto.OrderDto;
import by.deliveryservice.dto.OrderWithClientDto;
import by.deliveryservice.model.Order;
import by.deliveryservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.deliveryservice.util.OrderUtil.*;
import static by.deliveryservice.util.validation.ValidationUtil.assureIdConsistent;
import static by.deliveryservice.util.validation.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractOrderController {

    @Autowired
    private OrderService orderService;

    public List<OrderWithClientDto> getAll() {
        log.info("getAll for order");
        return getWithClientDtos(orderService.getAll());
    }

    public List<OrderDto> getAllByClientId(int clientId) {
        log.info("getAll for order by client id {}", clientId);
        return getDtos(orderService.getAllByClientId(clientId));
    }

    public OrderDto get(int id, int clientId) {
        log.info("get order {} by client id {}", id, clientId);
        return createDto(orderService.get(id));
    }

    public void delete(int id, int clientId) {
        log.info("delete order {} by client id {}", id, clientId);
        orderService.delete(id);
    }

    public Order create(Order order, int clientId) {
        checkNew(order);
        log.info("create {}  for client {}", order, clientId);
        return orderService.save(order, clientId);
    }

    public void update(Order order, int id, int clientId) {
        assureIdConsistent(order, id);
        log.info("update {}", order);
        orderService.save(order, clientId);
    }

    public void ship(int id, int clientId) {
        log.info("shipped order {} by client {}", id, clientId);
        orderService.ship(id);
    }
}