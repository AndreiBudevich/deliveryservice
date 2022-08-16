package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Category;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaProductRepository implements ProductRepository {

    ProductCrudRepository productCrudRepository;

    public DataJpaProductRepository(ProductCrudRepository productCrudRepository) {
        this.productCrudRepository = productCrudRepository;
    }

    @Override
    public List<Product> getAll() {
        return productCrudRepository.findAll();
    }

    @Override
    public Optional<Product> get(int id) {
        return productCrudRepository.findById(id);
    }

    public Product getReferenceById(int id) {
        return productCrudRepository.getReferenceById(id);
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
