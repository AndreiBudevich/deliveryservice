package by.deliveryservice.web.client;

import by.deliveryservice.model.Client;
import by.deliveryservice.web.View;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientUIController extends AbstractClientController {
    @GetMapping()
    @Override
    public List<Client> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Client get(@PathVariable int id) {
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
    public void createOrUpdate(@Validated(View.Web.class) Client client) {
        if (client.isNew()) {
            super.create(client);
        } else {
            super.update(client, client.getId());
        }
    }
}
