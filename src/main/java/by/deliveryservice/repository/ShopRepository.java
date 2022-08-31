package by.deliveryservice.repository;

import by.deliveryservice.model.Shop;

public interface ShopRepository extends BaseRepository<Shop> {
    Shop save(Shop shop);
}
