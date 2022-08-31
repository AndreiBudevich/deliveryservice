package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Category;
import by.deliveryservice.repository.CategoryRepository;
import by.deliveryservice.repository.datajpa.crud.CategoryCrudRepository;
import by.deliveryservice.repository.datajpa.crud.CommonCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaCategoryRepository implements CategoryRepository {

    private final CommonCrudRepository<Category> categoryCrudRepository;

    public DataJpaCategoryRepository(CategoryCrudRepository categoryCrudRepository) {
        this.categoryCrudRepository = categoryCrudRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryCrudRepository.findAll();
    }

    @Override
    public Optional<Category> get(int id) {
        return categoryCrudRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        categoryCrudRepository.deleteById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryCrudRepository.save(category);
    }
}
