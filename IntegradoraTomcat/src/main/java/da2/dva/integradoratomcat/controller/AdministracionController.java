package da2.dva.integradoratomcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdministracionController {

    @GetMapping("/a")
    public String listado() {
        return "listado";
    }
}
