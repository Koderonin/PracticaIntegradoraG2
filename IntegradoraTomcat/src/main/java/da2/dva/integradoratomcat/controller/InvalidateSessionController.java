package da2.dva.integradoratomcat.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("logout")
public class InvalidateSessionController {

    @GetMapping("invalidate")
    public String logout(HttpSession sesion) {
        sesion.invalidate();
        return "redirect:/login/paso1";
    }

    @GetMapping("remove")
    public String logout2(HttpSession sesion) {
        sesion.removeAttribute("email");
        sesion.removeAttribute("usuario");
        return "redirect:/login/paso1";
    }
}
