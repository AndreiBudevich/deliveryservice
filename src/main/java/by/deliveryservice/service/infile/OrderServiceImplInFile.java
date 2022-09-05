package by.deliveryservice.service.infile;

import by.deliveryservice.model.Product;
import by.deliveryservice.repository.infile.InFileOrderRepository;
import by.deliveryservice.repository.infile.InFileShopRepository;

public class OrderServiceImplInFile {

    InFileShopRepository shopRepository;
    InFileOrderRepository orderRepository;

    public OrderServiceImplInFile() {
        this.shopRepository = new InFileShopRepository();
        this.orderRepository = new InFileOrderRepository();
    }

    public void addProducts(Integer id, Product... products) {
        shopRepository.deleteProducts(id, products);
        orderRepository.addProducts(id, products);
    }

    public void deleteProducts(Integer id, Product... products) {
        shopRepository.addProducts(id, products);
        orderRepository.deleteProducts(id, products);
    }
}
