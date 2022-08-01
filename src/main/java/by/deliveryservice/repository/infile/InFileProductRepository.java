package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Product;
import by.deliveryservice.repository.ProductRepository;

public class InFileProductRepository extends InFileRepository<Product> implements ProductRepository {
    public InFileProductRepository() {
        super("json/products.json", Product.class);
    }
}
