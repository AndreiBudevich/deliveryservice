package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Order;
import by.deliveryservice.repository.OrderRepository;

public class InFileOrderRepository extends InFileRepository<Order> implements OrderRepository {

    public InFileOrderRepository() {
        super("json/orders.json", Order.class);
    }

    @Override
    protected void updateEntity(Order order, Order orderOld) {
        order.setDateTime(orderOld.getDateTime());
        repositoryInMemory.computeIfPresent(order.getId(), (id, oldClient) -> order);
    }
}
