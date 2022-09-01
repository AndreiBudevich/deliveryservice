package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Client;
import by.deliveryservice.model.Order;
import by.deliveryservice.repository.OrderRepository;
import by.deliveryservice.repository.datajpa.crud.CommonCrudRepository;
import by.deliveryservice.repository.datajpa.crud.OrderCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaOrderRepository implements OrderRepository {

    OrderCrudRepository orderCrudRepository;
    CommonCrudRepository<Client> clientCrudRepository;

    public DataJpaOrderRepository(OrderCrudRepository orderCrudRepository, CommonCrudRepository<Client> clientCrudRepository) {
        this.orderCrudRepository = orderCrudRepository;
        this.clientCrudRepository = clientCrudRepository;
    }

    @Override
    public List<Order> getAll() {
        return orderCrudRepository.getAllWithClient();
    }

    @Override
    public List<Order> getAllByClientId(int clientId) {
        return orderCrudRepository.getAllByClientId(clientId);
    }

    @Override
    public Optional<Order> get(int id) {
        return orderCrudRepository.get(id);
    }

    @Override
    public void delete(int id) {
        orderCrudRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Order save(Order order, int clientId) {
        order.setClient(clientCrudRepository.getReferenceById(clientId));
        return orderCrudRepository.save(order);
    }
}
