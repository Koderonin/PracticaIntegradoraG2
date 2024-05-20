package da2.dva.integradoratomcat.controller.REST;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public Usuario getUsuarioCliente(@PathVariable("id") String id) {
        return servicioUsuario.getUsuario(id);
    }

    @PostMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<String> actualizarUsuarioCliente(@RequestBody UsuarioCliente usuarioActualizado) {
        System.out.println("Actualizando usuario: ");
        return ResponseEntity.ok().body(servicioUsuario.actualizarUsuario(usuarioActualizado));

    }

}
