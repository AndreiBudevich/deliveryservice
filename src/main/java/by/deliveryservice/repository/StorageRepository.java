package by.deliveryservice.repository;

import by.deliveryservice.model.Storage;

import java.util.List;

public interface StorageRepository extends BaseRepository<Storage> {

    List<Storage> getAllWithProduct(int shopId);

    Storage save(Storage storage, int shopId, int productId);
}
