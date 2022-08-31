package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.repository.OrderDetailRepository;
import by.deliveryservice.repository.datajpa.crud.OrderCrudRepository;
import by.deliveryservice.repository.datajpa.crud.OrderDetailCrudRepository;
import by.deliveryservice.repository.datajpa.crud.ProductCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaOrderDetailRepository implements OrderDetailRepository {

    private final OrderDetailCrudRepository orderDetailCrudRepository;
    private final OrderCrudRepository orderCrudRepository;
    private final ProductCrudRepository productCrudRepository;

    public DataJpaOrderDetailRepository(OrderDetailCrudRepository orderDetailCrudRepository, OrderCrudRepository orderCrudRepository,
                                        ProductCrudRepository productCrudRepository) {
        this.orderDetailCrudRepository = orderDetailCrudRepository;
        this.orderCrudRepository = orderCrudRepository;
        this.productCrudRepository = productCrudRepository;
    }

    @Override
    public List<OrderDetail> getAllByOrderIdWithProduct(int orderId) {
        return orderDetailCrudRepository.getAllByOrderIdWithProduct(orderId);
    }

    @Override
    public List<OrderDetail> getAllByOrderId(int orderId) {
        return orderDetailCrudRepository.getAllByOrderId(orderId);
    }

    @Override
    public Optional<OrderDetail> get(int id) {
        return orderDetailCrudRepository.get(id);
    }

    public Optional<OrderDetail> getByOrderIdByProductId(int orderId, int productId) {
        return orderDetailCrudRepository.getByOrderIdByProductId(orderId, productId);
    }

    @Override
    public void delete(int id) {
        orderDetailCrudRepository.deleteById(id);
    }

    @Override
    @Transactional
    public OrderDetail save(OrderDetail orderDetail, int orderId, int productId) {
        orderDetail.setOrder(orderCrudRepository.getReferenceById(orderId));
        orderDetail.setProduct(productCrudRepository.getReferenceById(productId));
        orderDetail.setAmount(orderDetail.getPrice() * orderDetail.getQuantity());
        return orderDetailCrudRepository.save(orderDetail);
    }
}



