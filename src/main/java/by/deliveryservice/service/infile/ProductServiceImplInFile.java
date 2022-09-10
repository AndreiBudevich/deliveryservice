package by.deliveryservice.service.infile;

import by.deliveryservice.model.Category;
import by.deliveryservice.model.Product;
import by.deliveryservice.model.Storage;
import by.deliveryservice.repository.infile.InFileCategoryRepository;
import by.deliveryservice.repository.infile.InFileProductRepository;
import by.deliveryservice.repository.infile.InFileStorageRepository;

public class ProductServiceImplInFile {

    private final InFileProductRepository inFileProductRepository;
    private final InFileStorageRepository inFileStorageRepository;
    private final InFileCategoryRepository inFileCategoryRepository;

    public ProductServiceImplInFile() {
        this.inFileProductRepository = new InFileProductRepository();
        this.inFileStorageRepository = new InFileStorageRepository();
        this.inFileCategoryRepository = new InFileCategoryRepository();
    }

    public Product save(Product product, int shopId) {
        boolean createStorage = product.getId() == null;
        Product newProduct = inFileProductRepository.save(product, shopId);
        if (createStorage) {
            inFileStorageRepository.save(new Storage(product.getShop(), product, 0));
        }
        return newProduct;
    }

    public void addCategory(int id, int categoryId) {
        addOrDelete(id, categoryId, "add");
    }

    public void deleteCategory(int id, int categoryId) {
        addOrDelete(id, categoryId, "delete");
    }

    private void addOrDelete(int id, int categoryId, String nameMethod) {
        Product product = inFileProductRepository.get(id).orElse(null);
        if (product != null) {
            Category category = inFileCategoryRepository.get(categoryId).orElse(null);
            if (category != null) {
                if ("add".equals(nameMethod)) {
                    product.getCategories().add(category);
                }
                product.getCategories().add(category);
                if ("delete".equals(nameMethod)) {
                    product.getCategories().remove(category);
                }
                inFileProductRepository.save(product);
            }
        }
    }
}

