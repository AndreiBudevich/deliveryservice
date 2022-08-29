package by.deliveryservice.web.order.details;

import by.deliveryservice.model.OrderDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/clients/{clientId}/orders/{orderId}/details", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderDetailUIController extends AbstractOrderDetailController {

    @Override
    @GetMapping
    List<OrderDetail> getAllByOrderId(@PathVariable int clientId, @PathVariable int orderId) {
        return super.getAllByOrderId(clientId, orderId);
    }

    @Override
    @GetMapping("/{id}")
    public OrderDetail get(@PathVariable int clientId, @PathVariable int orderId, @PathVariable int id) {
        return super.get(clientId, orderId, id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int clientId, @PathVariable int orderId, @PathVariable int id) {
        super.delete(clientId, orderId, id);
    }

    @PostMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(OrderDetail orderDetail, @PathVariable int clientId, @PathVariable int orderId, @PathVariable int productId) {
        super.update(orderDetail, orderDetail.getId(), clientId, orderId, productId);
    }
}
