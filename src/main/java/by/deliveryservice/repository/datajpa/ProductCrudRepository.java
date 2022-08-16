package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCrudRepository extends CommonCrudRepository<Product> {

    @Query("SELECT p FROM Product p WHERE p.shop.id=?1 ORDER BY p.name ASC")
    List<Product> getAllProductsByShopId(int shopId);
}
