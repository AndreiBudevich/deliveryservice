package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Product;
import by.deliveryservice.model.Shop;
import by.deliveryservice.repository.ShopRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InFileShopRepository extends InFileRepository<Shop> implements ShopRepository {
    public InFileShopRepository() {
        super("json/shops.json", Shop.class);
    }

    @Override
    public void addProducts(Integer id, Product... products) {
        operationsProducts(id, "add", products);
    }

    @Override
    public void deleteProducts(Integer id, Product... products) {
        operationsProducts(id, "delete", products);
    }

    private void operationsProducts(Integer id, String operation, Product... products) {
        readInFile();
        Shop shop = repositoryInMemory.get(id);
        Arrays.asList(products).forEach(product -> {
            if (product.getShop().getId().equals(shop.getId())) {
                if (operation.equals("add")) {
                    shop.getProducts().add(product);
                } else {
                    shop.getProducts().remove(product);
                }
            } else {
                System.out.println("товар id=" + product.getId() + " didn't " + "operation");
            }
        });
        saveInFile();
        System.out.println(shop);
    }

    @Override
    public Map<Product, Long> getShopProducts(Integer id) {
        readInFile();
        List<Product> products = repositoryInMemory.get(id).getProducts();
        return products.stream()
                .collect(Collectors.groupingBy(Product::get, Collectors.counting()));
    }
}
