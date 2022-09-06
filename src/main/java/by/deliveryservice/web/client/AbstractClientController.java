package by.deliveryservice.web.client;

import by.deliveryservice.model.Client;
import by.deliveryservice.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.deliveryservice.util.validation.ValidationUtil.*;

@Slf4j
public abstract class AbstractClientController {

    @Autowired
    private ClientRepository clientRepository;

    public Client get(int id) {
        log.info("get client {}", id);
        return checkNotFoundWithId(clientRepository.get(id).orElse(null), id);
    }

    public void delete(int id) {
        log.info("delete client {}", id);
        clientRepository.delete(id);
    }

    public List<Client> getAll() {
        log.info("getAll for client");
        return clientRepository.getAll();
    }

    public Client create(Client client) {
        checkNew(client);
        log.info("create {}", client);
        return clientRepository.save(client);
    }

    public void update(Client client, int id) {
        assureIdConsistent(client, id);
        log.info("update {}", client);
        clientRepository.save(client);
    }
}