package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Category;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.ProductRepository;
import by.deliveryservice.repository.datajpa.crud.ProductCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static by.deliveryservice.util.ProductUtil.filteringByCategory;

@Repository
public class DataJpaProductRepository implements ProductRepository {

    ProductCrudRepository productCrudRepository;

    public DataJpaProductRepository(ProductCrudRepository productCrudRepository) {
        this.productCrudRepository = productCrudRepository;
    }

    @Override
    public List<Product> getAll() {
        return productCrudRepository.getAll();
    }

    public List<Product> getAllWithFilter(String nameContains, String descriptionContains, String shopNameContains, Long priceFrom, Long priceUpTo,
                                          Integer discountFrom, Integer discountUpTo, String[] actualStringIdsCategories) {
        return filteringByCategory(productCrudRepository.getAllWithFilter("%" + nameContains + "%", "%" +
                        descriptionContains + "%", "%" + shopNameContains + "%", priceFrom, priceUpTo,
                discountFrom, discountUpTo), actualStringIdsCategories);
    }

    @Override
    public Optional<Product> get(int id) {
        return productCrudRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        productCrudRepository.deleteById(id);
    }

    @Override
    public Product save(Product product) {
        return productCrudRepository.save(product);
    }

    public List<Product> getAllProductsByShopId(int shopId) {
        return productCrudRepository.getAllProductsByShopId(shopId);
    }

    @Override
    public void addCategories(Integer id, Category... categories) {

    }

    @Override
    public void deleteCategories(Integer id, Category... categories) {

    }

    @Override
    public List<Product> findByAttributes(String... attributes) {
        return null;
    }

    @Override
    public List<Product> getSortPrice() {
        return null;
    }
}
