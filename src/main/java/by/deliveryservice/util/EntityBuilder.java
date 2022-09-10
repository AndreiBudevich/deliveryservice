package by.deliveryservice.util;

import by.deliveryservice.model.*;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static by.deliveryservice.util.DateTimeUtil.getBirthday;

@Slf4j
@UtilityClass
public class EntityBuilder {

    private static final String PATTERN_CLIENT = "*surname; name; middleName; residentialAddress; dateOfBirth*";
    private static final String PATTERN_CATEGORY = "*name;*";
    private static final String PATTERN_SHOP = "*name; address; description; contact*";
    private static final String PATTERN_PRODUCT = "*id shop; name; description; price; discount*";
    private static final String PATTERN_ORDER = "*id client*";

    Map<String, String> patterns = new HashMap<>();

    static {
        patterns.put("client", PATTERN_CLIENT);
        patterns.put("category", PATTERN_CATEGORY);
        patterns.put("shop", PATTERN_SHOP);
        patterns.put("order", PATTERN_ORDER);
        patterns.put("product", PATTERN_PRODUCT);
    }

    public static BaseEntity createEntity(String nameEntity, String[] fields) {
        try {
            return switch (nameEntity) {
                case ("client") -> new Client(fields[1], fields[0], fields[2], fields[3], getBirthday(fields[4]));
                case ("category") -> new Category(fields[0]);
                case ("order") -> new Order(null, "");
                case ("product") -> new Product(fields[1], fields[2], null, Long.parseLong(fields[3]), Integer.parseInt(fields[4]));
                case ("shop") -> new Shop(fields[0], fields[1], fields[2], fields[3]);
                default -> null;
            };
        } catch (Exception e) {
            log.info("Не верно введены поля {} по шаблону {}", nameEntity, patterns.get(nameEntity));
        }
        return null;
    }
}


