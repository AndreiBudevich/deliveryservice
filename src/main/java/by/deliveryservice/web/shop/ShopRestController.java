package by.deliveryservice.web.shop;

import by.deliveryservice.model.Shop;
import by.deliveryservice.web.View;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ShopRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ShopRestController extends AbstractShopController {

    static final String REST_URL = "/rest/shops";

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
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Shop shop, @PathVariable int id) {
        super.update(shop, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shop> createWithLocation(@Validated(View.Web.class) @RequestBody Shop shop) {
        Shop created = super.create(shop);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}