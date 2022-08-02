package by.deliveryservice.controller;

import by.deliveryservice.model.BaseEntity;
import by.deliveryservice.util.ProxyUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static by.deliveryservice.util.EntityUtil.creatEntityFromString;
import static by.deliveryservice.util.RepositoryUtil.getRepositoryClass;
import static by.deliveryservice.view.EntityPrint.print;

public class Controller {

    private static final Map<String, String[]> acceptableCommands = new HashMap<>();

    static {
        acceptableCommands.put("client", new String[]{"getall", "delete", "create", "update"});
        acceptableCommands.put("category", new String[]{"getall", "delete", "create", "update"});
        acceptableCommands.put("order", new String[]{"getall", "delete", "create", "update"});
        acceptableCommands.put("product", new String[]{"getall", "delete", "create", "update", "getsortprice"});
        acceptableCommands.put("shop", new String[]{"getall", "delete", "create", "update"});
    }

    public static void runAplication() {
        String[] commands;
        System.out.println("Введите команду.\n" +
                "Для выхода из системы введите 'stop'.");
        do {
            commands = getSplitReadLine();
            if (commands.length > 1 && checkAcceptableEntity(commands[0], commands[1])) {
                Class<?> clazzRepository = getRepositoryClass(commands[0]);

                if (contains(commands[1], "get")) {
                    print(ProxyUtil.getInstance(clazzRepository, commands[1]));
                }
                if (equals(commands[1], "create")) {
                    BaseEntity baseEntity = creatEntityFromString(commands[0], commands[2]);
                    if (baseEntity != null) {
                        ProxyUtil.getInstance(clazzRepository, "save", baseEntity);
                    }
                }
                if (equals(commands[1], "update")) {
                    BaseEntity baseEntity = creatEntityFromString(commands[0], commands[3]);
                    if (baseEntity != null) {
                        baseEntity.setId(Integer.parseInt(commands[2]));
                        ProxyUtil.getInstance(clazzRepository, "save", baseEntity);
                    }
                }
                if (equals(commands[1], "delete")) {
                    ProxyUtil.getInstance(clazzRepository, "delete", Integer.parseInt(commands[2]));
                }
            } else {
                if (!commands[0].equals("stop")) {
                    System.out.println("Команда не распознана");
                }
            }

        } while (!commands[0].equals("stop"));
    }

    private static boolean checkAcceptableEntity(String entityExpectedName, String commandExpectedName) {
        if (acceptableCommands.containsKey(entityExpectedName)) {
            long count = Arrays.stream(acceptableCommands.get(entityExpectedName))
                    .filter(c -> equals(c, commandExpectedName)).count();
            return count > 0;
        }
        return false;
    }

    private static String[] getSplitReadLine() {

        String topic = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            topic = br.readLine();
        } catch (IOException exc) {
            System.out.println("Ошибка при чтении с консоли");
        }
        return topic.split("/");
    }

    private static boolean equals(String actualCommand, String expectedCommand) {
        return actualCommand.toLowerCase(Locale.ROOT).equals(expectedCommand);
    }

    private static boolean contains (String actualCommand, String expectedCommand) {
        return actualCommand.toLowerCase(Locale.ROOT).contains(expectedCommand);
    }
}
