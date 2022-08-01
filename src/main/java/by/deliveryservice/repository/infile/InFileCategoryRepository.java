package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Category;

public class InFileCategoryRepository extends InFileRepository<Category> {
    public InFileCategoryRepository() {
        super("json/categories.json", Category.class);
    }
}
