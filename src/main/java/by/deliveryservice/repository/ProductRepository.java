package by.deliveryservice.repository;

import by.deliveryservice.model.Product;

public interface ProductRepository extends BaseRepository<Product> {

    Product save(Product product, int shopId);
}
