package by.deliveryservice.web.category;

import by.deliveryservice.model.Category;
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
@RequestMapping(value = CategoryRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryRestController extends AbstractCategoryController {

    static final String REST_URL = "/rest/categories";

    @GetMapping()
    @Override
    public List<Category> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Category get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }


    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Category category, @PathVariable int id) {
        super.update(category, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> createWithLocation(@Validated(View.Web.class) @RequestBody Category category) {
        Category created = super.create(category);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}