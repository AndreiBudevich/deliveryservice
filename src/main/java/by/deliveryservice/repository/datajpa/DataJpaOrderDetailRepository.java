package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Order;
import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.OrderDetailRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaOrderDetailRepository implements OrderDetailRepository {

    private final OrderDetailCrudRepository orderDetailCrudRepository;
    private final OrderCrudRepository orderCrudRepository;
    private final ProductCrudRepository productCrudRepository;

    public DataJpaOrderDetailRepository(OrderDetailCrudRepository orderDetailCrudRepository,
                                        OrderCrudRepository orderCrudRepository, ProductCrudRepository productCrudRepository) {
        this.orderDetailCrudRepository = orderDetailCrudRepository;
        this.orderCrudRepository = orderCrudRepository;
        this.productCrudRepository = productCrudRepository;
    }

    @Override
    public List<OrderDetail> getAllByOrderId(int id) {
        return orderDetailCrudRepository.getAllByOrderId(id);
    }

    @Override
    public Optional<OrderDetail> get(int id) {
        return orderDetailCrudRepository.get(id);
    }

    @Override
    public void delete(int id) {
        orderDetailCrudRepository.deleteById(id);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail, int orderId, int productId) {
        Order order = orderCrudRepository.getReferenceById(orderId);
        Product product = productCrudRepository.getReferenceById(productId);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        return orderDetailCrudRepository.save(orderDetail);
    }
}
