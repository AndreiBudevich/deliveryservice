package by.deliveryservice.web.order;

import by.deliveryservice.model.Order;
import by.deliveryservice.repository.datajpa.DataJpaOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.deliveryservice.util.validation.ValidationUtil.assureIdConsistent;
import static by.deliveryservice.util.validation.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractOrderController {

    @Autowired
    private DataJpaOrderRepository orderRepository;

    public Order get(int id) {
        log.info("get order {}", id);
        return orderRepository.get(id).orElse(null);
    }

    public void delete(int id) {
        log.info("delete order {}", id);
        orderRepository.delete(id);
    }

    public List<Order> getAll() {
        log.info("getAll for order");
        return orderRepository.getAll();
    }

    public List<Order> getAllByClientId(int clientId) {
        log.info("getAll for order by client id {}", clientId);
        return orderRepository.getAllByClientId(clientId);
    }

    public Order create(Order order) {
        checkNew(order);
        log.info("create {}", order);
        return orderRepository.save(order);
    }

    public void update(Order order, int id) {
        assureIdConsistent(order, id);
        log.info("update {}", order);
        orderRepository.save(order);
    }
}