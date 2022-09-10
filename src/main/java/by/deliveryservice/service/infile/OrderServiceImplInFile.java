package by.deliveryservice.service.infile;

import by.deliveryservice.model.Order;
import by.deliveryservice.repository.OrderRepository;
import by.deliveryservice.repository.infile.InFileOrderRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceImplInFile {

    private final OrderRepository orderRepository;

    public OrderServiceImplInFile() {
        this.orderRepository = new InFileOrderRepository();
    }

    public void setAddress(Integer id, String deliveryAddress) {
        Order order = orderRepository.get(id).orElse(null);
        assert order != null;
        order.setDeliveryAddress(deliveryAddress);
        orderRepository.save(order, order.getClient().getId());
        log.info("set new address {} by order {}", deliveryAddress, id);
    }
}
