package by.deliveryservice.web.shop;

import by.deliveryservice.model.Shop;
import by.deliveryservice.repository.ShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.deliveryservice.util.validation.ValidationUtil.assureIdConsistent;
import static by.deliveryservice.util.validation.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractShopController {

    @Autowired
    private ShopRepository shopRepository;

    public Shop get(int id) {
        log.info("get shop {}", id);
        return shopRepository.get(id).orElse(null);
    }

    public void delete(int id) {
        log.info("delete shop {}", id);
        shopRepository.delete(id);
    }

    public List<Shop> getAll() {
        log.info("getAll for shop");
        return shopRepository.getAll();
    }

    public Shop create(Shop shop) {
        checkNew(shop);
        log.info("create {}", shop);
        return shopRepository.save(shop);
    }

    public void update(Shop shop, int id) {
        assureIdConsistent(shop, id);
        log.info("update {}", shop);
        shopRepository.save(shop);
    }
}