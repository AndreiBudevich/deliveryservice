package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Order;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaOrderRepository implements OrderRepository {

    OrderCrudRepository orderCrudRepository;

    public DataJpaOrderRepository(OrderCrudRepository orderCrudRepository) {
        this.orderCrudRepository = orderCrudRepository;
    }

    @Override
    public List<Order> getAll() {
        return orderCrudRepository.getWithClient();
    }

    public List<Order> getAllByClientId(int clientId) {
        return orderCrudRepository.getAllByClientId(clientId);
    }

    @Override
    public Optional<Order> get(int id) {
        return orderCrudRepository.findById(id);
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public Order save(Order order) {
        return null;
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
