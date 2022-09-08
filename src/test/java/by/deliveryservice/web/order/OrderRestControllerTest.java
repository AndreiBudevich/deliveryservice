package by.deliveryservice.web.order;

import by.deliveryservice.AbstractControllerTest;
import by.deliveryservice.model.Order;
import by.deliveryservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static by.deliveryservice.testdata.ClientTestData.CLIENT_ID_2;
import static by.deliveryservice.testdata.OrderTestData.*;
import static by.deliveryservice.util.JsonUtil.writeValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = OrderRestController.REST_URL + "/";

    @Autowired
    OrderRepository orderRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CLIENT_ID_2 + "/orders/" + ORDER_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ORDER_MATCHER.contentJson(order1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CLIENT_ID_2 + "/orders/" + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ORDER_MATCHER.contentJson(order4, order3, order2, order1));
    }

    @Test
    void update() throws Exception {
        Order updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + CLIENT_ID_2 + "/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        ORDER_MATCHER.assertMatch(orderRepository.get(ORDER_ID_1).orElse(null), getUpdated());
    }

    @Test
    void createWithLocation() throws Exception {
        Order newOrder = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + CLIENT_ID_2 + "/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newOrder)))
                .andExpect(status().isCreated());
        Order created = ORDER_MATCHER.readFromJson(action);
        int newId = created.id();
        newOrder.setId(newId);
        ORDER_MATCHER.assertMatch(created, newOrder);
        ORDER_MATCHER.assertMatch(orderRepository.get(newId).orElse(null), newOrder);
    }

    @Test
    void createInvalid() throws Exception {
        Order invalid = new Order(null, "");
        perform(MockMvcRequestBuilders.post(REST_URL + CLIENT_ID_2 + "/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void updateInvalid() throws Exception {
        Order invalid = new Order(ORDER_ID_1, null, "");
        perform(MockMvcRequestBuilders.put(REST_URL + CLIENT_ID_2 + "/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}

/*
одробности: Ошибочная строка содержит (5, г.Лида, ул. Радунская д.19, null, f, 0, 1)
        (3, '2022-10-01', 1000, 'г.Лида, ул. Машерова 27', true);*/
