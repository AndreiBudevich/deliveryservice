package by.deliveryservice.testdata;

import by.deliveryservice.MatcherFactory;
import by.deliveryservice.model.Product;

public class ProductTestData {

    public static final int PRODUCT_ID_1 = 1;
    public static final int PRODUCT_ID_2 = PRODUCT_ID_1 + 1;
    public static final int PRODUCT_ID_3 = PRODUCT_ID_2 + 1;
    public static final int PRODUCT_ID_4 = PRODUCT_ID_3 + 1;
    public static final int PRODUCT_ID_5 = PRODUCT_ID_4 + 1;
    public static final int PRODUCT_ID_6 = PRODUCT_ID_5 + 1;
    public static final int PRODUCT_ID_7 = PRODUCT_ID_6 + 1;

    public static final MatcherFactory.Matcher<Product> PRODUCT_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Product.class, "orders", "categories", "shop");

    public static final Product product1 = new Product(PRODUCT_ID_1, "Телевизор LG", "Smart", null, 3000L, 0);
    public static final Product product2 = new Product(PRODUCT_ID_2, "Телевизор Samsung", "Smart", null, 3500L, 0);
    public static final Product product3 = new Product(PRODUCT_ID_3, "Фен Philips", "с насадками", null, 150L, 10);
    public static final Product product4 = new Product(PRODUCT_ID_4, "Фен Braun", "с грилем", null, 200L, 10);
    public static final Product product5 = new Product(PRODUCT_ID_5, "Микроволновка", "без насадок", null, 500L, 10);
    public static final Product product6 = new Product(PRODUCT_ID_6, "Апельсины", "цена за кг.", null, 10L, 3);
    public static final Product product7 = new Product(PRODUCT_ID_7, "Бананы", "цена за кг.", null, 7L, 5);

    public static Product getUpdated() {
        return new Product(PRODUCT_ID_1, "Обновленный продукт", "Обновленное описание", null, 1000L, 100);
    }

    public static Product getNew() {
        return new Product("Новый продукт", "Новое описание", null, 1000L, 0);
    }
}
