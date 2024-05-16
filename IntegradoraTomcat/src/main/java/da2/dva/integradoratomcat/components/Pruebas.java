package da2.dva.integradoratomcat.components;

import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repositories.jpa.ClienteRepository;
import da2.dva.integradoratomcat.repositories.jpa.UsuarioClienteRepository;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Pruebas {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    UsuarioClienteRepository usuarioClienteRepository;

    @Autowired
    ServicioUsuario servicioUsuario;

    @Bean
    public void prTest() {

        try{
            servicioUsuario.borrarUsuario(usuarioClienteRepository.findByEmail("admin@example.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        UsuarioCliente usuarioCliente = new UsuarioCliente();
        usuarioCliente.setClave("Admin1!");
        usuarioCliente.setConfirmClave("Admin1!");
        usuarioCliente.setEmail("admin@example.com");
        usuarioCliente.setPreguntaRecuperacion(2L);
        usuarioCliente.setRespuestaRecuperacion("Muy bien");
        usuarioClienteRepository.save(usuarioCliente);

    }
}
