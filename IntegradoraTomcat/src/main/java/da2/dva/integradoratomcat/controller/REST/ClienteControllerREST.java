package da2.dva.integradoratomcat.controller.REST;

import com.fasterxml.jackson.databind.ObjectMapper;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repositories.jpa.ClienteRepository;
import da2.dva.integradoratomcat.services.ServicioCliente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost", "http://apache.da2.dva", "http://tomcat.da2.dva", "http://localhost:8080"}, allowCredentials = "true")
@RequestMapping("/api/cliente")
public class ClienteControllerREST {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    private ServicioCliente servicioCliente;

    // Read
    @GetMapping("listado")
    public List<Cliente> listadoClientes() {
        return servicioCliente.listarClientes();
    }

    /*@GetMapping("/infoSesion")
    public List<Cliente> detalleClienteByUsuario(HttpSession sesion) {
        List<Cliente> listado = new ArrayList<>();
        listado.add(servicioCliente.getClienteByUsuario((UsuarioCliente) sesion.getAttribute("usuario")));
        return listado;
    }*/
    @GetMapping("/infoSesion")
    public Cliente detalleClienteByUsuario(HttpSession sesion) {
        return servicioCliente.getClienteByUsuario((UsuarioCliente) sesion.getAttribute("usuario"));
    }

    @GetMapping("/sesion")
    public int getVisitasSesion(HttpSession sesion){
        if( sesion.getAttribute("paginas_visitadas") != null){
            return (int) sesion.getAttribute("paginas_visitadas") ;
        }else return 0;

    }
    @GetMapping("/sesion/agregarVisita")
    public void agregarVisitaSesion(HttpSession sesion){
        if( sesion.getAttribute("paginas_visitadas") == null){
            sesion.setAttribute("paginas_visitadas",0);
        }
        sesion.setAttribute("paginas_visitadas" ,(int) sesion.getAttribute("paginas_visitadas") +1 );
    }
}
