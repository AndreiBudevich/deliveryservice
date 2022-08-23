package by.deliveryservice.web.product;

import by.deliveryservice.model.Product;
import by.deliveryservice.repository.datajpa.DataJpaProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.deliveryservice.util.validation.ValidationUtil.assureIdConsistent;
import static by.deliveryservice.util.validation.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractProductController {

    @Autowired
    private DataJpaProductRepository productRepository;

    public Product get(int id) {
        log.info("get product {}", id);
        return productRepository.get(id).orElse(null);
    }

    public void delete(int id) {
        log.info("delete product {}", id);
        productRepository.delete(id);
    }

    public List<Product> getAll() {
        log.info("getAll for product");
        return productRepository.getAll();
    }

    public List<Product> getAllProductsByShopId(int shopId) {
        log.info("getAll for product by shopId {}", shopId);
        return productRepository.getAllProductsByShopId(shopId);
    }

    public Product create(Product product) {
        checkNew(product);
        log.info("create {}", product);
        return productRepository.save(product);
    }

    public void update(Product product, int id) {
        assureIdConsistent(product, id);
        log.info("update {}", product);
        productRepository.save(product);
    }

    List<Product> getAllWithFilter(String nameContains, String descriptionContains, String shopNameContains, Long priceFrom, Long priceUpTo, Integer discountFrom,
                                   Integer discountUpTo, String[] idsCategories) {
        log.info("get all with filter");
        return productRepository.getAllWithFilter(nameContains, descriptionContains, shopNameContains, priceFrom, priceUpTo,
                discountFrom, discountUpTo, idsCategories);
    }
}
