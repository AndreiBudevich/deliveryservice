package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Client;

import static by.deliveryservice.util.FileUtil.isEmpty;

public class InFileClientRepository extends InFileRepository<Client> {
    public InFileClientRepository() {
        super("json/clients.json", Client.class);
    }

    @Override
    public Client save(Client client) {
        int maxId = 0;
        if (!isEmpty(nameFile)) {
            readInFile();
            maxId = getMaxId();
        }
        if (client.isNew()) {
            maxId++;
            client.setId(maxId);
            repositoryInMemory.put(client.getId(), client);
            System.out.println("create " + client + "by id :" + maxId);
        } else {
            Client clientOld = repositoryInMemory.get(client.getId());
            client.setRegistered(clientOld.getRegistered());
            repositoryInMemory.computeIfPresent(client.getId(), (id, oldClient) -> client);
            System.out.println("update " + client + "by id :" + maxId);
        }
        saveInFile();
        return client;
    }
}
