package by.deliveryservice.repository;

import by.deliveryservice.model.Product;
import by.deliveryservice.model.Shop;

public interface ShopRepository extends Repository<Shop> {
    void addProducts(Integer id, Product... products);
    void deleteProducts(Integer id, Product... products);
}
