package by.deliveryservice.testdata;

import by.deliveryservice.MatcherFactory;
import by.deliveryservice.model.Order;

import static by.deliveryservice.testdata.ClientTestData.*;

public class OrderTestData {

    public static final int ORDER_ID_1 = 1;
    public static final int ORDER_ID_2 = ORDER_ID_1 + 1;
    public static final int ORDER_ID_3 = ORDER_ID_2 + 1;
    public static final int ORDER_ID_4 = ORDER_ID_3 + 1;

    public static final MatcherFactory.Matcher<Order> ORDER_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Order.class, "registered", "client", "totalCost", "shipped");

    public static final Order order1 = new Order(ORDER_ID_1, client1, "г.Лида, ул. Радунская д.19");
    public static final Order order2 = new Order(ORDER_ID_2, client2, "г.Лида, г.Лида, ул. Машерова 21");
    public static final Order order3 = new Order(ORDER_ID_3, client2, "г.Лида, ул. Машерова 21");
    public static final Order order4 = new Order(ORDER_ID_4, client3, "г.Лида, ул. Машерова 27");

    public static Order getUpdated() {
        return new Order(ORDER_ID_1, client1, "Новый адрес");
    }

    public static Order getNew() {
        return new Order(client1, "г.Лида, ул. Радунская д.25");
    }
}

