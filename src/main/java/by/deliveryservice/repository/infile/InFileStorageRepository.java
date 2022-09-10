package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Storage;
import by.deliveryservice.repository.ProductRepository;
import by.deliveryservice.repository.ShopRepository;
import by.deliveryservice.repository.StorageRepository;

import java.util.List;
import java.util.Optional;

import static by.deliveryservice.util.validation.ValidationUtil.checkNotFoundWithId;

public class InFileStorageRepository extends InFileRepository<Storage> implements StorageRepository {

    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;

    public InFileStorageRepository() {
        super("json/storage.json", Storage.class);
        this.productRepository = new InFileProductRepository();
        this.shopRepository = new InFileShopRepository();
    }

    @Override
    public List<Storage> getAllWithProduct(int shopId) {
        readInFile();
        return repositoryInMemory.values().stream()
                .filter(storage -> storage.getShop().getId() == shopId)
                .toList();
    }

    @Override
    public Storage save(Storage storage, int shopId, int productId) {
        storage.setShop(checkNotFoundWithId(shopRepository.get(shopId).orElse(null), shopId));
        storage.setProduct(checkNotFoundWithId(productRepository.get(productId).orElse(null), productId));
        return super.save(storage);
    }

    @Override
    public Optional<Storage> getByProductId(int productId) {
        readInFile();
        return repositoryInMemory.values().stream()
                .filter(storage -> storage.getProduct().getId() == productId)
                .findFirst();
    }
}