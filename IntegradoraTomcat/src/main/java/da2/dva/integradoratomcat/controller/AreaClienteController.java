package da2.dva.integradoratomcat.controller;

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
    public ModelAndView areaCliente() {

        return mv;
    }
}
