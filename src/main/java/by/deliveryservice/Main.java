package by.deliveryservice;

import by.deliveryservice.controller.ControllerFileRepository;
import by.deliveryservice.util.json.JsonUtil;
import by.deliveryservice.util.json.ProductSerialize;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Main {

    private static final ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    static {
        SimpleModule module = new SimpleModule();
        module.addSerializer(new ProductSerialize());
        mapper.registerModule(module);
    }

    public static void main(String[] args) {
        JsonUtil.setMapper(mapper);
        ControllerFileRepository.runApplication();
    }
}



