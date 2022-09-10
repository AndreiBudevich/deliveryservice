package by.deliveryservice.controller;

import by.deliveryservice.model.BaseEntity;
import by.deliveryservice.model.Product;
import by.deliveryservice.service.infile.OrderDetailServiceImplInFile;
import by.deliveryservice.service.infile.OrderServiceImplInFile;
import by.deliveryservice.service.infile.ProductServiceImplInFile;
import by.deliveryservice.util.ProxyUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static by.deliveryservice.util.EntityBuilder.createEntity;
import static by.deliveryservice.util.RepositoryUtil.getRepositoryClass;
import static by.deliveryservice.util.StringUtil.getSplit;
import static by.deliveryservice.view.EntityPrint.print;

@Slf4j
public class ControllerFileRepository {

    private static final String UPDATE = "update";
    private static final String GET_ALL = "getall";
    private static final String DELETE = "delete";
    private static final String CREATE = "create";
    private static final String GET = "get";
    private static final String GET_SORT_PRICE = "getsortprice";
    private static final String ADD_CATEGORY = "addcategory";
    private static final String DELETE_CATEGORY = "deletecategory";
    private static final String FIND_BY_ATTRIBUTES = "findbyattributes";
    private static final String ADD_PRODUCT = "addproduct";
    private static final String DELETE_PRODUCT = "deleteproduct";
    private static final String SET_ADDRESS = "setaddress";
    private static final String GET_SHOP_PRODUCT = "getshopproduct";

    private static final String ORDER = "order";
    private static final String PRODUCT = "product";
    private static final String CLIENT = "client";
    private static final String CATEGORY = "category";
    private static final String SHOP = "shop";

    private static final Map<String, String[]> extendedCommands = new HashMap<>();
    private static final Set<String> baseCommands = new HashSet<>(Arrays.asList(GET_ALL, DELETE, CREATE));
    private static final String[] productsCommands = new String[]{UPDATE, GET_SORT_PRICE, ADD_CATEGORY, DELETE_CATEGORY, FIND_BY_ATTRIBUTES};
    private static final String[] orderCommands = new String[]{GET, ADD_PRODUCT, DELETE_PRODUCT, SET_ADDRESS};
    private static final String[] shopCommands = new String[]{UPDATE, ADD_PRODUCT, DELETE_PRODUCT, GET_SHOP_PRODUCT};
    private static final String[] clientCommands = new String[]{UPDATE};
    private static final String[] categoryCommands = new String[]{UPDATE};

    static {
        extendedCommands.put(CLIENT, clientCommands);
        extendedCommands.put(CATEGORY, categoryCommands);
        extendedCommands.put(ORDER, orderCommands);
        extendedCommands.put(PRODUCT, productsCommands);
        extendedCommands.put(SHOP, shopCommands);
    }

    private ControllerFileRepository() {
    }

    public static void runApplication() {
        String[] parameters;
        log.info("Введите команду.\n" +
                "Для выхода из системы введите 'stop'.");
        do {
            parameters = getSplit(getReadLine(), "/");
            if (parameters.length > 1 && checkAcceptableCommand(parameters[0], parameters[1])) {
                controller(parameters);
            } else {
                if (!parameters[0].equals("stop")) {
                    log.info("Команда не распознана");
                }
            }
        } while (!parameters[0].equals("stop"));
    }

    private static String getReadLine() {
        String topic = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            topic = br.readLine();
        } catch (IOException exc) {
            log.info("Ошибка при чтении с консоли");
        }
        return topic;
    }

    //The method takes a string and calls the corresponding method repository or service
    private static void controller(String[] parameters) {
        Class<?> clazzRepository = getRepositoryClass(parameters[0]);
        String nameMethod = parameters[1].toLowerCase(Locale.ROOT);
        try {
            switch (nameMethod) {
                case (GET):
                    print(ProxyUtil.getInstance(OrderDetailServiceImplInFile.class, parameters[1], getId(parameters[2])));
                    break;
                case (GET_ALL), (GET_SORT_PRICE):
                    print(ProxyUtil.getInstance(clazzRepository, nameMethod));
                    break;
                case (CREATE):
                    if (parameters[0].equals(PRODUCT)) {
                        ProxyUtil.getInstance(ProductServiceImplInFile.class, "save",
                                createEntity(PRODUCT, getSplit(parameters[3], "; ")), getId(parameters[2]));
                    } else if (parameters[0].equals(ORDER)) {
                        ProxyUtil.getInstance(clazzRepository, "save",
                                createEntity(ORDER, new String[0]), getId(parameters[2]));
                    } else {
                        saveOrUpdate(clazzRepository, parameters[0], nameMethod, null, parameters[2]);
                    }
                    break;
                case (UPDATE):
                    saveOrUpdate(clazzRepository, parameters[0], nameMethod, parameters[2], parameters[3]);
                    break;
                case (DELETE):
                    ProxyUtil.getInstance(clazzRepository, nameMethod, Integer.parseInt(parameters[2]));
                    break;
                case (GET_SHOP_PRODUCT):
                    print(ProxyUtil.getInstance(clazzRepository, nameMethod, Integer.parseInt(parameters[2])));
                    break;
                case (FIND_BY_ATTRIBUTES):
                    print(ProxyUtil.getInstance(clazzRepository, nameMethod, (Object) getSplit(parameters[2], "; ")));
                    break;
                case (ADD_CATEGORY), (DELETE_CATEGORY):
                    ProxyUtil.getInstance(ProductServiceImplInFile.class, parameters[1], getId(parameters[2]), getId(parameters[3]));
                    break;
                case (ADD_PRODUCT), (DELETE_PRODUCT): {
                    if (parameters[0].equals(ORDER)) {
                        ProxyUtil.getInstance(OrderDetailServiceImplInFile.class, nameMethod, getId(parameters[2]), getId(parameters[3]));
                    } else {
                        /* operationsEntities(Product.class, clazzRepository, nameMethod, parameters[2], parameters[3]);*/
                    }
                    break;
                }
                case (SET_ADDRESS):
                    ProxyUtil.getInstance(OrderServiceImplInFile.class, nameMethod, Integer.parseInt(parameters[2]), parameters[3]);
                    break;
            }
        } catch (Exception e) {
            log.info("Неверно введена команда");
        }
    }

    //The method gets fields from an array of strings and saves or updates the entities
    private static void saveOrUpdate(Class<?> clazzRepository, String stringNameEntity, String nameMethod, String id, String fields) {
        BaseEntity baseEntity = createEntity(stringNameEntity, getSplit(fields, "; "));
        if (baseEntity != null) {
            if (nameMethod.equalsIgnoreCase(UPDATE)) {
                baseEntity.setId(Integer.parseInt(id));
            }
            ProxyUtil.getInstance(clazzRepository, "save", baseEntity);
        }
    }

    //checking the command line for validity
    private static boolean checkAcceptableCommand(String entityName, String commandName) {
        if (extendedCommands.containsKey(entityName)) {
            return extendedCommands.containsKey(entityName)
                    && baseCommands.contains(commandName.toLowerCase(Locale.ROOT)) || Arrays.stream(extendedCommands.get(entityName))
                    .anyMatch(c -> c.equalsIgnoreCase(commandName));
        }
        return false;
    }

    private static Integer getId(String id) {
        return Integer.parseInt(id);
    }
}
