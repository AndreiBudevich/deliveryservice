package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Client;
import by.deliveryservice.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaClientRepository implements BaseRepository<Client> {

    CommonCrudRepository<Client> clientCrudRepository;

    public DataJpaClientRepository(ClientCrudRepository clientCrudRepository) {
        this.clientCrudRepository = clientCrudRepository;
    }

    @Override
    public List<Client> getAll() {
        return clientCrudRepository.findAll();
    }

    @Override
    public Optional<Client> get(int id) {
        return clientCrudRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        clientCrudRepository.deleteById(id);
    }

    @Override
    public Client save(Client client) {
        return clientCrudRepository.save(client);
    }
}
