package by.deliveryservice.web.stogare;

import by.deliveryservice.model.Storage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public void createOrUpdate(Storage storage, @PathVariable int shopId, @PathVariable int productId) {
        if (storage.isNew()) {
            super.create(storage, shopId, productId);
        } else {
            super.update(storage, storage.getId(), shopId, productId);
        }
    }
}
