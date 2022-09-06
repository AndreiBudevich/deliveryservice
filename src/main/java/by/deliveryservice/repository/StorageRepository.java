package by.deliveryservice.repository;

import by.deliveryservice.model.Storage;

import java.util.List;
import java.util.Optional;

public interface StorageRepository extends BaseRepository<Storage> {

    List<Storage> getAllWithProduct(int shopId);

    Storage save(Storage storage, int shopId, int productId);

    Optional<Storage> getByProductId(int productId);
}
