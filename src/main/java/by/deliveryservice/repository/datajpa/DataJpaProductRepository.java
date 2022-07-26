package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Product;
import by.deliveryservice.repository.ProductRepository;
import by.deliveryservice.repository.datajpa.crud.ProductCrudRepository;
import by.deliveryservice.repository.datajpa.crud.ShopCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static by.deliveryservice.util.ProductUtil.filteringByCategory;

@Repository
public class DataJpaProductRepository implements ProductRepository {

    private final ProductCrudRepository productCrudRepository;
    private final ShopCrudRepository shopCrudRepository;

    public DataJpaProductRepository(ProductCrudRepository productCrudRepository, ShopCrudRepository shopCrudRepository) {
        this.productCrudRepository = productCrudRepository;
        this.shopCrudRepository = shopCrudRepository;
    }

    @Override
    public List<Product> getAll() {
        return productCrudRepository.getAll();
    }

    @Override
    public List<Product> getAllWithFilter(String nameContains, String descriptionContains, String shopNameContains, Long priceFrom, Long priceUpTo,
                                          Integer discountFrom, Integer discountUpTo, String[] actualStringIdsCategories) {
        return filteringByCategory(productCrudRepository.getAllWithFilter("%" + nameContains + "%", "%" +
                        descriptionContains + "%", "%" + shopNameContains + "%", priceFrom, priceUpTo,
                discountFrom, discountUpTo), actualStringIdsCategories);
    }

    @Override
    public Optional<Product> get(int id) {
        return productCrudRepository.get(id);
    }

    @Override
    public Optional<Product> getWithCategories(int id) {
        return productCrudRepository.getWithCategories(id);
    }

    @Override
    public void delete(int id) {
        productCrudRepository.deleteExisted(id);
    }

    @Override
    public Product save(Product product, int shopId) {
        product.setShop(shopCrudRepository.getReferenceById(shopId));
        return productCrudRepository.save(product);
    }
}
