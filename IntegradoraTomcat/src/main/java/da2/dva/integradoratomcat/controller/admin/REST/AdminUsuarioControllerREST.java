package da2.dva.integradoratomcat.controller.admin.REST;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost", "http://apache.da2.dva", "http://tomcat.da2.dva", "http://localhost:8080", "http://localhost:8081"}
        , allowCredentials = "true")
@RequestMapping("admin/api/usuario")
public class AdminUsuarioControllerREST {

    @Autowired
    private ServicioUsuario servicioUsuario;

    @GetMapping("listado")
    public Object listadoUsuarios() {
        return servicioUsuario.listarUsuarios();
    }

    @GetMapping({"/{id}"})
    public Usuario getCliente(@PathVariable("id") String id) {
        return servicioUsuario.getUsuario(id);
    }

}
