package by.deliveryservice.controller;

import by.deliveryservice.model.BaseEntity;
import by.deliveryservice.model.Product;
import by.deliveryservice.service.infile.OrderDetailServiceImplInFile;
import by.deliveryservice.service.infile.OrderServiceImplInFile;
import by.deliveryservice.service.infile.ProductServiceImplInFile;
import by.deliveryservice.util.ProxyUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static by.deliveryservice.util.EntityUtil.creatEntityFromString;
import static by.deliveryservice.util.EntityUtil.getEntitiesByIdsArray;
import static by.deliveryservice.util.RepositoryUtil.getRepositoryClass;
import static by.deliveryservice.util.StringUtil.getSplit;
import static by.deliveryservice.view.EntityPrint.print;

public class ControllerFileRepository {

    private static final String UPDATE = "update";
    private static final String ORDER = "order";
    private static final String PRODUCT = "product";

    private static final Map<String, String[]> extendedCommands = new HashMap<>();
    private static final Set<String> baseCommands = new HashSet<>(Arrays.asList("getall", "delete", "create"));
    private static final String[] productsCommands = new String[]{UPDATE, "getsortprice", "addcategory", "deletecategory", "findbyattributes"};
    private static final String[] orderCommands = new String[]{UPDATE, "get", "addproduct", "deleteproduct", "setaddress"};
    private static final String[] shopCommands = new String[]{UPDATE, "addproducts", "deleteproducts", "getshopproducts"};

    static {
        extendedCommands.put("client", null);
        extendedCommands.put("category", null);
        extendedCommands.put(ORDER, orderCommands);
        extendedCommands.put(PRODUCT, productsCommands);
        extendedCommands.put("shop", shopCommands);
    }

    private ControllerFileRepository() {
    }

    public static void runApplication() {
        String[] parameters;
        System.out.println("Введите команду.\n" +
                "Для выхода из системы введите 'stop'.");
        do {
            parameters = getSplit(getReadLine(), "/");
            if (parameters.length > 1 && checkAcceptableCommand(parameters[0], parameters[1])) {
                controller(parameters);
            } else {
                if (!parameters[0].equals("stop")) {
                    System.out.println("Команда не распознана");
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
            System.out.println("Ошибка при чтении с консоли");
        }
        return topic;
    }

    //The method takes a string and calls the corresponding method repository or service
    private static void controller(String[] parameters) {
        Class<?> clazzRepository = getRepositoryClass(parameters[0]);
        String nameMethod = parameters[1].toLowerCase(Locale.ROOT);
        try {
            switch (nameMethod) {
                case ("get"):
                    print(ProxyUtil.getInstance(OrderDetailServiceImplInFile.class, parameters[1], getId(parameters[2])));
                    break;
                case ("getall"):
                case ("getsortprice"):
                    print(ProxyUtil.getInstance(clazzRepository, nameMethod));
                    break;
                case ("create"):
                    if (parameters[0].equals(PRODUCT)) {
                        ProxyUtil.getInstance(ProductServiceImplInFile.class, "save",
                                creatEntityFromString(PRODUCT, getSplit(parameters[2], "; ")));
                    }
                    if (parameters[0].equals(ORDER)) {
                        ProxyUtil.getInstance(clazzRepository, "save",
                                creatEntityFromString(ORDER, new String[0]), getId(parameters[2]));
                    } else {
                        saveOrUpdate(clazzRepository, parameters[0], nameMethod, null, parameters[2]);
                    }
                    break;
                case (UPDATE):
                    saveOrUpdate(clazzRepository, parameters[0], nameMethod, parameters[2], parameters[3]);
                    break;
                case ("delete"):
                    ProxyUtil.getInstance(clazzRepository, nameMethod, Integer.parseInt(parameters[2]));
                    break;
                case ("getshopproducts"):
                    print(ProxyUtil.getInstance(clazzRepository, nameMethod, Integer.parseInt(parameters[2])));
                    break;
                case ("findbyattributes"):
                    print(ProxyUtil.getInstance(clazzRepository, nameMethod, (Object) getSplit(parameters[2], "; ")));
                    break;
                case ("addcategory"):
                case ("deletecategory"):
                    ProxyUtil.getInstance(ProductServiceImplInFile.class, parameters[1], getId(parameters[2]), getId(parameters[3]));
                    break;
                case ("addproduct"):
                case ("deleteproduct"): {
                    if (parameters[0].equals(ORDER)) {
                        ProxyUtil.getInstance(OrderDetailServiceImplInFile.class, nameMethod, getId(parameters[2]), getId(parameters[3]));
                    } else {
                        operationsEntities(Product.class, clazzRepository, nameMethod, parameters[2], parameters[3]);
                    }
                    break;
                }
                case ("setaddress"):
                    ProxyUtil.getInstance(OrderServiceImplInFile.class, nameMethod, Integer.parseInt(parameters[2]), parameters[3]);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Неверно введена команда");
        }
    }

    //The method gets fields from an array of strings and saves or updates the entities
    private static void saveOrUpdate(Class<?> clazzRepository, String stringNameEntity, String nameMethod, String id, String fields) {
        BaseEntity baseEntity = creatEntityFromString(stringNameEntity, getSplit(fields, "; "));
        if (baseEntity != null) {
            if (nameMethod.equalsIgnoreCase(UPDATE)) {
                baseEntity.setId(Integer.parseInt(id));
            }
            ProxyUtil.getInstance(clazzRepository, "save", baseEntity);
        }
    }

    private static <T> void operationsEntities(Class<? extends BaseEntity> clazz, Class<?> clazzRepository,
                                               String nameMethod, String stringId, String ids) {
        T[] entities = getEntitiesByIdsArray(clazz, getSplit(ids, ", "));
        ProxyUtil.getInstance(clazzRepository, nameMethod, Integer.parseInt(stringId), entities);
    }

    //checking the command line for validity
    private static boolean checkAcceptableCommand(String entityExpectedName, String commandExpectedName) {
        if (extendedCommands.containsKey(entityExpectedName)) {
            return extendedCommands.containsKey(entityExpectedName)
                    && baseCommands.contains(commandExpectedName.toLowerCase(Locale.ROOT)) || Arrays.stream(extendedCommands.get(entityExpectedName))
                    .anyMatch(c -> c.equalsIgnoreCase(commandExpectedName));
        }
        return false;
    }

    private static Integer getId(String id) {
        return Integer.parseInt(id);
    }
}
