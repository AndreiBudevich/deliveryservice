package by.deliveryservice.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class RootController {

    @GetMapping("/")
    public String root() {
        log.info("root");
        return "index";
    }

    @GetMapping("/products")
    public String products() {
        log.info("products");
        return "products";
    }

    @GetMapping("/categories")
    public String categories() {
        log.info("categories");
        return "categories";
    }

    @GetMapping("/shops")
    public String shops() {
        log.info("shops");
        return "shops";
    }
}
