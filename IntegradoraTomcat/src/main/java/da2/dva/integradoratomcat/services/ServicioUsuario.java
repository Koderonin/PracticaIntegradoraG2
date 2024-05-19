package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repositories.jpa.UsuarioAdministradorRepository;
import da2.dva.integradoratomcat.repositories.jpa.UsuarioClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


/**
 * @since 0.7
 * <br/>
 * Esta clase hace alusión al objeto {@link da2.dva.integradoratomcat.model.entities.UsuarioCliente}.
 * Se llama Usuario y no UsuarioCliente porque el objeto {@link da2.dva.integradoratomcat.model.entities.UsuarioAdministrador}
 * no tiene acceso a través de aplicación para creación, modificación o eliminación.
 * <br/>
 * Se encarga de realizar todas las operaciones sobre la base de datos.*
 * @author Vicen
 * @version 0.7
 * @since 0.7
 */
@Service("userDetailsService")
@Transactional
public class ServicioUsuario {

    @Autowired
    private UsuarioClienteRepository usuarioClienteRepository;

    @Autowired
    private UsuarioAdministradorRepository usuarioAdministradorRepository;

    public Usuario getUsuario(String id) {
        return usuarioClienteRepository.findById(UUID.fromString(id)).get();
    }

    public void insertarUsuario(UsuarioCliente usuario){
     usuarioClienteRepository.save(usuario);
    }

    public void borrarUsuario(UsuarioCliente usuario){
     usuarioClienteRepository.delete(usuario);
    }

    public List<UsuarioCliente> listarUsuarios(){
     return usuarioClienteRepository.findAll();
    }

    // éste es igual que el de insertar, es una cuestión de claridad
    public void actualizarUsuario(UsuarioCliente usuario){
        System.out.println(usuario.toString());
        usuarioClienteRepository.save(usuario);
    }

    public void borrarTodosUsuarios(){
     usuarioClienteRepository.deleteAll();
    }

    public void actualizarNumAccesos(UsuarioCliente usuario){
         try {
             usuario.setNumAccesos(usuario.getNumAccesos() + 1);
         } catch (NullPointerException e) {
             usuario.setNumAccesos(1);
         }
         // ODO: mirar si en algún momento se puede quitar la remilmierda ésta.
         usuario.setConfirmClave(usuario.getClave());
         usuarioClienteRepository.save(usuario);
    }

    public void actualizarFechaBloqueo(UsuarioCliente usuario, LocalDate fecha) {
        usuario.setFechaBloqueo(fecha);
        usuario.setConfirmClave(usuario.getClave());
        usuarioClienteRepository.save(usuario);
    }

    public Map<String, UsuarioCliente> devuelveUsuarios() {
        Map<String, UsuarioCliente> usuarios = new HashMap<>();
        UsuarioCliente cliente1 = new UsuarioCliente(UUID.randomUUID(),"admin@gmail.com", "aA1111111?", "aA1111111?", 1L, "Manolo");
        usuarios.put(cliente1.getEmail(), cliente1);
        usuarioClienteRepository.findAll().forEach(
                usuario -> usuarios.put(usuario.getEmail(), usuario)
        );
        return usuarios;
    }

}
