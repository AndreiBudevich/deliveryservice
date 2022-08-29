package by.deliveryservice.web.product;

import by.deliveryservice.dto.ProductDto;
import by.deliveryservice.model.Product;
import com.sun.istack.NotNull;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductUIController extends AbstractProductController {

    @Override
    @GetMapping
    public List<ProductDto> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Product get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping("/filter")
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
}
