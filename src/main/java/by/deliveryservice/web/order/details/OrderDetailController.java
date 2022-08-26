package by.deliveryservice.web.order.details;

import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/clients/{clientId}/orders/{id}/details", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderDetailController {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @GetMapping
    List<OrderDetail> getByOrderId(@PathVariable int id, @PathVariable int clientId) {
        return orderDetailRepository.getByOrderId(id);
    }
}

