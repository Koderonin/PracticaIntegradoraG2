package da2.dva.integradoratomcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ¡Esta clase es de administración, no se puede acceder con UsuarioCliente!
 * Es el otro controlador No-REST de la administración
 * Aquí tenemos los endpoints de las vistas de administración de las entidades de la base de datos SQL
 */
@Controller
@RequestMapping("/admin")
public class AdministracionController {

    @GetMapping("/")
    public String index() {
        return "redirect:/admin/listado";
    }

    @GetMapping("/listado")
    public String listado() {
        return "listado";
    }
}
