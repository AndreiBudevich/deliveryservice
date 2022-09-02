package by.deliveryservice.service;

import by.deliveryservice.model.Category;
import by.deliveryservice.model.Product;
import by.deliveryservice.model.Storage;
import by.deliveryservice.repository.CategoryRepository;
import by.deliveryservice.repository.ProductRepository;
import by.deliveryservice.repository.StorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final StorageRepository storageRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, StorageRepository storageRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.storageRepository = storageRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product get(int id) {
        return Objects.requireNonNull(productRepository.get(id).orElse(null));
    }

    public void delete(int id) {
        productRepository.delete(id);
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Transactional
    public void addCategory(int id, int categoryId) {
        getCategoriesByProduct(id).add(getCategory(categoryId));
    }

    @Transactional
    public void deleteCategory(int id, int categoryId) {
        getCategoriesByProduct(id).remove(getCategory(categoryId));
    }

    private Set<Category> getCategoriesByProduct(int id) {
        Product productWithCategories = productRepository.getWithCategories(id).orElse(null);
        assert productWithCategories != null;
        return productWithCategories.getCategories();
    }

    private Category getCategory(int categoryId) {
        return categoryRepository.get(categoryId).orElse(null);
    }

    @Transactional
    public Product save(Product product, int shopId) {
        Product newProduct = productRepository.save(product, shopId);
        storageRepository.save(new Storage(null, null, 0), shopId, newProduct.getId());
        return productRepository.save(product, shopId);
    }

    public List<Product> getAllWithFilter(String nameContains, String descriptionContains, String shopNameContains, Long priceFrom, Long priceUpTo, Integer discountFrom,
                                          Integer discountUpTo, String[] idsCategories) {
        return productRepository.getAllWithFilter(nameContains, descriptionContains, shopNameContains, priceFrom, priceUpTo,
                discountFrom, discountUpTo, idsCategories);
    }
}
