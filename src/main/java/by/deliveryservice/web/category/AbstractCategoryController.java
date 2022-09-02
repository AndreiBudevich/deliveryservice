package by.deliveryservice.web.category;

import by.deliveryservice.model.Category;
import by.deliveryservice.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.deliveryservice.util.validation.ValidationUtil.assureIdConsistent;
import static by.deliveryservice.util.validation.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category get(int id) {
        log.info("get category {}", id);
        return categoryRepository.get(id).orElse(null);
    }

    public void delete(int id) {
        log.info("delete category {}", id);
        categoryRepository.delete(id);
    }

    public List<Category> getAll() {
        log.info("getAll for category");
        return categoryRepository.getAll();
    }

    public Category create(Category category) {
        checkNew(category);
        log.info("create {}", category);
        return categoryRepository.save(category);
    }

    public void update(Category category, int id) {
        assureIdConsistent(category, id);
        log.info("update {}", category);
        categoryRepository.save(category);
    }
}