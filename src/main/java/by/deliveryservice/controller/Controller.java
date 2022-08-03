package by.deliveryservice.controller;

import by.deliveryservice.model.BaseEntity;
import by.deliveryservice.model.Category;
import by.deliveryservice.util.ProxyUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static by.deliveryservice.util.EntityUtil.creatEntityFromString;
import static by.deliveryservice.util.EntityUtil.getEntitiesByIdsArray;
import static by.deliveryservice.util.RepositoryUtil.getRepositoryClass;
import static by.deliveryservice.view.EntityPrint.print;

public class Controller {

    private static final Map<String, String[]> acceptableCommands = new HashMap<>();

    static {
        acceptableCommands.put("client", new String[]{"getall", "delete", "create", "update"});
        acceptableCommands.put("category", new String[]{"getall", "delete", "create", "update"});
        acceptableCommands.put("order", new String[]{"getall", "delete", "create", "update"});
        acceptableCommands.put("product", new String[]{"getall", "delete", "create", "update", "getsortprice", "addcategories"});
        acceptableCommands.put("shop", new String[]{"getall", "delete", "create", "update"});
    }

    public static void runApplication() {
        String[] parameters;
        System.out.println("Введите команду.\n" +
                "Для выхода из системы введите 'stop'.");
        do {
            parameters = getSplit(getReadLine(), "/");
            if (parameters.length > 1 && checkAcceptableEntity(parameters[0], parameters[1])) {
                controller(parameters);
            } else {
                if (!parameters[0].equals("stop")) {
                    System.out.println("Команда не распознана");
                }
            }
        } while (!parameters[0].equals("stop"));
    }

    private static void controller(String[] parameters) {
        Class<?> clazzRepository = getRepositoryClass(parameters[0]);
        if (contains(parameters[1], "get")) {
            print(ProxyUtil.getInstance(clazzRepository, parameters[1]));
        }
        if (equals(parameters[1], "create")) {
            BaseEntity baseEntity = creatEntityFromString(parameters[0], getSplit(parameters[2], "; "));
            if (baseEntity != null) {
                ProxyUtil.getInstance(clazzRepository, "save", baseEntity);
            }
        }
        if (equals(parameters[1], "update")) {
            BaseEntity baseEntity = creatEntityFromString(parameters[0], getSplit(parameters[3], "; "));
            if (baseEntity != null) {
                baseEntity.setId(Integer.parseInt(parameters[2]));
                ProxyUtil.getInstance(clazzRepository, "save", baseEntity);
            }
        }
        if (equals(parameters[1], "delete")) {
            ProxyUtil.getInstance(clazzRepository, "delete", Integer.parseInt(parameters[2]));
        }
        if (contains(parameters[1], "addcategories")) {
            Category[] categories = getEntitiesByIdsArray(Category.class, getSplit(parameters[3], "; "));
            ProxyUtil.getInstance(clazzRepository, "addcategories", Integer.parseInt(parameters[2]), categories);
        }
    }

    private static boolean checkAcceptableEntity(String entityExpectedName, String commandExpectedName) {
        if (acceptableCommands.containsKey(entityExpectedName)) {
            long count = Arrays.stream(acceptableCommands.get(entityExpectedName))
                    .filter(c -> equals(c, commandExpectedName)).count();
            return count > 0;
        }
        return false;
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

    private static boolean equals(String actualCommand, String expectedCommand) {
        return actualCommand.toLowerCase(Locale.ROOT).equals(expectedCommand);
    }

    private static boolean contains(String actualCommand, String expectedCommand) {
        return actualCommand.toLowerCase(Locale.ROOT).contains(expectedCommand);
    }

    private static String[] getSplit(String stringEntity, String regex) {
        return stringEntity.split(regex);
    }
}
