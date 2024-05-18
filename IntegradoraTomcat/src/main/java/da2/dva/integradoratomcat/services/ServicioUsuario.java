package da2.dva.integradoratomcat.services;

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

     public void insertarUsuario(UsuarioCliente usuario){
         usuarioClienteRepository.save(usuario);
     }

     public void borrarUsuario(UsuarioCliente usuario){
         usuarioClienteRepository.delete(usuario);
     }

     public List<UsuarioCliente> listarUsuarios(){
         return usuarioClienteRepository.findAll();
     }

     public Usuario getUsuarioByEmail(String email){
         return usuarioAdministradorRepository.findByEmail(email);
     }

     // éste es igual que el de insertar, es una cuestión de claridad
     public void actualizarUsuario(UsuarioCliente usuario){
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

    public Map<String, Usuario> devuelveUsuarios() {
        Map<String, Usuario> usuarios = new HashMap<>();
        usuarioClienteRepository.findAll().forEach(
                usuario -> usuarios.put(usuario.getEmail(), usuario)
        );
        usuarioAdministradorRepository.findAll().forEach(
                usuario -> usuarios.put(usuario.getEmail(), usuario)
        );
        return usuarios;
    }

}
