package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Client;
import by.deliveryservice.model.Order;
import by.deliveryservice.repository.BaseRepository;

import java.util.Optional;

public class InFileOrderRepository extends InFileRepository<Order> implements BaseRepository<Order> {

    InFileClientRepository inFileClientRepository = new InFileClientRepository();

    public InFileOrderRepository() {
        super("json/orders.json", Order.class);
    }

    @Override
    protected void update(Order order, Order orderOld) {
        order.setRegistered(orderOld.getRegistered());
        repositoryInMemory.computeIfPresent(order.getId(), (id, oldClient) -> order);
    }

    public Order save(Order order, int clientId) {
        Optional<Client> client = inFileClientRepository.get(clientId);
        if (client.isPresent()) {
            order.setDeliveryAddress(client.orElse(null).getResidentialAddress());
        }
        return super.save(order);
    }

    public void setAddress(Integer id, String deliveryAddress) {
        Order order = get(id);
        order.setDeliveryAddress(deliveryAddress);
        saveAndPrint(order);
    }
}
