package da2.dva.integradoratomcat.controller.REST;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.services.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cliente")
public class ClienteControllerREST {
    @Autowired
    private ServicioCliente servicioCliente;

    @GetMapping("listado")
    public List<Cliente> listadoClientes() {
        return servicioCliente.listarClientes();
    }
}
