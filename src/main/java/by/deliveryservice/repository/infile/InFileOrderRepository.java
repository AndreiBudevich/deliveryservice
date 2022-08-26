package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Client;
import by.deliveryservice.model.Order;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.OrderRepository;

import java.util.Arrays;
import java.util.Optional;

import static by.deliveryservice.util.EntityUtil.calculationTotalCost;

public class InFileOrderRepository extends InFileRepository<Order> implements OrderRepository {

    InFileClientRepository inFileClientRepository = new InFileClientRepository();

    public InFileOrderRepository() {
        super("json/orders.json", Order.class);
    }

    @Override
    protected void updateEntity(Order order, Order orderOld) {
        order.setRegistered(orderOld.getRegistered());
        repositoryInMemory.computeIfPresent(order.getId(), (id, oldClient) -> order);
    }

    @Override
    public Order save(Order order, int clientId) {
        Optional<Client> client = inFileClientRepository.get(clientId);
        if (client.isPresent()) {
            order.setDeliveryAddress(client.orElse(null).getResidentialAddress());
        }
        return super.save(order);
    }

    @Override
    public void addProducts(Integer id, Product... products) {
       /* addOrDeleteProducts(id, "add", products);*/
    }

    @Override
    public void deleteProducts(Integer id, Product... products) {
     /*   addOrDeleteProducts(id, "delete", products);*/
    }

   /* private void addOrDeleteProducts(Integer id, String operation, Product... products) {
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
    }*/

    @Override
    public void setAddress(Integer id, String deliveryAddress) {
        Order order = get(id);
        order.setDeliveryAddress(deliveryAddress);
        saveAndPrint(order);
    }
}
