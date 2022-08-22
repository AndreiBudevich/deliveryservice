package by.deliveryservice.web.product;

import by.deliveryservice.model.Product;
import com.sun.istack.NotNull;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductUIController extends AbstractProductController {

    @Override
    @GetMapping
    public List<Product> getAll() {
        return super.getAll();
    }


    @GetMapping("/filter")
    public List<Product> getAllWithFilter(@RequestParam @Nullable String nameContains,
                                          @RequestParam @Nullable String descriptionContains,
                                          @RequestParam @NotNull Long priceFrom,
                                          @RequestParam @NotNull Long priceUpTo,
                                          @RequestParam @NotNull Integer discountFrom,
                                          @RequestParam @NotNull Integer discountUpTo,
                                          @RequestParam @NotNull String categories) {
        return super.getAllWithFilter(nameContains, descriptionContains, priceFrom, priceUpTo,
                discountFrom, discountUpTo, categories.split("_"));
    }
}