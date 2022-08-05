package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Order;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.OrderRepository;

import java.util.Arrays;

import static by.deliveryservice.util.EntityUtil.calculationTotalCost;

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
        Order order = get(id);
        Arrays.asList(products).forEach(product -> {
            if (operation.equals("add")) {
                order.getProducts().add(product);
            } else {
                order.getProducts().remove(product);
            }
        });
        order.setTotalCost(calculationTotalCost(order.getProducts()));
        saveAndPrint(order);
    }

    @Override
    public void setAddress(Integer id, String deliveryAddress) {
        Order order = get(id);
        order.setDeliveryAddress(deliveryAddress);
        saveAndPrint(order);
    }

    private Order get(Integer id) {
        readInFile();
        return repositoryInMemory.get(id);
    }

    private void saveAndPrint(Order order) {
        saveInFile();
        System.out.println(order);
    }
}
