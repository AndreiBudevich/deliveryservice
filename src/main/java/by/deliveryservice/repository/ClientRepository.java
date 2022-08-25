package by.deliveryservice.repository;

import by.deliveryservice.model.Client;

public interface ClientRepository extends BaseRepository<Client> {
    Client save(Client client);
}
