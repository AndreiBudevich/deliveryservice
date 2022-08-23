package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Order;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderCrudRepository extends CommonCrudRepository<Order> {

    @Query("SELECT o FROM Order o JOIN FETCH o.client ORDER BY o.registered ASC")
    List<Order> getWithClient();

    @Query("SELECT o FROM Order o JOIN FETCH o.client WHERE o.client.id = ?1 ORDER BY o.registered ASC")
    List<Order> getAllByClientId(int id);
}
