package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Category;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class InFileProductRepository extends InFileRepository<Product> implements ProductRepository {
    public InFileProductRepository() {
        super("json/products.json", Product.class);
    }

    @Override
    public void addCategories(Integer id, Category... categories) {
        readInFile();
        Product product = repositoryInMemory.get(id);
        product.addCategories(categories);
        saveInFile();
        System.out.println(product);
    }

    @Override
    public List<Product> getSortPrice() {
        readInFile();
        return repositoryInMemory.values().stream()
                .sorted((p1, p2) -> (int) (p1.getPrice() - p2.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByAttributes(String... attributes) {
        return null;
    }
}
