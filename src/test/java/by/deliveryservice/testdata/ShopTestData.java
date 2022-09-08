package by.deliveryservice.testdata;

import by.deliveryservice.MatcherFactory;
import by.deliveryservice.model.Shop;

public class ShopTestData {
    public static final int SHOP_ID_1 = 1;
    public static final int SHOP_ID_2 = SHOP_ID_1 + 1;
    public static final int SHOP_ID_3 = SHOP_ID_2 + 1;

    public static final MatcherFactory.Matcher<Shop> SHOP_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Shop.class);

    public static final Shop shop1 = new Shop(SHOP_ID_1, "Auchan", "г.Лида, ул. Машерова 63", "Продовольственный магазин", "80447324144");
    public static final Shop shop2 = new Shop(SHOP_ID_2, "Евроопт", " г.Лида, ул. Советская 1", "Продовольственный магазин", "80297634349");
    public static final Shop shop3 = new Shop(SHOP_ID_3, "5 Элемент", "г.Лида, ул. Тавлая 10", "Магазин бытовой техники", "80294544545");

    public static Shop getUpdated() {
        return new Shop(SHOP_ID_1, "Новый магазин", "Новый адрес", "Обновленное описание", "Обновленные контакты");
    }

    public static Shop getNew() {
        return new Shop(null, "Новый магазин", "Новый адрес", "Новое описание", "Новые контакты");
    }
}

