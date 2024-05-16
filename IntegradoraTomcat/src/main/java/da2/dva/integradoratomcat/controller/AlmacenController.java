package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.repositories.jpa.PedidoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/api/producto")
public class AlmacenController {

    ModelAndView mv = new ModelAndView("/add-producto");

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping("/add-producto")
    public ModelAndView addProducto(HttpSession sesion) {

        mv.setViewName("add-producto");

        return mv;
    }
}
