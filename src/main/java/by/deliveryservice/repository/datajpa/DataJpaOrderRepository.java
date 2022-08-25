package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Client;
import by.deliveryservice.model.Order;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.OrderRepository;
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
        return orderCrudRepository.getWithClient();
    }

    @Override
    public Optional<Order> get(int id) {
        return orderCrudRepository.findByIdWithClient(id);
    }

    public List<Order> getAllByClientId(int clientId) {
        return orderCrudRepository.getAllByClientId(clientId);
    }

    @Override
    public void delete(int id) {
    }

    @Override
    @Transactional
    public Order save(Order order, int clientId) {
        if (!order.isNew() && get(order.getId()).isEmpty()) {
            return null;
        }
        order.setClient(clientCrudRepository.getReferenceById(clientId));
        return orderCrudRepository.save(order);
    }

    @Override
    public void addProducts(Integer id, Product... products) {
    }

    @Override
    public void deleteProducts(Integer id, Product... products) {
    }

    @Override
    public void setAddress(Integer id, String deliveryAddress) {
    }
}
