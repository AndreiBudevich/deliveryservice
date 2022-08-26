package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Category;
import by.deliveryservice.model.OrderDetail;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailCrudRepository extends CommonCrudRepository<Category> {

    @Query("SELECT od FROM OrderDetail od JOIN FETCH od.product WHERE od.order.id =?1 ORDER BY od.product.name ASC")
    List<OrderDetail> getByOrderId(int id);
}
