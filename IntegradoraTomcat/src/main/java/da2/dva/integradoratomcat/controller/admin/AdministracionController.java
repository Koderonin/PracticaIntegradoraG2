package da2.dva.integradoratomcat.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ¡Esta clase es de administración, no se puede acceder con UsuarioCliente!
 * Es el otro controlador No-REST de la administración
 * Aquí tenemos los endpoints de las vistas de administración de las entidades de la base de datos SQL
 */
@Controller
@RequestMapping("/admin")
public class AdministracionController {

    @GetMapping("/area-admin")
    public ModelAndView devuelveAreaAdmin() {
        ModelAndView mv = new ModelAndView("/admin/area-admin");
        return mv;
    }
}
