package by.deliveryservice.service;

import by.deliveryservice.model.Product;
import by.deliveryservice.model.Storage;
import by.deliveryservice.repository.datajpa.DataJpaProductRepository;
import by.deliveryservice.repository.datajpa.DataJpaStorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private DataJpaProductRepository productRepository;
    private DataJpaStorageRepository storageRepository;

    public ProductService(DataJpaProductRepository productRepository, DataJpaStorageRepository storageRepository) {
        this.productRepository = productRepository;
        this.storageRepository = storageRepository;
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
