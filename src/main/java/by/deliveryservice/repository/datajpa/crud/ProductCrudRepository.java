package by.deliveryservice.repository.datajpa.crud;

import by.deliveryservice.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCrudRepository extends CommonCrudRepository<Product> {

    @Query("SELECT p FROM Product p WHERE p.shop.id=?1 ORDER BY p.name ASC")
    List<Product> getAllProductsByShopId(int shopId);

    @Query("SELECT p FROM Product p JOIN FETCH p.shop ORDER BY p.name ASC")
    List<Product> getAll();

    @EntityGraph(attributePaths = {"categories"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT p FROM Product p JOIN FETCH p.shop WHERE LOWER (p.shop.name) LIKE LOWER (:shopNameContains) " +
            "AND LOWER (p.name) LIKE LOWER (:nameContains) AND LOWER (p.description) LIKE LOWER (:descriptionContains)" +
            "AND p.price>=:priceFrom AND p.price<=:priceUpTo AND p.discount>=:discountFrom AND p.discount<=:discountUpTo ORDER BY p.name ASC")
    List<Product> getAllWithFilter(@Param("nameContains") String nameContains,
                                   @Param("descriptionContains") String descriptionContains,
                                   @Param("shopNameContains") String shopNameContains,
                                   @Param("priceFrom") Long priceFrom,
                                   @Param("priceUpTo") Long priceUpTo,
                                   @Param("discountFrom") Integer discountFrom,
                                   @Param("discountUpTo") Integer discountUpTo);
}
