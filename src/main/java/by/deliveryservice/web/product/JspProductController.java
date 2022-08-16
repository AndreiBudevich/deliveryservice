package by.deliveryservice.web.product;

import by.deliveryservice.model.Product;
import by.deliveryservice.model.Shop;
import by.deliveryservice.repository.datajpa.DataJpaShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping("/products")
public class JspProductController extends AbstractProductController {

    @Autowired
    DataJpaShopRepository shopRepository;

    @GetMapping()
    public String getProducts(Model model) {
        model.addAttribute("products", super.getAll());
        return "products";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request, Model model) {
        super.delete(getId(request));
        return getProductsShop(request, model);
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("product", super.get(getId(request)));
        return "productForm";
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        model.addAttribute("product", new Product("", "", null, 0L, 0));
        return "productForm";
    }

    @GetMapping("/view-products-by-shop")
    public String getAllProductsByShopId(HttpServletRequest request, Model model) {
        return getProductsShop(request, model);
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request, Model model) {
        Product product = getFromRequest(request);
        if (request.getParameter("id").isEmpty()) {
            super.create(product);
        } else {
            super.update(product, getId(request));
        }
        return getProductsShop(request, model);
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    private Product getFromRequest(HttpServletRequest request) {
        Shop shop = shopRepository.getReferenceById(getIntegerParameter(request, "shopId"));
        return new Product(request.getParameter("name"),
                request.getParameter("description"), shop,
                Long.parseLong(request.getParameter("price")),
                getIntegerParameter(request, "discount"));
    }

    private Integer getIntegerParameter(HttpServletRequest request, String parameter) {
        return Integer.parseInt(request.getParameter(parameter));
    }

    private String getProductsShop(HttpServletRequest request, Model model) {
        model.addAttribute("products", super.getAllProductsByShopId(getIntegerParameter(request, "shopId")));
        return "productsShop";
    }
}
