package by.deliveryservice.testdata;

import by.deliveryservice.MatcherFactory;
import by.deliveryservice.model.Client;

import java.time.LocalDate;

public class ClientTestData {

    public static final int CLIENT_ID_1 = 1;
    public static final int CLIENT_ID_2 = CLIENT_ID_1 + 1;
    public static final int CLIENT_ID_3 = CLIENT_ID_2 + 1;

    public static final MatcherFactory.Matcher<Client> CLIENT_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Client.class, "registered", "orders");

    public static final Client client1 = new Client(CLIENT_ID_1, "Андрей", "Будевич", "Чеславович",
            "г.Лида, ул. Радунская д.19", LocalDate.parse("1986-04-02"));
    public static final Client client2 = new Client(CLIENT_ID_2, "Татьяна", "Будевич", "Александровна",
            "г.Лида, ул. Машерова 21", LocalDate.parse("1990-06-20"));
    public static final Client client3 = new Client(CLIENT_ID_3, "Ксения", "Будевич", "Андреева",
            "г.Лида, ул. Машерова 27", LocalDate.parse("2012-07-26"));

    public static Client getUpdated() {
        return new Client(CLIENT_ID_1, "Сменил Имя", "Сменил Фамилию", "Сменил Отчество",
                "Сменил адрес проживания", LocalDate.parse("1986-12-18"));
    }

    public static Client getNew() {
        return new Client("Александр", "Василючек", "Феликсович",
                "Димитрова 6", LocalDate.parse("1980-01-01"));
    }
}

