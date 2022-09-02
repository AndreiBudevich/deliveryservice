package by.deliveryservice.repository.datajpa.crud;

import by.deliveryservice.model.Client;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ClientCrudRepository extends CommonCrudRepository<Client> {
}
