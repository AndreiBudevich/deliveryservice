package by.deliveryservice.web.order;

import by.deliveryservice.dto.OrderDto;
import by.deliveryservice.dto.OrderWithClientDto;
import by.deliveryservice.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderUIController extends AbstractOrderController {

    @GetMapping("/orders")
    @Override
    public List<OrderWithClientDto> getAll() {
        return super.getAll();
    }

    @GetMapping("/{clientId}/orders")
    @Override
    public List<OrderDto> getAllByClientId(@PathVariable int clientId) {
        return super.getAllByClientId(clientId);
    }

    @Override
    @GetMapping("/{clientId}/orders/{id}")
    public OrderDto get(@PathVariable int id, @PathVariable int clientId) {
        return super.get(id, clientId);
    }

    @Override
    @DeleteMapping("/{clientId}/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int clientId) {
        super.delete(id, clientId);
    }

    @PostMapping("/{clientId}/orders")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(Order order, @PathVariable int clientId) {
        if (order.isNew()) {
            super.create(order, clientId);
        } else {
            super.update(order, order.getId(), clientId);
        }
    }

    @Override
    @PostMapping("/{clientId}/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ship(@PathVariable int id, @PathVariable int clientId) {
        super.ship(id, clientId);
    }
}
