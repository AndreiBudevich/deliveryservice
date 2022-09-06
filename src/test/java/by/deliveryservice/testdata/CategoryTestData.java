package by.deliveryservice.testdata;

import by.deliveryservice.MatcherFactory;
import by.deliveryservice.model.Category;

public class CategoryTestData {
    public static final int CATEGORY_ID_1 = 1;
    public static final int CATEGORY_ID_2 = CATEGORY_ID_1 + 1;
    public static final int CATEGORY_ID_3 = CATEGORY_ID_2 + 1;

    public static final MatcherFactory.Matcher<Category> CATEGORY_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Category.class, "products");

    public static final Category category1 = new Category(CATEGORY_ID_1, "Продукты");
    public static final Category category2 = new Category(CATEGORY_ID_2, "Бытовая техника");
    public static final Category category3 = new Category(CATEGORY_ID_3, "товары со скидкой");

    public static Category getUpdated() {
        return new Category(CATEGORY_ID_1, "Продукты свежие");
    }

    public static Category getNew() {
        return new Category("Крупная Бытовая техника");
    }
}