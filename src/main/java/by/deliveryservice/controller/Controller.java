package by.deliveryservice.controller;

import by.deliveryservice.model.BaseEntity;
import by.deliveryservice.model.Category;
import by.deliveryservice.model.Product;
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

public class Controller {

    private static final Map<String, String[]> extendedCommands = new HashMap<>();
    private static final Set<String> baseCommands = new HashSet<>(Arrays.asList("getall", "delete", "create", "update"));
    private static final String[] productsCommands = new String[]{"getsortprice", "addcategories", "deletecategories", "findbyattributes"};
    private static final String[] orderCommands = new String[]{"addproducts", "deleteproducts"};
    private static final String[] shopCommands = new String[]{"addproducts", "deleteproducts", "getshopproducts"};

    static {
        extendedCommands.put("client", null);
        extendedCommands.put("category", null);
        extendedCommands.put("order", orderCommands);
        extendedCommands.put("product", productsCommands);
        extendedCommands.put("shop", shopCommands);
    }

    private Controller() {
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

    private static void controller(String[] parameters) {
        Class<?> clazzRepository = getRepositoryClass(parameters[0]);
        String nameMethod = parameters[1].toLowerCase(Locale.ROOT);

        switch (nameMethod) {
            case ("getall"):
            case ("getsortprice"):
                print(ProxyUtil.getInstance(clazzRepository, nameMethod));
                break;
            case ("create"):
                saveAndUpdate(clazzRepository, parameters[0], nameMethod, null, parameters[2]);
                break;
            case ("update"):
                saveAndUpdate(clazzRepository, parameters[0], nameMethod, parameters[2], parameters[3]);
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
            case ("addcategories"):
            case ("deletecategories"):
                operationsEntities(Category.class, clazzRepository, nameMethod, parameters[2], parameters[3]);
                break;

            case ("addproducts"):
            case ("deleteproducts"):
                operationsEntities(Product.class, clazzRepository, nameMethod, parameters[2], parameters[3]);
                break;
        }
    }

    private static void saveAndUpdate(Class<?> clazzRepository, String stringNameEntity, String nameMethod, String id, String fields) {
        BaseEntity baseEntity = creatEntityFromString(stringNameEntity, getSplit(fields, "; "));
        if (baseEntity != null) {
            if (nameMethod.equalsIgnoreCase("update")) {
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

    private static boolean checkAcceptableCommand(String entityExpectedName, String commandExpectedName) {
        return extendedCommands.containsKey(entityExpectedName)
                && baseCommands.contains(commandExpectedName.toLowerCase(Locale.ROOT)) || Arrays.stream(extendedCommands.get(entityExpectedName))
                .anyMatch(c -> c.equalsIgnoreCase(commandExpectedName));
    }
}
