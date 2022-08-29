package by.deliveryservice.repository;

import by.deliveryservice.model.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository {

    List<OrderDetail> getAllByOrderId(int id);

    Optional<OrderDetail> get(int id);

    void delete(int id);

    OrderDetail save(OrderDetail orderDetail, int orderId, int productId);
}
