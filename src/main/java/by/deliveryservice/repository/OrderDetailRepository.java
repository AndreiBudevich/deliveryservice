package by.deliveryservice.repository;

import by.deliveryservice.model.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository {

    List<OrderDetail> getAllByOrderIdWithProduct(int orderId);

    List<OrderDetail> getAllByOrderId(int orderId);

    Optional<OrderDetail> get(int id);

    Optional<OrderDetail> getByOrderIdByProductId (int orderId, int productId);

    void delete(int id);

    OrderDetail save(OrderDetail orderDetail, int orderId, int productId);
}
