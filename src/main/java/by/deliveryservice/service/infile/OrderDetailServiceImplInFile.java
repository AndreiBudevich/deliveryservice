package by.deliveryservice.service.infile;

import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.repository.infile.InFileClientRepository;
import by.deliveryservice.repository.infile.InFileOrderDetailRepository;
import by.deliveryservice.repository.infile.InFileOrderRepository;
import by.deliveryservice.repository.infile.InFileProductRepository;
import by.deliveryservice.service.AbstractOrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Objects;

import static by.deliveryservice.error.ExceptionMessage.ORDER_SHIPPED_NO_CHANGES;
import static by.deliveryservice.util.validation.ValidationUtil.checkNotFoundWithId;

@Slf4j
public class OrderDetailServiceImplInFile extends AbstractOrderDetailService {

    private final InFileClientRepository clientRepository;

    public OrderDetailServiceImplInFile() {
        super(new InFileOrderDetailRepository(), new InFileProductRepository(), new InFileOrderRepository());
        this.clientRepository = new InFileClientRepository();
    }

    public List<OrderDetail> get(int orderId) {
        log.info("get order details by order {}", orderId);
        return orderDetailRepository.getAllByOrderId(orderId);
    }

    public void addProduct(int orderId, int productId) {
        try {
            int clientId = getIdClient(orderId);
            super.addProduct(clientId, orderId, productId);
            log.info("add product {} by order {} by client id {}", productId, orderId, clientId);
        } catch (DataIntegrityViolationException e) {
            log.info(ORDER_SHIPPED_NO_CHANGES);
        }
    }

    public void deleteProduct(int orderId, int productId) {
        try {
            int clientId = getIdClient(orderId);
            super.deleteProduct(clientId, orderId, productId);
            log.info("delete product {} by order {} by client id {}", productId, orderId, clientId);
        } catch (DataIntegrityViolationException e) {
            log.info(ORDER_SHIPPED_NO_CHANGES);
        }
    }

    private int getIdClient(int orderId) {
        return Objects.requireNonNull(checkNotFoundWithId(clientRepository.get(orderId).orElse(null), orderId)).getId();
    }
}