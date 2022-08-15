package by.deliveryservice.web.category;

import by.deliveryservice.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping("/categories")
public class JspCategoryController extends AbstractCategoryController {

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("categories", super.getAll());
        return "categories";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/categories";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("category", super.get(getId(request)));
        return "categoryForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category(""));
        return "categoryForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Category category = new Category(request.getParameter("name"));
        if (request.getParameter("id").isEmpty()) {
            super.create(category);
        } else {
            super.update(category, getId(request));
        }
        return "redirect:/categories";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
