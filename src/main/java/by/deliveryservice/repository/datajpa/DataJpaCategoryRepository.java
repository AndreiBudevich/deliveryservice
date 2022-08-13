package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Category;
import by.deliveryservice.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public class DataJpaCategoryRepository implements BaseRepository<Category> {

    private final CommonCrudRepository<Category> categoryCrudRepository;

    public DataJpaCategoryRepository(CommonCrudRepository<Category> categoryCrudRepository) {
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
