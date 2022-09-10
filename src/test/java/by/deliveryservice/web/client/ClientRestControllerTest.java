package by.deliveryservice.web.client;

import by.deliveryservice.AbstractControllerTest;
import by.deliveryservice.model.Client;
import by.deliveryservice.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static by.deliveryservice.testdata.ClientTestData.*;
import static by.deliveryservice.util.json.JsonUtil.writeValue;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ClientRestController.REST_URL + '/';

    @Autowired
    ClientRepository clientRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CLIENT_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CLIENT_MATCHER.contentJson(client1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + CLIENT_ID_1))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(clientRepository.get(CLIENT_ID_1).isPresent());
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CLIENT_MATCHER.contentJson(client1, client2, client3));
    }

    @Test
    void update() throws Exception {
        Client updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + CLIENT_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        CLIENT_MATCHER.assertMatch(clientRepository.get(CLIENT_ID_1).orElse(null), getUpdated());
    }

    @Test
    void createWithLocation() throws Exception {
        Client newClient = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newClient)))
                .andExpect(status().isCreated());
        Client created = CLIENT_MATCHER.readFromJson(action);
        int newId = created.id();
        newClient.setId(newId);
        CLIENT_MATCHER.assertMatch(created, newClient);
        CLIENT_MATCHER.assertMatch(clientRepository.get(newId).orElse(null), newClient);
    }

    @Test
    void createInvalid() throws Exception {
        Client invalid = new Client(null, "", "", "", null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void updateInvalid() throws Exception {
        Client invalid = new Client(CLIENT_ID_1, null, "", "", "", null);
        perform(MockMvcRequestBuilders.put(REST_URL + CLIENT_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS)
    void updateDuplicate() throws Exception {
        Client updated = new Client(CLIENT_ID_1, "Татьяна", "Будевич", "Александровна",
                "г.Лида, ул. Машерова 21", LocalDate.parse("1990-06-20"));
        perform(MockMvcRequestBuilders.put(REST_URL + CLIENT_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Данный клиент уже зарегистрирован")));
    }

    @Test
    void createDuplicate() throws Exception {
        Client expected = new Client(null, "Татьяна", "Будевич", "Александровна",
                "г.Лида, ул. Машерова 21", LocalDate.parse("1990-06-20"));
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(expected)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Данный клиент уже зарегистрирован")));
    }

    @Test
    void updateHtmlUnsafe() throws Exception {
        Client invalid = new Client(CLIENT_ID_1, "<script>alert(123)</script>", "Василючек", "Феликсович",
                "Нет адреса", LocalDate.parse("1990-01-01"));
        perform(MockMvcRequestBuilders.put(REST_URL + CLIENT_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}

