package by.deliveryservice.repository.datajpa.crud;

import by.deliveryservice.model.Category;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CategoryCrudRepository extends CommonCrudRepository<Category> {
}
