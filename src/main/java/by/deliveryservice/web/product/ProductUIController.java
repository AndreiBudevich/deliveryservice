package by.deliveryservice.web.product;

import by.deliveryservice.dto.ProductDto;
import by.deliveryservice.model.Product;
import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/shops", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductUIController extends AbstractProductController {

    @Override
    @GetMapping("/products")
    public List<ProductDto> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{shopId}/products/{id}")
    public ProductDto get(@PathVariable int shopId, @PathVariable int id) {
        return super.get(shopId, id);
    }

    @Override
    @PostMapping("/products/{id}/add-category/{categoryId}")
    void addCategory(@PathVariable int id, @PathVariable int categoryId) {
        super.addCategory(id, categoryId);
    }

    @Override
    @PostMapping("/products/{id}/delete-category/{categoryId}")
    void deleteCategory(@PathVariable int id, @PathVariable int categoryId) {
        super.deleteCategory(id, categoryId);
    }

    @GetMapping("/products/filter")
    public List<ProductDto> getAllWithFilter(@RequestParam @Nullable String nameContains,
                                             @RequestParam @Nullable String descriptionContains,
                                             @RequestParam @Nullable String shopNameContains,
                                             @RequestParam @NotNull Long priceFrom,
                                             @RequestParam @NotNull Long priceUpTo,
                                             @RequestParam @NotNull Integer discountFrom,
                                             @RequestParam @NotNull Integer discountUpTo,
                                             @RequestParam @NotNull String categories) {
        return super.getAllWithFilter(nameContains, descriptionContains, shopNameContains, priceFrom, priceUpTo,
                discountFrom, discountUpTo, categories.split("_"));
    }

    @PostMapping("/{shopId}/products")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(Product product, @PathVariable int shopId) {
        if (product.isNew()) {
            super.create(product, shopId);
        } else {
            super.update(product, shopId, product.getId());
        }
    }
}
