package by.deliveryservice.web.product;

import by.deliveryservice.AbstractControllerTest;
import by.deliveryservice.model.Product;
import by.deliveryservice.repository.ProductRepository;
import by.deliveryservice.repository.StorageRepository;
import by.deliveryservice.testdata.ProductTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static by.deliveryservice.testdata.CategoryTestData.*;
import static by.deliveryservice.testdata.ProductTestData.*;
import static by.deliveryservice.testdata.ShopTestData.*;
import static by.deliveryservice.util.JsonUtil.writeValue;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProductRestController.REST_URL;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StorageRepository storageRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + SHOP_ID_1 + "/products/" + PRODUCT_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(PRODUCT_MATCHER.contentJson(product1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + SHOP_ID_1 + "/products/" + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(PRODUCT_MATCHER.contentJson(product6, product7, product5, product1, product2, product4, product3));
    }

    @Test
    void update() throws Exception {
        Product updated = ProductTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + "/" + SHOP_ID_1 + "/products/" + PRODUCT_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        PRODUCT_MATCHER.assertMatch(productRepository.get(PRODUCT_ID_1).orElse(null), ProductTestData.getUpdated());
    }

    @Test
    void createWithLocation() throws Exception {
        Product newProduct = ProductTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "/" + SHOP_ID_1 + "/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newProduct)))
                .andExpect(status().isCreated());
        Product created = PRODUCT_MATCHER.readFromJson(action);
        int newId = created.id();
        newProduct.setId(newId);
        PRODUCT_MATCHER.assertMatch(created, newProduct);
        PRODUCT_MATCHER.assertMatch(productRepository.get(newId).orElse(null), newProduct);
        assertTrue(storageRepository.getByProductId(newId).isPresent());
    }

    @Test
    void createInvalid() throws Exception {
        Product invalid = new Product("Название", "описание", null, 51000L, 101);
        perform(MockMvcRequestBuilders.post(REST_URL + "/" + SHOP_ID_1 + "/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void updateInvalid() throws Exception {
        Product invalid = new Product(PRODUCT_ID_1, "Название", "", null, 51000L, 101);
        perform(MockMvcRequestBuilders.put(REST_URL + "/" + SHOP_ID_1 + "/products/" + PRODUCT_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS)
    void updateDuplicate() throws Exception {
        Product updated = new Product(PRODUCT_ID_4, "Микроволновка", "без насадок", null, 500L, 10);
        perform(MockMvcRequestBuilders.put(REST_URL + "/" + SHOP_ID_3 + "/products/" + PRODUCT_ID_4)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Товар с таким именем зарегистрирован")));
    }

    @Test
    void createDuplicate() throws Exception {
        Product expected = new Product("Телевизор LG", "Smart", null, 3000L, 0);
        perform(MockMvcRequestBuilders.post(REST_URL + "/" + SHOP_ID_1 + "/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(expected)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("Товар с таким именем зарегистрирован")));
    }


    @Test
    void admissibleCreateDuplicate() throws Exception {
        Product expected = new Product("Телевизор LG", "Smart", null, 3000L, 0);
        perform(MockMvcRequestBuilders.post(REST_URL + "/" + SHOP_ID_2 + "/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(expected)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void admissibleUpdateDuplicate() throws Exception {
        Product expected = new Product("Телевизор LG", "Smart", null, 3000L, 0);
        perform(MockMvcRequestBuilders.put(REST_URL + "/" + SHOP_ID_2 + "/products/" + PRODUCT_ID_2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(expected)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void updateHtmlUnsafe() throws Exception {
        Product invalid = new Product("<script>alert(123)</script>", "<script>alert(123)</script>",
                null, 3000L, 0);
        perform(MockMvcRequestBuilders.put(REST_URL + "/" + SHOP_ID_1 + "/products/" + PRODUCT_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getAllWithFilter() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/products/filter")
                .param("nameContains", "Фен").param("descriptionContains", "с ")
                .param("shopNameContains", "").param("categories", "2_3")
                .param("priceFrom", "0").param("priceUpTo", "300")
                .param("discountFrom", "0").param("discountUpTo", "100"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(PRODUCT_MATCHER.contentJson(product4, product3));
    }

    @Test
    void addCategory() throws Exception {
        Product withCategories = productRepository.getWithCategories(PRODUCT_ID_1).orElse(null);
        assert withCategories != null;
        CATEGORY_MATCHER.assertMatch(withCategories.getCategories(), category2);
        perform(MockMvcRequestBuilders.post(REST_URL + "/products/" + PRODUCT_ID_1 + "/add-category/" + CATEGORY_ID_3)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
        withCategories = productRepository.getWithCategories(PRODUCT_ID_1).orElse(null);
        assert withCategories != null;
        CATEGORY_MATCHER.assertMatch(withCategories.getCategories(), category2, category3);
    }

    @Test
    void deleteCategory() throws Exception {
        Product withCategories = productRepository.getWithCategories(PRODUCT_ID_3).orElse(null);
        assert withCategories != null;
        CATEGORY_MATCHER.assertMatch(withCategories.getCategories(), category2, category3);
        perform(MockMvcRequestBuilders.post(REST_URL + "/products/" + PRODUCT_ID_3 + "/delete-category/" + CATEGORY_ID_3)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
        withCategories = productRepository.getWithCategories(PRODUCT_ID_1).orElse(null);
        assert withCategories != null;
        CATEGORY_MATCHER.assertMatch(withCategories.getCategories(), category2);
    }
}