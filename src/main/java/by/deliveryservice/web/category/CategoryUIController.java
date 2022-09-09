package by.deliveryservice.web.category;

import by.deliveryservice.model.Category;
import by.deliveryservice.web.View;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryUIController extends AbstractCategoryController {

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

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(@Validated(View.Web.class) Category category) {
        if (category.isNew()) {
            super.create(category);
        } else {
            super.update(category, category.getId());
        }
    }
}
