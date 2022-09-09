package by.deliveryservice.web.order;

import by.deliveryservice.AbstractControllerTest;
import by.deliveryservice.model.Order;
import by.deliveryservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static by.deliveryservice.testdata.ClientTestData.CLIENT_ID_1;
import static by.deliveryservice.testdata.ClientTestData.CLIENT_ID_2;
import static by.deliveryservice.testdata.OrderTestData.*;
import static by.deliveryservice.util.JsonUtil.writeValue;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = OrderRestController.REST_URL + "/";

    @Autowired
    OrderRepository orderRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CLIENT_ID_1 + "/orders/" + ORDER_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ORDER_MATCHER.contentJson(order1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CLIENT_ID_1 + "/orders/" + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + CLIENT_ID_1 + "/orders/" + ORDER_ID_1))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(orderRepository.get(ORDER_ID_1).isPresent());
    }

    @Test
    void ship() throws Exception {
        assertFalse(Objects.requireNonNull(orderRepository.get(ORDER_ID_1).orElse(null)).isShipped());
        perform(MockMvcRequestBuilders.post(REST_URL + CLIENT_ID_1 + "/orders/" + ORDER_ID_1))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertTrue(Objects.requireNonNull(orderRepository.get(ORDER_ID_1).orElse(null)).isShipped());
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + CLIENT_ID_1 + "/orders/" + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deleteShipped() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + CLIENT_ID_2 + "/orders/" + ORDER_ID_2))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Заказ отгружен, изменения запрещены!")));
        assertTrue(orderRepository.get(ORDER_ID_1).isPresent());
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ORDER_MATCHER.contentJson(order4, order3, order2, order1));
    }

    @Test
    void getAllByClientId() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CLIENT_ID_2 + "/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ORDER_MATCHER.contentJson(order3, order2));
    }

    @Test
    void update() throws Exception {
        Order updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + CLIENT_ID_1 + "/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        ORDER_MATCHER.assertMatch(orderRepository.get(ORDER_ID_1).orElse(null), getUpdated());
    }

    @Test
    void createWithLocation() throws Exception {
        Order newOrder = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + CLIENT_ID_1 + "/orders")
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
        perform(MockMvcRequestBuilders.post(REST_URL + CLIENT_ID_1 + "/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void updateInvalid() throws Exception {
        Order invalid = new Order(ORDER_ID_1, null, "");
        perform(MockMvcRequestBuilders.put(REST_URL + CLIENT_ID_1 + "/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
