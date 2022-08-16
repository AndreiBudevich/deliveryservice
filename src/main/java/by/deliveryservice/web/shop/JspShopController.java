package by.deliveryservice.web.shop;

import by.deliveryservice.model.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping("/shops")
public class JspShopController extends AbstractShopController {

    @GetMapping()
    public String getShops(Model model) {
        model.addAttribute("shops", super.getAll());
        return "shops";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/shops";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("shop", super.get(getId(request)));
        return "shopForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("shop", new Shop("", "", "", ""));
        return "shopForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Shop shop = getFromRequest(request);
        if (request.getParameter("id").isEmpty()) {
            super.create(shop);
        } else {
            super.update(shop, getId(request));
        }
        return "redirect:/shops";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    private Shop getFromRequest(HttpServletRequest request) {
        return new Shop(request.getParameter("name"),
                request.getParameter("description"),
                request.getParameter("address"),
                request.getParameter("contact"));
    }
}
