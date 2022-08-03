package by.deliveryservice.controller;

import by.deliveryservice.model.BaseEntity;
import by.deliveryservice.model.Category;
import by.deliveryservice.util.EntityUtil;
import by.deliveryservice.util.ProxyUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static by.deliveryservice.util.EntityUtil.*;
import static by.deliveryservice.util.RepositoryUtil.getRepositoryClass;
import static by.deliveryservice.view.EntityPrint.print;

public class Controller {

    private static final Map<String, String[]> acceptableCommands = new HashMap<>();

    static {
        acceptableCommands.put("client", new String[]{"getall", "delete", "create", "update"});
        acceptableCommands.put("category", new String[]{"getall", "delete", "create", "update"});
        acceptableCommands.put("order", new String[]{"getall", "delete", "create", "update"});
        acceptableCommands.put("product", new String[]{"getall", "delete", "create", "update", "getsortprice", "addcategories", "findbyattributes"});
        acceptableCommands.put("shop", new String[]{"getall", "delete", "create", "update"});
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
        if (contains(parameters[1], "get")) {
            print(ProxyUtil.getInstance(clazzRepository, parameters[1]));
        }
        if (EntityUtil.equals(parameters[1], "create")) {
            BaseEntity baseEntity = creatEntityFromString(parameters[0], getSplit(parameters[2], "; "));
            if (baseEntity != null) {
                ProxyUtil.getInstance(clazzRepository, "save", baseEntity);
            }
        }
        if (EntityUtil.equals(parameters[1], "update")) {
            BaseEntity baseEntity = creatEntityFromString(parameters[0], getSplit(parameters[3], "; "));
            if (baseEntity != null) {
                baseEntity.setId(Integer.parseInt(parameters[2]));
                ProxyUtil.getInstance(clazzRepository, "save", baseEntity);
            }
        }
        if (EntityUtil.equals(parameters[1], "delete")) {
            ProxyUtil.getInstance(clazzRepository, "delete", Integer.parseInt(parameters[2]));
        }
        if (EntityUtil.equals(parameters[1], "addCategories")) {
            Category[] categories = getEntitiesByIdsArray(Category.class, getSplit(parameters[3], "; "));
            ProxyUtil.getInstance(clazzRepository, "addCategories", Integer.parseInt(parameters[2]), categories);
        }
        if (EntityUtil.equals(parameters[1], "findByAttributes")) {
            print(ProxyUtil.getInstance(clazzRepository, "findByAttributes", (Object) getSplit(parameters[2], "; ")));
        }
    }

    private static boolean checkAcceptableCommand(String entityExpectedName, String commandExpectedName) {
        return acceptableCommands.containsKey(entityExpectedName) && Arrays.stream(acceptableCommands.get(entityExpectedName))
                .anyMatch(c -> EntityUtil.equals(c, commandExpectedName));
    }
}
