package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Shop;
import by.deliveryservice.repository.ShopRepository;

public class InFileShopRepository extends InFileRepository<Shop> implements ShopRepository {
    public InFileShopRepository() {
        super("json/shops.json", Shop.class);
    }
}

