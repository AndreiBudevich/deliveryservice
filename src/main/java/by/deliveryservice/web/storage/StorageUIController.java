package by.deliveryservice.web.storage;

import by.deliveryservice.model.Storage;
import by.deliveryservice.web.View;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/shops/{shopId}/storage", produces = MediaType.APPLICATION_JSON_VALUE)
public class StorageUIController extends AbstractStorageController {

    @GetMapping()
    @Override
    public List<Storage> getAllWithProduct(@PathVariable int shopId) {
        return super.getAllWithProduct(shopId);
    }

    @Override
    @GetMapping("/{id}")
    public Storage get(@PathVariable int shopId, @PathVariable int id) {
        return super.get(shopId, id);
    }

    @PostMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) Storage storage, @PathVariable int shopId, @PathVariable int productId) {
        super.update(storage, storage.getId(), shopId, productId);
    }
}
