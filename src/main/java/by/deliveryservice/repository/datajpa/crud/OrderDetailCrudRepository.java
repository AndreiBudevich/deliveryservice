package by.deliveryservice.repository.datajpa.crud;

import by.deliveryservice.model.OrderDetail;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderDetailCrudRepository extends CommonCrudRepository<OrderDetail> {

    @Query("SELECT od FROM OrderDetail od JOIN FETCH od.product WHERE od.order.id =?1 ORDER BY od.product.name ASC")
    List<OrderDetail> getAllByOrderIdWithProduct(int orderId);

    @Query("SELECT od FROM OrderDetail od WHERE od.order.id =?1")
    List<OrderDetail> getAllByOrderId(int orderId);

    @Query("SELECT od FROM OrderDetail od WHERE od.id =?1")
    Optional<OrderDetail> get(int id);

    @Query("SELECT od FROM OrderDetail od WHERE od.order.id =?1 AND od.product.id =?2")
    Optional<OrderDetail> getByOrderIdByProductId(int orderId, int productId);
}
