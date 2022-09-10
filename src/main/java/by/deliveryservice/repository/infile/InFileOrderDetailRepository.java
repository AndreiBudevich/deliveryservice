package by.deliveryservice.repository.infile;

import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.repository.OrderDetailRepository;
import by.deliveryservice.repository.OrderRepository;
import by.deliveryservice.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import static by.deliveryservice.util.validation.ValidationUtil.checkNotFoundWithId;

public class InFileOrderDetailRepository extends InFileRepository<OrderDetail> implements OrderDetailRepository {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public InFileOrderDetailRepository() {
        super("json/orderDetails.json", OrderDetail.class);
        this.orderRepository = new InFileOrderRepository();
        this.productRepository = new InFileProductRepository();
    }

    @Override
    public Optional<OrderDetail> getByOrderIdByProductId(int orderId, int productId) {
        readInFile();
        return repositoryInMemory.values().stream()
                .filter(orderDetail -> orderDetail.getOrder().getId() == orderId)
                .filter(orderDetail -> orderDetail.getProduct().getId() == productId)
                .findFirst();
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail, int orderId, int productId) {
        orderDetail.setOrder(checkNotFoundWithId(orderRepository.get(orderId).orElse(null), orderId));
        orderDetail.setProduct(checkNotFoundWithId(productRepository.get(productId).orElse(null), productId));
        orderDetail.setAmount(orderDetail.getPrice() * orderDetail.getQuantity());
        return super.save(orderDetail);
    }

    @Override
    public List<OrderDetail> getAllByOrderIdWithProduct(int orderId) {
        readInFile();
        return repositoryInMemory.values().stream()
                .filter(orderDetail -> orderDetail.getOrder().getId() == orderId)
                .toList();
    }

    @Override
    public List<OrderDetail> getAllByOrderId(int orderId) {
        readInFile();
        return repositoryInMemory.values().stream()
                .filter(orderDetail -> orderDetail.getOrder().getId() == orderId)
                .toList();
    }
}
