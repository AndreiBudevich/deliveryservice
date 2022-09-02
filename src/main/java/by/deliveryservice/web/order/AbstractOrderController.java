package by.deliveryservice.web.order;

import by.deliveryservice.dto.OrderDto;
import by.deliveryservice.model.Order;
import by.deliveryservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

import static by.deliveryservice.util.OrderUtil.createDto;
import static by.deliveryservice.util.OrderUtil.getDtos;
import static by.deliveryservice.util.validation.ValidationUtil.assureIdConsistent;
import static by.deliveryservice.util.validation.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractOrderController {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        log.info("getAll for order");
        return orderRepository.getAll();
    }

    public List<OrderDto> getAllByClientId(int clientId) {
        log.info("getAll for order by client id {}", clientId);
        return getDtos(orderRepository.getAllByClientId(clientId));
    }

    public OrderDto get(int id, int clientId) {
        log.info("get order {} by client id {}", id, clientId);
        return createDto(Objects.requireNonNull(orderRepository.get(id).orElse(null)));
    }

    public void delete(int id, int clientId) {
        log.info("delete order {} by client id {}", id, clientId);
        orderRepository.delete(id);
    }

    public Order create(Order order, int clientId) {
        checkNew(order);
        log.info("create {}  for client {}", order, clientId);
        return orderRepository.save(order, clientId);
    }

    public void update(Order order, int id, int clientId) {
        assureIdConsistent(order, id);
        log.info("update {}", order);
        orderRepository.save(order, clientId);
    }
}