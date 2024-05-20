package da2.dva.integradoratomcat.controller.admin.REST;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repositories.jpa.ClienteRepository;
import da2.dva.integradoratomcat.services.ServicioCliente;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost", "http://apache.da2.dva", "http://tomcat.da2.dva", "http://localhost:8080", "http://localhost:8081"}
        , allowCredentials = "true")
@RequestMapping("admin/api/cliente")
public class AdminClienteControllerREST {

    @Autowired
    private ServicioCliente servicioCliente;

    // Read
    @GetMapping("listado_ampliado")
    public Object listadoClientes() {
        return servicioCliente.listarClientes();
    }

    @GetMapping({"/{id}"})
    public Cliente getCliente(@PathVariable("id") String id) {
        return servicioCliente.getClienteById(id);
    }

    @GetMapping("listado")
    public Object listadoClientesFiltrado() {
        List<Cliente> listado = servicioCliente.listarClientes();
        List<Object> listadoReducido = new ArrayList<>();
    // gestiono qué campos quiero quedarme
        for (int i = 0; i < listado.size(); i++) {
            listadoReducido.add(
                reducirListado(listado.get(i))
            );
        }
        return listadoReducido;
    }

    // El siguiente método es una modificación del anterior, en el que recibiremos un array de atributos como parámetro
    // y devolveremos un listado, al igual que el anterior, pero sólo con los atributos pedidos

    /*@GetMapping("listado_cribado")
    public Object listadoClientesFiltrado(String[] atributos) {
        List<Cliente> listado = servicioCliente.listarClientes();
        List<Object> listadoReducido = new ArrayList<>();
    // gestiono qué campos quiero quedarme
        for (int i = 0; i < listado.size(); i++) {
            listadoReducido.add(
                toDocument(listado.get(i))
            );
        }
        return listadoReducido;
    }*/

    @GetMapping("/verify")
    public List<Object> verify(HttpSession sesion) {
        List<Object> listado = new ArrayList<>();
        listado.add(sesion.getAttribute("usuario"));
        return listado;
    }

    // métodos auxiliares
    private Document reducirListado(Cliente cliente) {
        return new Document("id_cliente", cliente.getId_cliente())
                .append("email", cliente.getUsuarioCliente().getEmail())
                .append("nombre", cliente.getNombre())
                .append("apellidos", cliente.getApellidos())
                .append("fecha_alta", cliente.getFechaNacimiento())
                .append("fecha_baja", cliente.getFechaBajaEntidad() != null ? cliente.getFechaBajaEntidad() : "ALTA")
                .append("num_accesos", cliente.getUsuarioCliente().getNumAccesos())
                .append("bloqueado", cliente.getUsuarioCliente().getFechaBloqueo() != null ? cliente.getUsuarioCliente().getFechaBloqueo() : "Todo en orden");
    }
}
