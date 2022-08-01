package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Client;

public class InFileClientRepository extends InFileRepository<Client> {
    public InFileClientRepository() {
        super("json/clients.json", Client.class);
    }
}
