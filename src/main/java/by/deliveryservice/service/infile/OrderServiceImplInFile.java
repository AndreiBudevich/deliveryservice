package by.deliveryservice.service.infile;

import by.deliveryservice.model.Order;
import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.model.Storage;
import by.deliveryservice.repository.infile.InFileOrderDetailRepository;
import by.deliveryservice.repository.infile.InFileOrderRepository;
import by.deliveryservice.repository.infile.InFileStorageRepository;
import by.deliveryservice.service.AbstractOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static by.deliveryservice.error.ExceptionMessage.ORDER_SHIPPED_NO_CHANGES;
import static by.deliveryservice.util.validation.ValidationUtil.isShipped;

@Slf4j
public class OrderServiceImplInFile extends AbstractOrderService {

    public OrderServiceImplInFile() {
        super(new InFileOrderRepository(), new InFileOrderDetailRepository(), new InFileStorageRepository());
    }

    public void setAddress(Integer id, String deliveryAddress) {
        Order order = getById(id);
        assert order != null;
        order.setDeliveryAddress(deliveryAddress);
        orderRepository.save(order, order.getClient().getId());
        log.info("set new address {} by order {}", deliveryAddress, id);
    }

    @Override
    @Transactional
    public void ship(int id) {
        try {
            Order order = getById(id);
            isShipped(order);
            List<OrderDetail> all = orderDetailRepository.getAllByOrderIdWithProduct(id);
            all.forEach(oD -> recalculationStorageWithSave(oD.getQuantity(), oD.getProduct().getId()));
            Objects.requireNonNull(order).setShipped(true);
            orderRepository.save(order, order.getClient().getId());
            log.info("Заказ отгружен");
        } catch (DataIntegrityViolationException e) {
            log.info(ORDER_SHIPPED_NO_CHANGES);
        }
    }

    private void recalculationStorageWithSave(int shippedQuantity, int productId) {
        Storage storage = storageRepository.getByProductId(productId).orElse(null);
        assert storage != null;
        setQuantity(storage, shippedQuantity);
        storageRepository.save(storage, storage.getShop().getId(), productId);
    }
}
