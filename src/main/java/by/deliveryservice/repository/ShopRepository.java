package by.deliveryservice.repository;

import by.deliveryservice.model.Product;
import by.deliveryservice.model.Shop;

import java.util.Map;

public interface ShopRepository extends Repository<Shop> {
    void addProducts(Integer id, Product... products);

    void deleteProducts(Integer id, Product... products);

    Map<Product, Long> getShopProducts(Integer id);
}
