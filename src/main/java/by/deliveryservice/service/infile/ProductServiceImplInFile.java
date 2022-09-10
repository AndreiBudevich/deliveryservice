package by.deliveryservice.service.infile;

import by.deliveryservice.model.Category;
import by.deliveryservice.model.Product;
import by.deliveryservice.model.Storage;
import by.deliveryservice.repository.infile.InFileCategoryRepository;
import by.deliveryservice.repository.infile.InFileProductRepository;
import by.deliveryservice.repository.infile.InFileStorageRepository;
import by.deliveryservice.service.AbstractProductService;

import java.util.List;

public class ProductServiceImplInFile extends AbstractProductService {

    public ProductServiceImplInFile() {
        super(new InFileProductRepository(), new InFileStorageRepository(), new InFileCategoryRepository());
    }

    public void addCategory(int id, int categoryId) {
        addOrDelete(id, categoryId, "add");
    }

    public void deleteCategory(int id, int categoryId) {
        addOrDelete(id, categoryId, "delete");
    }

    private void addOrDelete(int id, int categoryId, String nameMethod) {
        Product product = productRepository.get(id).orElse(null);
        if (product != null) {
            Category category = categoryRepository.get(categoryId).orElse(null);
            if (category != null) {
                if ("add".equals(nameMethod)) {
                    product.getCategories().add(category);
                }
                product.getCategories().add(category);
                if ("delete".equals(nameMethod)) {
                    product.getCategories().remove(category);
                }
                productRepository.save(product, product.getShop().getId());
            }
        }
    }

    public void setQuantity(int productId, int quantity) {
        Storage storage = storageRepository.getByProductId(productId).orElse(null);
        if (storage != null) {
            storage.setQuantity(quantity);
            storageRepository.save(storage, storage.getShop().getId(), productId);
        }
    }

    public List<Storage> getShopProducts(int shopId) {
        return storageRepository.getAllWithProduct(shopId);
    }
}

