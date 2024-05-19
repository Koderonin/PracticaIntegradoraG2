package da2.dva.integradoratomcat.controller.cliente;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class AreaClienteController {
    ModelAndView mv = new ModelAndView();

    @GetMapping("/area-cliente")
    public ModelAndView areaCliente(HttpSession sesion) {
        mv.setViewName("redirect:http://apache.da2.dva/cliente/area-cliente.html");

        if (sesion.getAttribute("usuario") == null) {
            sesion.setAttribute("errorLogin", "Debes iniciar sesión");
            mv.setViewName("redirect:/login/paso1");
        }

        return mv;
    }
}
