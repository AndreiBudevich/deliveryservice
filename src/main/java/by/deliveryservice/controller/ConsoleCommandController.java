package by.deliveryservice.controller;

import by.deliveryservice.model.BaseEntity;
import by.deliveryservice.service.infile.OrderDetailServiceImplInFile;
import by.deliveryservice.service.infile.OrderServiceImplInFile;
import by.deliveryservice.service.infile.ProductServiceImplInFile;
import by.deliveryservice.util.ProxyUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static by.deliveryservice.error.ExceptionMessage.ERROR_READING_FROM_CONSOLE;
import static by.deliveryservice.error.ExceptionMessage.INVALID_COMMAND_ENTERED;
import static by.deliveryservice.util.EntityBuilder.createEntity;
import static by.deliveryservice.util.RepositoryUtil.getRepositoryClass;
import static by.deliveryservice.util.StringUtil.getSplit;
import static by.deliveryservice.view.EntityPrint.print;

@Slf4j
public class ConsoleCommandController {

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
    private static final String GET_SHOP_PRODUCTS = "getshopproducts";
    private static final String SET_QUANTITY = "setquantity";
    private static final String SHIP = "ship";

    private static final String ORDER = "order";
    private static final String PRODUCT = "product";
    private static final String CLIENT = "client";
    private static final String CATEGORY = "category";
    private static final String SHOP = "shop";

    private static final Map<String, String[]> extendedCommands = new HashMap<>();
    private static final Set<String> baseCommands = new HashSet<>(Arrays.asList(GET_ALL, DELETE, CREATE));
    private static final String[] productsCommands = new String[]{UPDATE, GET_SORT_PRICE, ADD_CATEGORY, DELETE_CATEGORY, FIND_BY_ATTRIBUTES};
    private static final String[] orderCommands = new String[]{GET, ADD_PRODUCT, DELETE_PRODUCT, SET_ADDRESS, SHIP};
    private static final String[] shopCommands = new String[]{UPDATE, SET_QUANTITY, GET_SHOP_PRODUCTS};
    private static final String[] clientCommands = new String[]{UPDATE};
    private static final String[] categoryCommands = new String[]{UPDATE};

    static {
        extendedCommands.put(CLIENT, clientCommands);
        extendedCommands.put(CATEGORY, categoryCommands);
        extendedCommands.put(ORDER, orderCommands);
        extendedCommands.put(PRODUCT, productsCommands);
        extendedCommands.put(SHOP, shopCommands);
    }

    private ConsoleCommandController() {
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
                    log.info(INVALID_COMMAND_ENTERED);
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
            log.info(ERROR_READING_FROM_CONSOLE);
        }
        return topic;
    }

    //The method takes a string and calls the corresponding method repository or service
    private static void controller(String[] parameters) {
        Class<?> clazzRepository = getRepositoryClass(parameters[0]);
        String nameMethod = parameters[1].toLowerCase(Locale.ROOT);
        try {
            switch (nameMethod) {
                case (GET_ALL), (GET_SORT_PRICE) -> print(ProxyUtil.getInstance(clazzRepository, nameMethod));
                case (CREATE) -> saveOrUpdate(clazzRepository, parameters[0], null, parameters[2]);
                case (UPDATE) -> saveOrUpdate(clazzRepository, parameters[0], getId(parameters[2]), parameters[3]);
                case (DELETE) -> ProxyUtil.getInstance(clazzRepository, nameMethod, getId(parameters[2]));
                case (GET_SHOP_PRODUCTS) -> print(ProxyUtil.getInstance(ProductServiceImplInFile.class, nameMethod, getId(parameters[2])));
                case (SET_QUANTITY) -> ProxyUtil.getInstance(ProductServiceImplInFile.class, nameMethod, getId(parameters[2]), Integer.parseInt(parameters[3]));
                case (ADD_CATEGORY), (DELETE_CATEGORY) -> ProxyUtil.getInstance(ProductServiceImplInFile.class, parameters[1], getId(parameters[2]), getId(parameters[3]));
                case (FIND_BY_ATTRIBUTES) -> print(ProxyUtil.getInstance(clazzRepository, nameMethod, (Object) getSplit(parameters[2], "; ")));
                case (GET) -> print(ProxyUtil.getInstance(OrderDetailServiceImplInFile.class, parameters[1], getId(parameters[2])));
                case (ADD_PRODUCT), (DELETE_PRODUCT) -> ProxyUtil.getInstance(OrderDetailServiceImplInFile.class, nameMethod, getId(parameters[2]), getId(parameters[3]));
                case (SET_ADDRESS) -> ProxyUtil.getInstance(OrderServiceImplInFile.class, nameMethod, getId(parameters[2]), parameters[3]);
                case (SHIP) -> ProxyUtil.getInstance(OrderServiceImplInFile.class, nameMethod, getId(parameters[2]));
            }
        } catch (Exception e) {
            log.info(INVALID_COMMAND_ENTERED);
        }
    }

    //The method gets fields from an array of strings and saves or updates the entities
    private static void saveOrUpdate(Class<?> clazzRepository, String nameEntity, Integer id, String fields) {
        String[] splitFields = getSplit(fields, "; ");
        if (nameEntity.equals(ORDER)) {
            ProxyUtil.getInstance(clazzRepository, "save", createEntity(ORDER, new String[0]), getId(splitFields[0]));
            return;
        }
        BaseEntity baseEntity = createEntity(nameEntity, splitFields);
        if (id != null) {
            assert baseEntity != null;
            baseEntity.setId(id);
        }
        if (nameEntity.equals(PRODUCT)) {
            ProxyUtil.getInstance(ProductServiceImplInFile.class, "save", baseEntity, getId(splitFields[0]));
            return;
        }
        ProxyUtil.getInstance(clazzRepository, "save", baseEntity);
    }

    //checking the command line for validity
    private static boolean checkAcceptableCommand(String nameEntity, String commandName) {
        if (extendedCommands.containsKey(nameEntity)) {
            return extendedCommands.containsKey(nameEntity)
                    && baseCommands.contains(commandName.toLowerCase(Locale.ROOT)) || Arrays.stream(extendedCommands.get(nameEntity))
                    .anyMatch(c -> c.equalsIgnoreCase(commandName));
        }
        return false;
    }

    private static Integer getId(String id) {
        return Integer.parseInt(id);
    }
}
