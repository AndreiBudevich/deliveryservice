package by.deliveryservice.repository.datajpa.crud;

import by.deliveryservice.model.Storage;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StorageCrudRepository extends CommonCrudRepository<Storage> {

    @Query("SELECT s FROM Storage s JOIN FETCH s.product WHERE s.shop.id = ?1 ORDER BY s.product.name ASC")
    List<Storage> getAllWithProduct(int shopId);

    @Query("SELECT s FROM Storage s JOIN FETCH s.product JOIN FETCH s.product WHERE s.id = ?1")
    Optional<Storage> get(int id);
}
