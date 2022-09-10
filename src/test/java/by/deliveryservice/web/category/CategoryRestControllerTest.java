package by.deliveryservice.web.category;

import by.deliveryservice.AbstractControllerTest;
import by.deliveryservice.model.Category;
import by.deliveryservice.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static by.deliveryservice.testdata.CategoryTestData.*;
import static by.deliveryservice.util.json.JsonUtil.writeValue;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = CategoryRestController.REST_URL + '/';

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CATEGORY_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CATEGORY_MATCHER.contentJson(category1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + CATEGORY_ID_1))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(categoryRepository.get(CATEGORY_ID_1).isPresent());
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
                .andExpect(CATEGORY_MATCHER.contentJson(category1, category2, category3));
    }

    @Test
    void update() throws Exception {
        Category updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + CATEGORY_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        CATEGORY_MATCHER.assertMatch(categoryRepository.get(CATEGORY_ID_1).orElse(null), getUpdated());
    }

    @Test
    void createWithLocation() throws Exception {
        Category newCategory = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newCategory)))
                .andExpect(status().isCreated());
        Category created = CATEGORY_MATCHER.readFromJson(action);
        int newId = created.id();
        newCategory.setId(newId);
        CATEGORY_MATCHER.assertMatch(created, newCategory);
        CATEGORY_MATCHER.assertMatch(categoryRepository.get(newId).orElse(null), newCategory);
    }

    @Test
    void createInvalid() throws Exception {
        Category invalid = new Category(null, "");
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void updateInvalid() throws Exception {
        Category invalid = new Category(CATEGORY_ID_1, null);
        perform(MockMvcRequestBuilders.put(REST_URL + CATEGORY_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS)
    void updateDuplicate() throws Exception {
        Category updated = new Category(CATEGORY_ID_1, "Бытовая техника");
        perform(MockMvcRequestBuilders.put(REST_URL + CATEGORY_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Категория с таким названием уже существует")));
    }

    @Test
    void createDuplicate() throws Exception {
        Category expected = new Category("Бытовая техника");
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(expected)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Категория с таким названием уже существует")));
    }

    @Test
    void updateHtmlUnsafe() throws Exception {
        Category invalid = new Category(CATEGORY_ID_1, "<script>alert(123)</script>");
        perform(MockMvcRequestBuilders.put(REST_URL + CATEGORY_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}

