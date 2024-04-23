package da2.dva.integradoratomcat.components;

import da2.dva.integradoratomcat.model.auxiliar.Pais;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioAdministrador;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repository.ClienteRepository;
import da2.dva.integradoratomcat.repository.UsuarioAdministradorRepository;
import da2.dva.integradoratomcat.repository.UsuarioClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class PruebasJPA {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioClienteRepository UCRepository;

    @Bean
    public void pruebitas() {

        UsuarioCliente usuarioCliente = new UsuarioCliente();
        usuarioCliente.setEmail("admin@integradora.jpa");
        usuarioCliente.setNumAccesos(2);
        usuarioCliente = UCRepository.save(usuarioCliente);
        Cliente cliente = new Cliente();
        cliente.setUsuarioCliente(usuarioCliente);
        cliente.setNombre("Pepe");
        cliente.setApellidos("García Mongólez");
        cliente.setGenero("Hombre");
        cliente.setFechaNacimiento(LocalDate.of(1988, 1, 14));
        cliente.setPaisNacimiento(new Pais("ES", "España"));
        cliente.setTipoDocumento("DNI");
        cliente.setDocumento("12345678A");
        cliente.setTelefonoMovil("687456842");
        clienteRepository.save(cliente);
    }
}
