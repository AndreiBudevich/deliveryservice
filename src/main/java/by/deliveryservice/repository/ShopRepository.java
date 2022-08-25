package by.deliveryservice.repository;

import by.deliveryservice.model.Product;
import by.deliveryservice.model.Shop;

import java.util.Map;

public interface ShopRepository extends BaseRepository<Shop> {

    Shop save(Shop shop);

    void addProducts(Integer id, Product... products);

    void deleteProducts(Integer id, Product... products);

    Map<Product, Long> getShopProducts(Integer id);
}
