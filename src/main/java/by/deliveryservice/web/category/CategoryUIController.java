package by.deliveryservice.web.category;

import by.deliveryservice.model.Category;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryUIController extends AbstractCategoryController {

    @GetMapping()
    public List<Category> getAllCategory() {
        return super.getAll();
    }
}
