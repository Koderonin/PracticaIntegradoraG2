package da2.dva.integradoratomcat.controller.REST;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Nomina;
import da2.dva.integradoratomcat.services.ServicioCliente;
import da2.dva.integradoratomcat.services.ServicioNomina;
import da2.dva.integradoratomcat.services.ServicioLineaNomina;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost", "http://apache.da2.dva", "http://tomcat.da2.dva", "http://localhost:8080", "http://localhost:8081"}
        , allowCredentials = "true")
@RequestMapping("admin/api/nomina")
public class AdminNominasControllerRest {

    @Autowired
    private ServicioNomina servicioNomina;

    @Autowired
    private ServicioLineaNomina servicioLineaNomina;

    @Autowired
    private ServicioCliente servicioCliente;

    @Autowired
    private AdminClienteControllerREST adminClienteControllerREST;

    // Read

    /**
     * Devuelve el listado de Clientes para elegir uno de ellos.
     * @return El listado de Clientes
     */
    @GetMapping("listado")
    public Object listadoClientes(){
        return adminClienteControllerREST.listadoClientesFiltrado();
    }

    @GetMapping("listado_completo")
    public Object listadoNominas(){
        return servicioNomina.findAll();
    }

    @GetMapping({"/{id}"})
    public List<Nomina> getNominasByCliente(@PathVariable("id") String id) {
        Cliente cliente = servicioCliente.getClienteById(id);
        return servicioNomina.findByCliente(cliente);
    }

}
