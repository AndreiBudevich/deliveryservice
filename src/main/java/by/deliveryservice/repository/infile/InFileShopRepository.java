package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Shop;

public class InFileShopRepository extends InFileRepository<Shop> {
    public InFileShopRepository() {
        super("json/shops.json", Shop.class);
    }
}
