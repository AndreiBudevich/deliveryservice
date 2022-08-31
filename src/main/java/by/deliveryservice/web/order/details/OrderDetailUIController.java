package by.deliveryservice.web.order.details;

import by.deliveryservice.model.OrderDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/clients/{clientId}/orders/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderDetailUIController extends AbstractOrderDetailController {

    @Override
    @GetMapping("/details")
    public List<OrderDetail> getAllByOrderIdWithProduct(@PathVariable int clientId, @PathVariable int orderId) {
        return super.getAllByOrderIdWithProduct(clientId, orderId);
    }

    @Override
    @GetMapping("/details/{id}")
    public OrderDetail get(@PathVariable int clientId, @PathVariable int orderId, @PathVariable int id) {
        return super.get(clientId, orderId, id);
    }

    @Override
    @PostMapping("/add-product/{productId}")
    public void addProduct(@PathVariable int clientId, @PathVariable int orderId, @PathVariable int productId) {
        super.addProduct(clientId, orderId, productId);
    }

    @Override
    @DeleteMapping("/details/{id}")
    public void delete(@PathVariable int clientId, @PathVariable int orderId, @PathVariable int id) {
        super.delete(clientId, orderId, id);
    }

    @PostMapping("/details/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(OrderDetail orderDetail, @PathVariable int clientId, @PathVariable int orderId, @PathVariable int productId) {
        super.update(orderDetail, orderDetail.getId(), clientId, orderId, productId);
    }
}


