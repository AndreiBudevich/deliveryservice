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

    @GetMapping("/clients")
    public String clients() {
        log.info("clients");
        return "clients";
    }

    @GetMapping("/orders")
    public String orders() {
        log.info("orders");
        return "orders";
    }

    @GetMapping("/client_orders")
    public String clientOrders() {
        log.info("client orders");
        return "clientOrders";
    }

    @GetMapping("/order_details")
    public String orderDetails() {
        log.info("order details");
        return "orderDetails";
    }

    @GetMapping("/order_products")
    public String orderProducts() {
        log.info("order orderProducts");
        return "orderProducts";
    }

    @GetMapping("/storage")
    public String storage() {
        log.info("storage");
        return "storage";
    }
}
