package by.deliveryservice.web.client;

import by.deliveryservice.model.Client;
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
@RequestMapping(value = ClientRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientRestController extends AbstractClientController {

    static final String REST_URL = "/rest/clients";

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

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Client client, @PathVariable int id) {
        super.update(client, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> createWithLocation(@Validated(View.Web.class) @RequestBody Client client) {
        Client created = super.create(client);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}