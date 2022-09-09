package by.deliveryservice.web.order.details;

import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.web.View;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = OrderDetailRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderDetailRestController extends AbstractOrderDetailController {

    static final String REST_URL = "/rest/clients/{clientId}/orders/{orderId}";

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
    @DeleteMapping("/details/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int clientId, @PathVariable int orderId, @PathVariable int id) {
        super.delete(clientId, orderId, id);
    }

    @PostMapping("/details/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody OrderDetail orderDetail, @PathVariable int clientId,
                       @PathVariable int orderId, @PathVariable int productId) {
        super.update(orderDetail, orderDetail.getId(), clientId, orderId, productId);
    }

    @Override
    @PostMapping("/add-product/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProduct(@PathVariable int clientId, @PathVariable int orderId, @PathVariable int productId) {
        super.addProduct(clientId, orderId, productId);
    }

    @Override
    @PostMapping("/delete-product/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int clientId, @PathVariable int orderId, @PathVariable int productId) {
        super.deleteProduct(clientId, orderId, productId);
    }
}


