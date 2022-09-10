package by.deliveryservice.web.shop;

import by.deliveryservice.AbstractControllerTest;
import by.deliveryservice.model.Shop;
import by.deliveryservice.repository.ShopRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static by.deliveryservice.testdata.ShopTestData.*;
import static by.deliveryservice.util.json.JsonUtil.writeValue;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ShopRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ShopRestController.REST_URL + '/';

    @Autowired
    ShopRepository shopRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + SHOP_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(SHOP_MATCHER.contentJson(shop1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(SHOP_MATCHER.contentJson(shop1, shop2, shop3));
    }

    @Test
    void update() throws Exception {
        Shop updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + SHOP_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        SHOP_MATCHER.assertMatch(shopRepository.get(SHOP_ID_1).orElse(null), getUpdated());
    }

    @Test
    void createWithLocation() throws Exception {
        Shop newShop = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newShop)))
                .andExpect(status().isCreated());
        Shop created = SHOP_MATCHER.readFromJson(action);
        int newId = created.id();
        newShop.setId(newId);
        SHOP_MATCHER.assertMatch(created, newShop);
        SHOP_MATCHER.assertMatch(shopRepository.get(newId).orElse(null), newShop);
    }

    @Test
    void createInvalid() throws Exception {
        Shop invalid = new Shop(null, "", "", "", null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void updateInvalid() throws Exception {
        Shop invalid = new Shop(SHOP_ID_1, null, "", "", "");
        perform(MockMvcRequestBuilders.put(REST_URL + SHOP_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS)
    void updateDuplicate() throws Exception {
        Shop updated = new Shop(SHOP_ID_1, "Евроопт", " г.Лида, ул. Советская 1", "Продовольственный магазин", "80297634349");
        perform(MockMvcRequestBuilders.put(REST_URL + SHOP_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Магазин с таким именем зарегистрирован")));
    }

    @Test
    void createDuplicate() throws Exception {
        Shop expected = new Shop(null, "Auchan", "г.Лида, ул. Машерова 63",
                "Продовольственный магазин", "80447324144");
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(expected)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Магазин с таким именем зарегистрирован")));
    }

    @Test
    void updateHtmlUnsafe() throws Exception {
        Shop invalid = new Shop(SHOP_ID_1, "<script>alert(123)</script>", "г.Лида, ул. Машерова 63",
                "Продовольственный магазин", "80447324144");
        perform(MockMvcRequestBuilders.put(REST_URL + SHOP_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}

