package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Client;
import by.deliveryservice.model.Order;
import by.deliveryservice.repository.OrderRepository;

import java.util.List;

public class InFileOrderRepository extends InFileRepository<Order> implements OrderRepository {

    private final InFileClientRepository inFileClientRepository;

    public InFileOrderRepository() {
        super("json/orders.json", Order.class);
        this.inFileClientRepository = new InFileClientRepository();
    }

    @Override
    public List<Order> getAllByClientId(int clientId) {
        throw new UnsupportedOperationException("getAllByClientId");
    }

    @Override
    public Order save(Order order, int clientId) {
        Client client = inFileClientRepository.get(clientId).orElse(null);
        if (client != null) {
            order.setClient(client);
            if (order.isNew()) {
                order.setDeliveryAddress(client.getResidentialAddress());
            }
        }
        return super.save(order);
    }
}
