package by.deliveryservice.service;

import by.deliveryservice.model.Product;
import by.deliveryservice.repository.infile.InFileOrderRepository;
import by.deliveryservice.repository.infile.InFileShopRepository;

public class OrderServiceImpl implements OrderService {

    InFileShopRepository shopRepository;
    InFileOrderRepository orderRepository;

    public OrderServiceImpl() {
        this.shopRepository = new InFileShopRepository();
        this.orderRepository = new InFileOrderRepository();
    }

    @Override
    public void addProducts(Integer id, Product... products) {
        shopRepository.deleteProducts(id, products);
        orderRepository.addProducts(id, products);
    }

    @Override
    public void deleteProducts(Integer id, Product... products) {
        shopRepository.addProducts(id, products);
        orderRepository.deleteProducts(id, products);
    }
}
