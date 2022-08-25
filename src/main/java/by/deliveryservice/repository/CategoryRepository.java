package by.deliveryservice.repository;

import by.deliveryservice.model.Category;

public interface CategoryRepository extends BaseRepository<Category> {
    Category save(Category category);
}
