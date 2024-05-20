package da2.dva.integradoratomcat.controller.REST;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.services.ServicioCliente;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost", "http://apache.da2.dva", "http://tomcat.da2.dva", "http://localhost:8080"}, allowCredentials = "true")
@RequestMapping("/api/sesion")
public class SesionControllerREST {
    @Autowired
    private ServicioCliente servicioCliente;
    @Autowired
    private ServicioUsuario servicioUsuario;

    @GetMapping("/cliente")
    public Cliente detalleClienteByUsuario(HttpSession sesion) {
        return servicioCliente.getClienteByUsuario((UsuarioCliente) sesion.getAttribute("usuario"));
    }

    @GetMapping("/usuarioEsAdmin")
    public boolean usuarioEnSesionEsAdmin(HttpSession sesion) {
        return servicioUsuario.devuelveUsuarios().get(((Usuario) sesion.getAttribute("usuario")).getEmail()).getAdministrador();
    }

    @GetMapping("/paginas-visitadas")
    public int paginasEnSesion(HttpSession sesion) {
        return sesion.getAttribute("paginas_visitadas") != null ? (int) sesion.getAttribute("paginas_visitadas") : 0;
    }

    @GetMapping("/agregar-visita")
    public void agregarVisita(HttpSession sesion) {
        if (sesion.getAttribute("paginas_visitadas") == null) {
            sesion.setAttribute("paginas_visitadas", 1);
        } else {
            sesion.setAttribute("paginas_visitadas", (int) sesion.getAttribute("paginas_visitadas") + 1);
        }
    }
}
