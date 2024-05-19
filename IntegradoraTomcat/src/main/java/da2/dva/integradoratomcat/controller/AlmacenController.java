package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.repositories.jpa.PedidoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ¡Esta clase es de administración, no se puede acceder con UsuarioCliente!
 * Es el controlador No-REST de la administración
 * Aquí tenemos los endpoints de las vistas de administración de productos de la base de datos MongoDB
 */

@Controller
@RequestMapping("/admin/almacen")
public class AlmacenController {

    ModelAndView mv = new ModelAndView("/add-producto");



    @GetMapping("/add-producto")
    public ModelAndView addProducto(HttpSession sesion) {

        mv.setViewName("add-producto");

        return mv;
    }
}
