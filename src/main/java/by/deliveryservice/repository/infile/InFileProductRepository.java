package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Category;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.ProductRepository;
import by.deliveryservice.repository.ShopRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


import static by.deliveryservice.util.CategoryUtil.getCategories;
import static by.deliveryservice.util.StringUtil.contains;
import static by.deliveryservice.util.StringUtil.getSplit;
import static by.deliveryservice.util.validation.ValidationUtil.checkNotFoundWithId;

@Slf4j
public class InFileProductRepository extends InFileRepository<Product> implements ProductRepository {

    private final ShopRepository shopRepository;

    public InFileProductRepository() {
        super("json/products.json", Product.class);
        this.shopRepository = new InFileShopRepository();
    }

    public List<Product> getSortPrice() {
        readInFile();
        return repositoryInMemory.values().stream()
                .sorted((p1, p2) -> (int) (p1.getPrice() - p2.getPrice()))
                .toList();
    }

    //search by fields
    public List<Product> findByAttributes(String... attributes) {
        if (attributes.length < 5) {
            log.info("Не верно заданы атрибуты");
        }
        readInFile();
        return repositoryInMemory.values().stream()
                .filter(product -> containsName(product.getName(), attributes[0]))
                .filter(product -> containsName(product.getDescription(), attributes[1]))
                .filter(product -> containsName(product.getShop().getId().toString(), attributes[2]))
                .filter(product -> containsName(product.getPrice().toString(), attributes[3]))
                .filter(product -> containsName(product.getDiscount().toString(), attributes[4]))
                .filter(product -> containsCategories(product.getCategories(), getSplit(attributes[5], ", ")))
                .toList();
    }

    private boolean containsCategories(Set<Category> expectedCategories, String[] actualStringIdsCategories) {
        return actualStringIdsCategories[0].equals("*") || !Collections.disjoint(expectedCategories,
                Arrays.asList(Objects.requireNonNull(getCategories(actualStringIdsCategories))));
    }

    private boolean containsName(String actualString, String expectedString) {
        return expectedString.equals("*") || contains(actualString, expectedString);
    }

    @Override
    public List<Product> getAllWithFilter(String nameContains, String descriptionContains, String shopNameContains, Long priceFrom, Long priceUpTo, Integer discountFrom, Integer discountUpTo, String[] actualStringIdsCategories) {
        throw new UnsupportedOperationException("getAllWithFilter");
    }

    @Override
    public Optional<Product> getWithCategories(int id) {
        throw new UnsupportedOperationException("getWithCategories");
    }

    @Override
    public Product save(Product product, int shopId) {
        product.setShop(checkNotFoundWithId(shopRepository.get(shopId).orElse(null), shopId));
        return super.save(product);
    }
}