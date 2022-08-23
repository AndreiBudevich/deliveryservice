package by.deliveryservice.web.shop;

import by.deliveryservice.model.Shop;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/shops", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShopUIController extends AbstractShopController{
    @GetMapping()
    @Override
    public List<Shop> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Shop get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(Shop shop) {
        if (shop.isNew()) {
            super.create(shop);
        } else {
            super.update(shop, shop.getId());
        }
    }
}
