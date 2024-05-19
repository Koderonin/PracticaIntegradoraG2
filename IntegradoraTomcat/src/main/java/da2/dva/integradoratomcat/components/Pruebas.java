package da2.dva.integradoratomcat.components;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repositories.jpa.ClienteRepository;
import da2.dva.integradoratomcat.repositories.jpa.UsuarioClienteRepository;
import da2.dva.integradoratomcat.services.ServicioCliente;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Pruebas {

    @Autowired
    ServicioCliente servicioCliente;

    @Autowired
    ServicioUsuario servicioUsuario;

    /*@Bean
    public void prTest() {
        Cliente c = servicioCliente.getClienteByEmail("admin@integradora.jpa");



    }*/
}
