package by.deliveryservice.repository.datajpa;

import by.deliveryservice.model.Storage;
import by.deliveryservice.repository.StorageRepository;
import by.deliveryservice.repository.datajpa.crud.ProductCrudRepository;
import by.deliveryservice.repository.datajpa.crud.ShopCrudRepository;
import by.deliveryservice.repository.datajpa.crud.StorageCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaStorageRepository implements StorageRepository {

    private final StorageCrudRepository storageCrudRepository;
    private final ProductCrudRepository productCrudRepository;
    private final ShopCrudRepository shopCrudRepository;

    public DataJpaStorageRepository(StorageCrudRepository storageCrudRepository, ProductCrudRepository productCrudRepository,
                                    ShopCrudRepository shopCrudRepository) {
        this.storageCrudRepository = storageCrudRepository;
        this.productCrudRepository = productCrudRepository;
        this.shopCrudRepository = shopCrudRepository;
    }

    @Override
    public List<Storage> getAll() {
        return storageCrudRepository.findAll();
    }

    @Override
    public List<Storage> getAllWithProduct(int shopId) {
        return storageCrudRepository.getAllWithProduct(shopId);
    }

    @Override
    public Optional<Storage> get(int id) {
        return storageCrudRepository.get(id);
    }

    @Override
    public void delete(int id) {
        storageCrudRepository.deleteById(id);
    }

    @Override
    public Storage save(Storage storage, int shopId, int productId) {
        storage.setShop(shopCrudRepository.getReferenceById(shopId));
        storage.setProduct(productCrudRepository.getReferenceById(productId));
        return storageCrudRepository.save(storage);
    }

    @Override
    public Optional<Storage> getByProductId(int productId) {
        return storageCrudRepository.getByProductId(productId);
    }
}
