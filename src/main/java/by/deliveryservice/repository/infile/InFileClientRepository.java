package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Client;
import by.deliveryservice.repository.ClientRepository;

public class InFileClientRepository extends InFileRepository<Client> implements ClientRepository {

    public InFileClientRepository() {
        super("json/clients.json", Client.class);
    }

    @Override
    protected void update(Client client) {
        Client clientOld = repositoryInMemory.get(client.getId());
        client.setRegistered(clientOld.getRegistered());
        repositoryInMemory.computeIfPresent(client.getId(), (id, oldClient) -> client);
    }
}
