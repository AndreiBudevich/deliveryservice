package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.repository.OrderDetailRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaOrderDetailRepository implements OrderDetailRepository {

    OrderDetailCrudRepository orderDetailCrudRepository;

    public DataJpaOrderDetailRepository(OrderDetailCrudRepository orderDetailCrudRepository) {
        this.orderDetailCrudRepository = orderDetailCrudRepository;
    }

    @Override
    public List<OrderDetail> getByOrderId(int id) {
        return orderDetailCrudRepository.getByOrderId(id);
    }
}
