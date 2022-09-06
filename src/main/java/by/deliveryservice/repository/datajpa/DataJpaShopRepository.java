package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Shop;
import by.deliveryservice.repository.ShopRepository;
import by.deliveryservice.repository.datajpa.crud.ShopCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaShopRepository implements ShopRepository {

    private final ShopCrudRepository shopCrudRepository;

    public DataJpaShopRepository(ShopCrudRepository shopCrudRepository) {
        this.shopCrudRepository = shopCrudRepository;
    }

    @Override
    public List<Shop> getAll() {
        return shopCrudRepository.findAll();
    }

    @Override
    public Optional<Shop> get(int id) {
        return shopCrudRepository.get (id);
    }

    @Override
    public void delete(int id) {
        shopCrudRepository.deleteExisted(id);
    }

    @Override
    @Transactional
    public Shop save(Shop shop) {
        return shopCrudRepository.save(shop);
    }
}
