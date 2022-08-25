package by.deliveryservice.util;

import by.deliveryservice.model.*;
import by.deliveryservice.repository.BaseRepository;
import by.deliveryservice.repository.infile.InFileCategoryRepository;
import by.deliveryservice.repository.infile.InFileProductRepository;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.LongStream;

import static by.deliveryservice.util.DateTimeUtil.getBirthday;
import static by.deliveryservice.util.RepositoryUtil.getRepositoryClass;

@UtilityClass
public class EntityUtil {

    private static final String PATTERN_CLIENT = "*surname; name; middleName; residentialAddress; dateOfBirth*";
    private static final String PATTERN_CATEGORY = "*name;*";
    private static final String PATTERN_SHOP = "*name; address; description; contact*";
    private static final String PATTERN_PRODUCT = "*name; description; id shop; price; discount*";
    private static final String PATTERN_ORDER = "*id client*";

    Map<String, String> patterns = new HashMap<>();

    static {
        patterns.put("client", PATTERN_CLIENT);
        patterns.put("category", PATTERN_CATEGORY);
        patterns.put("shop", PATTERN_SHOP);
        patterns.put("order", PATTERN_ORDER);
        patterns.put("product", PATTERN_PRODUCT);
    }

    public static BaseEntity creatEntityFromString(String stringNameEntity, String[] fields) {
        try {
            switch (stringNameEntity) {
                case ("client"):
                    return new Client(fields[1], fields[0], fields[2], fields[3], getBirthday(fields[4]));
                case ("category"):
                    return new Category(fields[0]);
                case ("order"):
                    return new Order(Objects.requireNonNull(getEntity(fields[0], "client")), null);
                case ("product"):
                    return new Product(fields[0], fields[1], Objects.requireNonNull(getEntity(fields[2], "shop")),
                            Long.parseLong(fields[3]), Integer.parseInt(fields[4]));
                case ("shop"):
                    return new Shop(fields[0], fields[1], fields[2], fields[3]);
            }
        } catch (Exception e) {
            System.out.println("Не верно введены поля " + stringNameEntity + " по шаблону \n" +
                    patterns.get(stringNameEntity));
        }
        return null;
    }

    private static <T> T getEntity(String id, String className) {
        BaseRepository<T> repository = null;
        Class<?> clazzRepository = getRepositoryClass(className);
        try {
            Object o = clazzRepository.newInstance();
            if (o instanceof BaseRepository) {
                repository = (BaseRepository<T>) o;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return repository != null ? repository.get(Integer.parseInt(id)).orElse(null) : null;
    }


    //The method gets fields from an array of strings and retrieves entities
    public static <T> T[] getEntitiesByIdsArray(Class<?> clazz, String... ids) {
        if (clazz == Category.class) {
            return (T[]) getEntitiesInRepositoryByIdsArray(clazz, new InFileCategoryRepository(), ids);
        }
        if (clazz == Product.class) {
            return (T[]) getEntitiesInRepositoryByIdsArray(clazz, new InFileProductRepository(), ids);
        }
        return null;
    }

    private <T> T[] getEntitiesInRepositoryByIdsArray(Class<?> clazz, BaseRepository<T> repository, String[] ids) {
        return Arrays.stream(ids)
                .map(id -> repository.get(Integer.parseInt(id)).orElse(null))
                .toArray(size -> (T[]) Array.newInstance(clazz, size));
    }

    public static Long calculationTotalCost(List<Product> products) {
        return products.stream().flatMapToLong(product -> LongStream.of(product.getPrice())).sum();
    }
}

