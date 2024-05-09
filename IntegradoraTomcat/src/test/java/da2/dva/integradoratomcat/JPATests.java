package da2.dva.integradoratomcat;

import da2.dva.integradoratomcat.model.collections.Pais;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repositories.jpa.ClienteRepository;
import da2.dva.integradoratomcat.repositories.jpa.DireccionRepository;
import da2.dva.integradoratomcat.repositories.jpa.PaisRepository;
import da2.dva.integradoratomcat.repositories.jpa.UsuarioClienteRepository;
import da2.dva.integradoratomcat.services.ServicioCliente;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class JPATests {

    @Autowired
    private ServicioCliente servicioCliente;
    @Autowired
    private ServicioUsuario servicioUsuario;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioClienteRepository UCRepository;
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private PaisRepository paisRepository;

    @Bean
    public ServicioCliente servicioCliente() {
        return new ServicioCliente();
    }

    @Test
    public void setUp() {

        servicioCliente.borrarTodosClientes();
        servicioUsuario.borrarTodosUsuarios();

        UsuarioCliente usuarioCliente = new UsuarioCliente();
        usuarioCliente.setEmail("cliente1@integradora.jpa");
        usuarioCliente.setNumAccesos(2);
        usuarioCliente.setClave("Cliente1!");
        usuarioCliente.setPreguntaRecuperacion(1L);
        usuarioCliente.setRespuestaRecuperacion("Gatito");
        usuarioCliente.setConfirmClave("Cliente1!");
        UCRepository.save(usuarioCliente);

//        UsuarioCliente usuarioCliente2 = new UsuarioCliente();
//        usuarioCliente.setEmail("cliente2@integradora.jpa");
//        usuarioCliente.setNumAccesos(2);
//        usuarioCliente.setClave("Cliente2!");
//        usuarioCliente.setPreguntaRecuperacion(2L);
//        usuarioCliente.setRespuestaRecuperacion("Perrito");
//        usuarioCliente.setConfirmClave("Cliente2!");
//        UCRepository.save(usuarioCliente2);


        Cliente cliente = new Cliente();
        cliente.setUsuarioCliente(usuarioCliente);
        cliente.setNombre("Pepe");
        cliente.setApellidos("Pérez");
        cliente.setGenero("M");
        cliente.setFechaNacimiento(LocalDate.of(1980, 6, 17));
        cliente.setTipoDocumento("P");
        cliente.setDocumento("ESP123456");
        cliente.setTelefonoMovil("742344842");
        cliente.setAceptacionLicencia(true);
        clienteRepository.save(cliente);

        assertEquals(cliente, servicioCliente.getClienteByUsuario(usuarioCliente));

    }
    @Test
    public void findByName_thenReturnClient() {
        UsuarioCliente usuarioCliente = new UsuarioCliente();
        usuarioCliente.setEmail("cliente1@integradora.jpa");
        usuarioCliente.setNumAccesos(2);
        usuarioCliente.setClave("Cliente1!");
        usuarioCliente.setPreguntaRecuperacion(1L);
        usuarioCliente.setRespuestaRecuperacion("Gatito");
        usuarioCliente.setConfirmClave("Cliente1!");
        //UCRepository.save(usuarioCliente);

        Pais pais = new Pais();
        pais.setNombrePais("España");
        pais.setSiglasPais("es");
        //paisRepository.save(pais);

        Cliente cliente = new Cliente();
        cliente.setNombre("Trabajador");
        cliente.setApellidos("Mc Trabajo");
        cliente.setGenero("M");
        cliente.setFechaNacimiento(LocalDate.of(1980, 6, 17));
        cliente.setPaisNacimiento(pais);
        cliente.setTipoDocumento("P");
        cliente.setDocumento("ESP123456");
        cliente.setTelefonoMovil("742344842");
        cliente.setAceptacionLicencia(true);

        // Introducimos objeto
        assertEquals(cliente.getNombre()+cliente.getApellidos(),
                servicioCliente.getClienteByNameAndSurname("Trabajador", "Mc Trabajo").getNombre()+
                servicioCliente.getClienteByNameAndSurname("Trabajador", "Mc Trabajo").getApellidos());
    }

}
