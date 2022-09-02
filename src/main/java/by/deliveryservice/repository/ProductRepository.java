package by.deliveryservice.repository;

import by.deliveryservice.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends BaseRepository<Product> {

    List<Product> getAllWithFilter(String nameContains, String descriptionContains, String shopNameContains, Long priceFrom, Long priceUpTo,
                                   Integer discountFrom, Integer discountUpTo, String[] actualStringIdsCategories);

    Optional<Product> getWithCategories(int id);

    Product save(Product product, int shopId);
}
