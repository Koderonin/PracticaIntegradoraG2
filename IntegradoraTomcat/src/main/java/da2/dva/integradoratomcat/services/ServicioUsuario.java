package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioAdministrador;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repositories.jpa.UsuarioAdministradorRepository;
import da2.dva.integradoratomcat.repositories.jpa.UsuarioClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Usuario getUsuarioByEmail(String email){
        return usuarioAdministradorRepository.findByEmail(email);
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
    public String actualizarUsuario(UsuarioCliente usuarioActualizado){
        try {
            UsuarioCliente usuario = (UsuarioCliente) getUsuario(usuarioActualizado.getId_usuario().toString());
            usuario.setClave(usuarioActualizado.getClave());
            usuario.setConfirmClave(usuarioActualizado.getConfirmClave());
            System.out.println(usuarioActualizado.getEmail());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setPreguntaRecuperacion(usuarioActualizado.getPreguntaRecuperacion());
            usuario.setRespuestaRecuperacion(usuarioActualizado.getRespuestaRecuperacion());
            usuario.setNumAccesos(usuarioActualizado.getNumAccesos());
            usuario.setFechaUltimaConexion(usuarioActualizado.getFechaUltimaConexion());
            usuario.setFechaBloqueo(usuarioActualizado.getFechaBloqueo());
            usuarioClienteRepository.save(usuario);
        } catch (Exception e) {
            return "Error en el servidor";
        }
        return "Usuario actualizado";
    }

    public void borrarTodosUsuarios(){
     usuarioClienteRepository.deleteAll();
    }

    public void actualizarNumAccesos(Usuario usuario){
         try {
             usuario.setNumAccesos(usuario.getNumAccesos() + 1);
         } catch (NullPointerException e) {
             usuario.setNumAccesos(1);
         }
         usuario.setConfirmClave(usuario.getClave());
        if (usuario.getAdministrador()) usuarioAdministradorRepository.save((UsuarioAdministrador) usuario);
        else usuarioClienteRepository.save((UsuarioCliente) usuario);
    }

    public void actualizarFechaBloqueo(Usuario usuario, LocalDateTime fecha) {
        usuario.setFechaBloqueo(fecha);
        usuario.setConfirmClave(usuario.getClave());
        if (usuario.getAdministrador()) usuarioAdministradorRepository.save((UsuarioAdministrador) usuario);
        else usuarioClienteRepository.save((UsuarioCliente) usuario);
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
