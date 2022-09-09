package by.deliveryservice.testdata;

import by.deliveryservice.MatcherFactory;
import by.deliveryservice.model.OrderDetail;

import static by.deliveryservice.testdata.OrderTestData.*;
import static by.deliveryservice.testdata.ProductTestData.*;

public class OrderDetailTestData {
    public static final int ORDER_DETAIL_ID_1 = 1;
    public static final int ORDER_DETAIL_ID_2 = ORDER_DETAIL_ID_1 + 1;
    public static final int ORDER_DETAIL_ID_3 = ORDER_DETAIL_ID_2 + 1;
    public static final int ORDER_DETAIL_ID_4 = ORDER_DETAIL_ID_3 + 1;
    public static final int ORDER_DETAIL_ID_5 = ORDER_DETAIL_ID_4 + 1;
    public static final int ORDER_DETAIL_ID_6 = ORDER_DETAIL_ID_5 + 1;
    public static final int ORDER_DETAIL_ID_7 = ORDER_DETAIL_ID_6 + 1;

    public static final MatcherFactory.Matcher<OrderDetail> ORDER_DETAIL_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(OrderDetail.class , "order", "product");

    public static final OrderDetail orderDetail1 = new OrderDetail(ORDER_DETAIL_ID_1, order1, product2, 3500L, 2, 7000L);
    public static final OrderDetail orderDetail2 = new OrderDetail(ORDER_DETAIL_ID_2, order1, product7, 7L, 1, 7L);
    public static final OrderDetail orderDetail3 = new OrderDetail(ORDER_DETAIL_ID_3, order2, product6, 10L, 5, 50L);
    public static final OrderDetail orderDetail4 = new OrderDetail(ORDER_DETAIL_ID_4, order2, product4, 200L, 2, 400L);
    public static final OrderDetail orderDetail5 = new OrderDetail(ORDER_DETAIL_ID_5, order2, product3, 150L, 2, 300L);
    public static final OrderDetail orderDetail6 = new OrderDetail(ORDER_DETAIL_ID_6, order3, product1, 3000L, 1, 3000L);
    public static final OrderDetail orderDetail7 = new OrderDetail(ORDER_DETAIL_ID_7, order4, product5, 500L, 2, 1000L);

    public static OrderDetail getUpdated() {
        return new OrderDetail(ORDER_DETAIL_ID_1, order1, product2, 3500L, 1, 3500L);
    }
}