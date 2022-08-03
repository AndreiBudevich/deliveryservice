package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Category;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

import static by.deliveryservice.util.EntityUtil.*;

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
        if (attributes.length < 5) {
            System.out.println("Не верно заданы атрибуты");
        }
        readInFile();
        return repositoryInMemory.values().stream()
                .filter(product -> containsName(product.getName(), attributes[0]))
                .filter(product -> containsName(product.getDescription(), attributes[1]))
                .filter(product -> containsName(product.getShop().getId().toString(), attributes[2]))
                .filter(product -> containsName(product.getPrice().toString(), attributes[3]))
                .filter(product -> containsName(product.getDiscount().toString(), attributes[4]))
                .filter(product -> containsCategories(product.getCategories(), getSplit(attributes[5], ", ")))
                .collect(Collectors.toList());
    }

    private boolean containsCategories(Set<Category> expectedCategories, String[] actualStringIdsCategories) {
        return actualStringIdsCategories[0].equals("*") || !Collections.disjoint(expectedCategories,
                Arrays.asList(Objects.requireNonNull(getEntitiesByIdsArray(Category.class, actualStringIdsCategories))));
    }

    private boolean containsName(String actualString, String expectedString) {
        return expectedString.equals("*") || contains(actualString, expectedString);
    }
}
