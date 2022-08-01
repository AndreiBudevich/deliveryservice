package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Order;
import by.deliveryservice.repository.OrderRepository;

public class InFileOrderRepository extends InFileRepository<Order> implements OrderRepository {
    public InFileOrderRepository() {
        super("json/orders.json", Order.class);
    }
}
