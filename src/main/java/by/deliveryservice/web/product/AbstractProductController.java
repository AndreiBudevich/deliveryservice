package by.deliveryservice.web.product;

import by.deliveryservice.dto.ProductDto;
import by.deliveryservice.model.Product;
import by.deliveryservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static by.deliveryservice.util.ProductUtil.createDto;
import static by.deliveryservice.util.ProductUtil.getDtos;
import static by.deliveryservice.util.validation.ValidationUtil.assureIdConsistent;
import static by.deliveryservice.util.validation.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractProductController {

    @Autowired
    private ProductService productService;

    public ProductDto get(int shopId, int id) {
        log.info("get product {} by shop {}", id, shopId);
        return createDto(productService.get(id));
    }

    public List<ProductDto> getAll() {
        log.info("getAll for product");
        return getDtos(productService.getAll());
    }

    public Product create(Product product, int shopId) {
        checkNew(product);
        log.info("create {}", product);
        return productService.save(product, shopId);
    }

    public void update(Product product, int shopId, int id) {
        assureIdConsistent(product, id);
        log.info("update {} by shop {}", product, shopId);
        productService.save(product, shopId);
    }

    void addCategory(int id, int categoryId) {
        productService.addCategory(id, categoryId);
        log.info("add category {} in product {}", categoryId, id);
    }

    void deleteCategory(@PathVariable int id, @PathVariable int categoryId) {
        productService.deleteCategory(id, categoryId);
        log.info("delete category {} in product {}", categoryId, id);
    }

    List<ProductDto> getAllWithFilter(String nameContains, String descriptionContains, String shopNameContains, Long priceFrom, Long priceUpTo, Integer discountFrom,
                                      Integer discountUpTo, String[] idsCategories) {
        log.info("get all with filter");
        return getDtos(productService.getAllWithFilter(nameContains, descriptionContains, shopNameContains, priceFrom, priceUpTo,
                discountFrom, discountUpTo, idsCategories));
    }
}
