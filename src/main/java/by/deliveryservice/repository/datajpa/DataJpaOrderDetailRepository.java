package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Order;
import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.OrderDetailRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public List<OrderDetail> getAllByOrderIdWithProduct(int orderId) {
        return orderDetailCrudRepository.getAllByOrderIdWithProduct(orderId);
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
    @Transactional
    public OrderDetail save(OrderDetail orderDetail, int orderId, int productId) {
        Order order = orderCrudRepository.getReferenceById(orderId);
        Product product = productCrudRepository.findById(productId).orElse(null);
        if (orderDetail.isNew() && product != null) {
            orderDetail.setProduct(product);
            orderDetail.setPrice(product.getPrice());
            orderDetail.setQuantity(1);
        }
        orderDetail.setOrder(order);
        return orderDetailCrudRepository.save(getWithAmountRecalculation(orderDetail));
    }

    private OrderDetail getWithAmountRecalculation(OrderDetail orderDetail) {
        orderDetail.setAmount(orderDetail.getPrice() * orderDetail.getQuantity());
        return orderDetail;
    }
}



