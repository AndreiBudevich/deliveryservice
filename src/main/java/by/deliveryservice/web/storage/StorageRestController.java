package by.deliveryservice.web.storage;

import by.deliveryservice.model.Storage;
import by.deliveryservice.web.View;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = StorageRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class StorageRestController extends AbstractStorageController {

    static final String REST_URL = "/rest/shops/{shopId}/storage";

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

    @PutMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Storage storage, @PathVariable int shopId,
                       @PathVariable int productId) {
        super.update(storage, storage.getId(), shopId, productId);
    }
}