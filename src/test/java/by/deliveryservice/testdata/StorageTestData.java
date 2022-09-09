package by.deliveryservice.testdata;

import by.deliveryservice.MatcherFactory;
import by.deliveryservice.model.Storage;

import static by.deliveryservice.testdata.ProductTestData.*;
import static by.deliveryservice.testdata.ShopTestData.*;

public class StorageTestData {

    public static final int STORAGE_ID_1 = 1;
    public static final int STORAGE_ID_2 = STORAGE_ID_1 + 1;
    public static final int STORAGE_ID_3 = STORAGE_ID_2 + 1;
    public static final int STORAGE_ID_4 = STORAGE_ID_3 + 1;
    public static final int STORAGE_ID_5 = STORAGE_ID_4 + 1;
    public static final int STORAGE_ID_6 = STORAGE_ID_5 + 1;
    public static final int STORAGE_ID_7 = STORAGE_ID_6 + 1;

    public static final MatcherFactory.Matcher<Storage> STORAGE_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Storage.class, "shop", "product");

    public static final Storage storage1 = new Storage(STORAGE_ID_1, shop1, product1, 100);
    public static final Storage storage2 = new Storage(STORAGE_ID_2, shop2, product2, 200);
    public static final Storage storage3 = new Storage(STORAGE_ID_3, shop1, product3, 300);
    public static final Storage storage4 = new Storage(STORAGE_ID_4, shop3, product4, 400);
    public static final Storage storage5 = new Storage(STORAGE_ID_5, shop3, product5, 500);
    public static final Storage storage6 = new Storage(STORAGE_ID_6, shop2, product6, 600);
    public static final Storage storage7 = new Storage(STORAGE_ID_7, shop2, product7, 700);

    public static Storage getUpdated() {
        return new Storage(STORAGE_ID_1, shop1, product1, 1000);
    }
}

