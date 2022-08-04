package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Order;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.OrderRepository;

import java.util.Arrays;

public class InFileOrderRepository extends InFileRepository<Order> implements OrderRepository {

    public InFileOrderRepository() {
        super("json/orders.json", Order.class);
    }

    @Override
    protected void updateEntity(Order order, Order orderOld) {
        order.setDateTime(orderOld.getDateTime());
        repositoryInMemory.computeIfPresent(order.getId(), (id, oldClient) -> order);
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
        Order order = repositoryInMemory.get(id);
        Arrays.asList(products).forEach(product -> {
            if (operation.equals("add")) {
                order.getProducts().add(product);
            } else {
                order.getProducts().remove(product);
            }
        });
        saveInFile();
        System.out.println(order);
    }
}
