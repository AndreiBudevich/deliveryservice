package by.deliveryservice.web.client;

import by.deliveryservice.model.Client;
import by.deliveryservice.util.DateTimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static by.deliveryservice.util.DateTimeUtil.getToday;

@Controller
@RequestMapping("/clients")
public class JspClientController extends AbstractClientController {

    @GetMapping()
    public String getClients(Model model) {
        model.addAttribute("clients", super.getAll());
        return "clients";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/clients";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("client", super.get(getId(request)));
        return "clientForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("client", new Client("", "", "", "", getToday()));
        return "clientForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Client client = getFromRequest(request);
        if (request.getParameter("id").isEmpty()) {
            super.create(client);
        } else {
            super.update(client, getId(request));
        }
        return "redirect:/clients";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    private Client getFromRequest(HttpServletRequest request) {
        return new Client(request.getParameter("name"),
                request.getParameter("surname"),
                request.getParameter("middleName"),
                request.getParameter("residentialAddress"),
                DateTimeUtil.getBirthday(request.getParameter("birthday")));
    }
}

