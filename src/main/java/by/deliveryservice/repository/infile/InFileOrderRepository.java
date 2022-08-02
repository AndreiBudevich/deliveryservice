package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Order;
import by.deliveryservice.repository.OrderRepository;

import static by.deliveryservice.util.FileUtil.isEmpty;

public class InFileOrderRepository extends InFileRepository<Order> implements OrderRepository {

    @Override
    public Order save(Order order) {
        int maxId = 0;
        if (!isEmpty(nameFile)) {
            readInFile();
            maxId = getMaxId();
        }
        if (order.isNew()) {
            maxId++;
            order.setId(maxId);
            repositoryInMemory.put(order.getId(), order);
            System.out.println("create " + order + "by id :" + maxId);
        } else {
            Order orderOld = repositoryInMemory.get(order.getId());
            order.setDateTime(orderOld.getDateTime());
            repositoryInMemory.computeIfPresent(order.getId(), (id, oldClient) -> order);
            System.out.println("update " + order + "by id :" + maxId);
        }
        saveInFile();
        return order;
    }

    public InFileOrderRepository() {
        super("json/orders.json", Order.class);

    }
}
