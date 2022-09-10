package by.deliveryservice.service;

import by.deliveryservice.model.Category;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.CategoryRepository;
import by.deliveryservice.repository.ProductRepository;
import by.deliveryservice.repository.StorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static by.deliveryservice.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductServiceImpl extends AbstractProductService implements ProductService {

    public ProductServiceImpl(ProductRepository productRepository, StorageRepository storageRepository, CategoryRepository categoryRepository) {
        super(productRepository, storageRepository, categoryRepository);
    }

    @Override
    public Product get(int id) {
        return checkNotFoundWithId(productRepository.get(id).orElse(null), id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    @Transactional
    public void addCategory(int id, int categoryId) {
        getCategoriesByProduct(id).add(getCategory(categoryId));
    }

    @Override
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

    @Override
    @Transactional
    public Product save(Product product, int shopId) {
        return super.save(product, shopId);
    }

    @Override
    public List<Product> getAllWithFilter(String nameContains, String descriptionContains, String shopNameContains, Long priceFrom, Long priceUpTo, Integer discountFrom,
                                          Integer discountUpTo, String[] idsCategories) {
        return productRepository.getAllWithFilter(nameContains, descriptionContains, shopNameContains, priceFrom, priceUpTo,
                discountFrom, discountUpTo, idsCategories);
    }
}
