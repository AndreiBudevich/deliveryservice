package by.deliveryservice.web.order.details;

import by.deliveryservice.AbstractControllerTest;
import by.deliveryservice.model.OrderDetail;
import by.deliveryservice.repository.OrderDetailRepository;
import by.deliveryservice.testdata.OrderDetailTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static by.deliveryservice.testdata.ClientTestData.CLIENT_ID_1;
import static by.deliveryservice.testdata.ClientTestData.CLIENT_ID_2;
import static by.deliveryservice.testdata.OrderDetailTestData.*;
import static by.deliveryservice.testdata.OrderTestData.*;
import static by.deliveryservice.testdata.ProductTestData.*;
import static by.deliveryservice.util.JsonUtil.writeValue;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderDetailControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/rest/clients/";

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CLIENT_ID_1 + "/orders/1/details/" + ORDER_DETAIL_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ORDER_DETAIL_MATCHER.contentJson(orderDetail1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CLIENT_ID_1 + "/orders/" + ORDER_ID_1 + "/details/" + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + CLIENT_ID_1 + "/orders/" + ORDER_ID_1 + "/details/" + ORDER_DETAIL_ID_1))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(orderDetailRepository.get(ORDER_DETAIL_ID_1).isPresent());
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + CLIENT_ID_1 + "/orders/" + ORDER_ID_1 + "/details/" + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deleteShipped() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + CLIENT_ID_2 + "/orders/" + ORDER_ID_2 + "/details/" + ORDER_DETAIL_ID_3))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Заказ отгружен, изменения запрещены!")));
        assertTrue(orderDetailRepository.get(ORDER_DETAIL_ID_1).isPresent());
    }

    @Test
    void getAllByOrderIdWithProduct() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CLIENT_ID_2 + "/orders/" + ORDER_ID_2 + "/details"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ORDER_DETAIL_MATCHER.contentJson(orderDetail3, orderDetail4, orderDetail5));
    }

    @Test
    void update() throws Exception {
        OrderDetail updated = OrderDetailTestData.getUpdated();
        perform(MockMvcRequestBuilders.post(REST_URL + CLIENT_ID_1 + "/orders/" + ORDER_ID_1 + "/details/" + PRODUCT_ID_2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        ORDER_DETAIL_MATCHER.assertMatch(orderDetailRepository.get(ORDER_DETAIL_ID_1).orElse(null), OrderDetailTestData.getUpdated());
    }

    @Test
    void updateInvalid() throws Exception {
        OrderDetail invalid = new OrderDetail(ORDER_DETAIL_ID_1, order1, product2, 3500L, 0, 7000L);
        perform(MockMvcRequestBuilders.post(REST_URL + CLIENT_ID_1 + "/orders/" + ORDER_ID_1 + "/details/" + PRODUCT_ID_2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void updateShipped() throws Exception {
        OrderDetail updated = OrderDetailTestData.getUpdated();
        perform(MockMvcRequestBuilders.post(REST_URL + CLIENT_ID_2 + "/orders/" + ORDER_ID_2 + "/details/" + PRODUCT_ID_3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Заказ отгружен, изменения запрещены!")));
    }

    @Test
    void addProduct() throws Exception {
        assertFalse(orderDetailRepository.getByOrderIdByProductId(ORDER_ID_1, PRODUCT_ID_3).isPresent());
        perform(MockMvcRequestBuilders.post(REST_URL + CLIENT_ID_1 + "/orders/" + ORDER_ID_1 + "/add-product/" + PRODUCT_ID_3))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertTrue(orderDetailRepository.getByOrderIdByProductId(ORDER_ID_1, PRODUCT_ID_3).isPresent());
    }

    @Test
    void deleteProduct() throws Exception {
        assertTrue(orderDetailRepository.getByOrderIdByProductId(ORDER_ID_1, PRODUCT_ID_7).isPresent());
        perform(MockMvcRequestBuilders.post(REST_URL + CLIENT_ID_1 + "/orders/" + ORDER_ID_1 + "/delete-product/" + PRODUCT_ID_7))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(orderDetailRepository.getByOrderIdByProductId(ORDER_ID_1, PRODUCT_ID_7).isPresent());
    }

    @Test
    void lowerQuantityProduct() throws Exception {
        assertEquals(2, (int) Objects.requireNonNull(orderDetailRepository.getByOrderIdByProductId(ORDER_ID_1, PRODUCT_ID_2).orElse(null)).getQuantity());
        perform(MockMvcRequestBuilders.post(REST_URL + CLIENT_ID_1 + "/orders/" + ORDER_ID_1 + "/delete-product/" + PRODUCT_ID_2))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertEquals(1, (int) Objects.requireNonNull(orderDetailRepository.getByOrderIdByProductId(ORDER_ID_1, PRODUCT_ID_2).orElse(null)).getQuantity());
    }

    @Test
    void addProductShipped() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + CLIENT_ID_2 + "/orders/" + ORDER_ID_2 + "/add-product/" + PRODUCT_ID_3))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Заказ отгружен, изменения запрещены!")));
    }

    @Test
    void deleteProductShipped() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + CLIENT_ID_2 + "/orders/" + ORDER_ID_2 + "/delete-product/" + PRODUCT_ID_3))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Заказ отгружен, изменения запрещены!")));
        assertTrue(orderDetailRepository.getByOrderIdByProductId(ORDER_ID_1, PRODUCT_ID_7).isPresent());
    }
}

