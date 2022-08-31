package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Product;
import by.deliveryservice.model.Shop;
import by.deliveryservice.repository.ShopRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static by.deliveryservice.view.EntityPrint.print;

public class InFileShopRepository extends InFileRepository<Shop> implements ShopRepository {
    public InFileShopRepository() {
        super("json/shops.json", Shop.class);
    }

    public void addProducts(Integer id, Product... products) {
        addOrDeleteProducts(id, "add", products);
    }

    public void deleteProducts(Integer id, Product... products) {
        addOrDeleteProducts(id, "delete", products);
    }

    private void addOrDeleteProducts(Integer id, String operation, Product... products) {
        Shop shop = get(id);
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
        print(getShopProducts(id));
    }

    public Map<Product, Long> getShopProducts(Integer id) {
        readInFile();
        List<Product> products = repositoryInMemory.get(id).getProducts();
        return products.stream()
                .collect(Collectors.groupingBy(Product::get, Collectors.counting()));
    }
}
