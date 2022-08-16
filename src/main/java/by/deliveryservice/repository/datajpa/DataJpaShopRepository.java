package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Product;
import by.deliveryservice.model.Shop;
import by.deliveryservice.repository.ShopRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class DataJpaShopRepository implements ShopRepository {

    private final CommonCrudRepository<Shop> shopCrudRepository;

    public DataJpaShopRepository(CommonCrudRepository<Shop> shopCrudRepository) {
        this.shopCrudRepository = shopCrudRepository;
    }

    @Override
    public List<Shop> getAll() {
        return shopCrudRepository.findAll();
    }

    @Override
    public Optional<Shop> get(int id) {
        return shopCrudRepository.findById(id);
    }

    public Shop getReferenceById(int id) {
        return shopCrudRepository.getReferenceById(id);
    }

    @Override
    public void delete(int id) {
        shopCrudRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Shop save(Shop shop) {
        return shopCrudRepository.save(shop);
    }

    @Override
    public void addProducts(Integer id, Product... products) {

    }

    @Override
    public void deleteProducts(Integer id, Product... products) {

    }

    @Override
    public Map<Product, Long> getShopProducts(Integer id) {
        return null;
    }
}
