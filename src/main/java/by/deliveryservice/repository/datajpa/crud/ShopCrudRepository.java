package by.deliveryservice.repository.datajpa.crud;

import by.deliveryservice.model.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ShopCrudRepository extends CommonCrudRepository<Shop> {

    @Query("SELECT s FROM Shop s WHERE s.id = ?1")
    Optional<Shop> get(int id);
}
