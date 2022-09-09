package by.deliveryservice.web.product;

import by.deliveryservice.dto.ProductDto;
import by.deliveryservice.model.Product;
import by.deliveryservice.web.View;
import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ProductRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductRestController extends AbstractProductController {

    static final String REST_URL = "/rest/shops";

    @GetMapping("/products")
    @Override
    public List<ProductDto> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{shopId}/products/{id}")
    public ProductDto get(@PathVariable int shopId, @PathVariable int id) {
        return super.get(shopId, id);
    }

    @Override
    @PutMapping(value = "/{shopId}/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Product product, @PathVariable int shopId, @PathVariable int id) {
        super.update(product, shopId, id);
    }

    @Override
    @PostMapping("/products/{id}/add-category/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addCategory(@PathVariable int id, @PathVariable int categoryId) {
        super.addCategory(id, categoryId);
    }

    @Override
    @PostMapping("/products/{id}/delete-category/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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

    @PostMapping(value = "/{shopId}/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createWithLocation(@Validated(View.Web.class) @RequestBody Product product, @PathVariable int shopId) {
        Product created = super.create(product, shopId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}