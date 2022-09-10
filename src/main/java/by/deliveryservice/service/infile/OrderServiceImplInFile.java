package by.deliveryservice.service.infile;

import by.deliveryservice.repository.infile.InFileOrderRepository;
import by.deliveryservice.repository.infile.InFileShopRepository;

public class OrderServiceImplInFile {

    InFileShopRepository shopRepository;
    InFileOrderRepository orderRepository;

    public OrderServiceImplInFile() {
        this.shopRepository = new InFileShopRepository();
        this.orderRepository = new InFileOrderRepository();
    }
}
