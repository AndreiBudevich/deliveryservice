package by.deliveryservice.web.stogare;

import by.deliveryservice.model.Storage;
import by.deliveryservice.repository.StorageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.deliveryservice.util.validation.ValidationUtil.assureIdConsistent;
import static by.deliveryservice.util.validation.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractStorageController {

    @Autowired
    private StorageRepository storageRepository;

    public Storage get(int shopId, int id) {
        log.info("get storage {} by shop {}", id, shopId);
        return storageRepository.get(id).orElse(null);
    }

    public List<Storage> getAllWithProduct(int shopId) {
        log.info("get all with product for storage by shop {}", shopId);
        return storageRepository.getAllWithProduct(shopId);
    }

    public Storage create(Storage storage, int shopId, int productId) {
        checkNew(storage);
        log.info("create storage {} by shop {} by product {}", storage, shopId, productId);
        return storageRepository.save(storage, shopId, productId);
    }

    public void update(Storage storage, int id, int shopId, int productId) {
        assureIdConsistent(storage, id);
        log.info("update storage {} by shop {} by product {}", storage, shopId, productId);
        storageRepository.save(storage, shopId, productId);
    }
}