package by.deliveryservice.web.product;

import by.deliveryservice.dto.ProductDto;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.datajpa.DataJpaProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

import static by.deliveryservice.util.ProductUtil.createDto;
import static by.deliveryservice.util.ProductUtil.getDtos;
import static by.deliveryservice.util.validation.ValidationUtil.assureIdConsistent;
import static by.deliveryservice.util.validation.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractProductController {

    @Autowired
    private DataJpaProductRepository productRepository;

    public ProductDto get(int shopId, int id) {
        log.info("get product {} by shop {}", id, shopId);
        return createDto(Objects.requireNonNull(productRepository.get(id).orElse(null)));
    }

    public void delete(int shopId, int id) {
        log.info("delete product {} by shop {}", id, shopId);
        productRepository.delete(id);
    }

    public List<ProductDto> getAll() {
        log.info("getAll for product");
        return getDtos(productRepository.getAll());
    }

    public Product create(Product product, int shopId) {
        checkNew(product);
        log.info("create {}", product);
        return productRepository.save(product, shopId);
    }

    public void update(Product product, int shopId, int id) {
        assureIdConsistent(product, id);
        log.info("update {} by shop {}", product, shopId);
        productRepository.save(product, shopId);
    }

    List<ProductDto> getAllWithFilter(String nameContains, String descriptionContains, String shopNameContains, Long priceFrom, Long priceUpTo, Integer discountFrom,
                                      Integer discountUpTo, String[] idsCategories) {
        log.info("get all with filter");
        return getDtos(productRepository.getAllWithFilter(nameContains, descriptionContains, shopNameContains, priceFrom, priceUpTo,
                discountFrom, discountUpTo, idsCategories));
    }
}
