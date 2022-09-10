package by.deliveryservice.web.storage;

import by.deliveryservice.AbstractControllerTest;
import by.deliveryservice.model.Storage;
import by.deliveryservice.repository.StorageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static by.deliveryservice.testdata.StorageTestData.*;
import static by.deliveryservice.util.json.JsonUtil.writeValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StorageRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = StorageRestController.REST_URL.replace("{shopId}", "1") + '/';

    @Autowired
    StorageRepository storageRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + STORAGE_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(STORAGE_MATCHER.contentJson(storage1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getAllWithProduct() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(STORAGE_MATCHER.contentJson(storage1, storage3));
    }

    @Test
    void update() throws Exception {
        Storage updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + STORAGE_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        STORAGE_MATCHER.assertMatch(storageRepository.get(STORAGE_ID_1).orElse(null), getUpdated());
    }
}

