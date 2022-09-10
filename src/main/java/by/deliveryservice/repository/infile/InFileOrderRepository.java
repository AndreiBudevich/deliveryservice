package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Client;
import by.deliveryservice.model.Order;
import by.deliveryservice.repository.ClientRepository;
import by.deliveryservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static by.deliveryservice.error.ExceptionMessage.ORDER_SHIPPED_NO_CHANGES;
import static by.deliveryservice.util.validation.ValidationUtil.isShipped;

@Slf4j
public class InFileOrderRepository extends InFileRepository<Order> implements OrderRepository {

    private final ClientRepository clientRepository;

    public InFileOrderRepository() {
        super("json/orders.json", Order.class);
        this.clientRepository = new InFileClientRepository();
    }

    @Override
    public List<Order> getAllByClientId(int clientId) {
        throw new UnsupportedOperationException("getAllByClientId");
    }

    @Override
    public void delete(int id) {
        try {
            Order order = get(id).orElse(null);
            isShipped(order);
            super.delete(id);
        } catch (DataIntegrityViolationException e) {
            log.info(ORDER_SHIPPED_NO_CHANGES);
        }
    }

    @Override
    public Order save(Order order, int clientId) {
        Client client = clientRepository.get(clientId).orElse(null);
        if (client != null) {
            order.setClient(client);
            if (order.isNew()) {
                order.setDeliveryAddress(client.getResidentialAddress());
            }
        }
        return super.save(order);
    }
}
