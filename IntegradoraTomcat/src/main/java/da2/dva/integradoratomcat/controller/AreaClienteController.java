package da2.dva.integradoratomcat.controller;

import jakarta.servlet.http.HttpSession;
import da2.dva.integradoratomcat.repositories.jpa.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class AreaClienteController {
    ModelAndView mv = new ModelAndView("/area-cliente");

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping("area-cliente")
    public ModelAndView areaCliente(HttpSession sesion) {
        mv.setViewName("redirect:http://apache.da2.dva/area-cliente.html");
        if (sesion.getAttribute("usuario") == null) {
            sesion.setAttribute("errorLogin", "Debes iniciar sesión");
            mv.setViewName("redirect:/login/paso1");
        }
        return mv;
    }
}
