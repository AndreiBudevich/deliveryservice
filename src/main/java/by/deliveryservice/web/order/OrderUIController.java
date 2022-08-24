package by.deliveryservice.web.order;

import by.deliveryservice.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderUIController extends AbstractOrderController {
    @GetMapping()
    @Override
    public List<Order> getAll() {
        return super.getAll();
    }

    @GetMapping("/clients/{clientId}")
    @Override
    public List<Order> getAllByClientId(@PathVariable int clientId) {
        return super.getAllByClientId(clientId);
    }

    @Override
    @GetMapping("/{id}")
    public Order get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(Order order) {
        if (order.isNew()) {
            super.create(order);
        } else {
            super.update(order, order.getId());
        }
    }
}
