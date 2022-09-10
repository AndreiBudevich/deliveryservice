package by.deliveryservice.service;

import by.deliveryservice.model.Product;
import by.deliveryservice.model.Storage;
import by.deliveryservice.repository.CategoryRepository;
import by.deliveryservice.repository.ProductRepository;
import by.deliveryservice.repository.StorageRepository;

public class AbstractProductService {

    protected final ProductRepository productRepository;
    protected final StorageRepository storageRepository;
    protected final CategoryRepository categoryRepository;

    public AbstractProductService(ProductRepository productRepository, StorageRepository storageRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.storageRepository = storageRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product save(Product product, int shopId) {
        boolean createStorage = product.getId() == null;
        Product newProduct = productRepository.save(product, shopId);
        if (createStorage) {
            storageRepository.save(new Storage(null, null, 0), shopId, newProduct.getId());
        }
        return newProduct;
    }
}
