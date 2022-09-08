package by.deliveryservice.web.order;

import by.deliveryservice.dto.OrderDto;
import by.deliveryservice.dto.OrderWithClientDto;
import by.deliveryservice.model.Order;
import by.deliveryservice.web.View;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = OrderRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRestController extends AbstractOrderController {

    static final String REST_URL = "/rest/clients";

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

    @PutMapping(value = "/{clientId}/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Order order, @PathVariable int clientId) {
        super.update(order, order.getId(), clientId);
    }

    @PostMapping(value = "/{clientId}/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createWithLocation(@Validated(View.Web.class) @RequestBody Order order, @PathVariable int clientId) {
        Order created = super.create(order, clientId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PostMapping("/{clientId}/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ship(@PathVariable int id, @PathVariable int clientId) {
        super.ship(id, clientId);
    }
}
