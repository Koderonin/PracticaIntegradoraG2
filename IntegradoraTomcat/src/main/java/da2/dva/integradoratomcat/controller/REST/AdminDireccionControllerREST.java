package da2.dva.integradoratomcat.controller.REST;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.services.ServicioDireccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost", "http://apache.da2.dva", "http://tomcat.da2.dva", "http://localhost:8080", "http://localhost:8081"}
        , allowCredentials = "true")
@RequestMapping("admin/api")
public class AdminDireccionControllerREST {

    @Autowired
    private ServicioDireccion servicioDireccion;

    // lo siguiente recibe un map con los atributos de la Dirección y sus valores
    @PostMapping(value = "direccion/update", consumes = "application/json")
    public void actualizarDireccion(@RequestBody Direccion direccion) {
        servicioDireccion.save(direccion);
    }


}
