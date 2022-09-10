package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Category;
import by.deliveryservice.repository.CategoryRepository;

public class InFileCategoryRepository extends InFileRepository<Category> implements CategoryRepository {
    public InFileCategoryRepository() {
        super("json/categories.json", Category.class);
    }
}
