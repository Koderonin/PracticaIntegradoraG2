package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;

import java.util.List;
import java.util.Map;

public interface Servicio {
     void cargarPaises();

     void cargarGeneros();

     void cargarTiposDocumentos();

     void cargarPreguntas();

     void cargarTiposVia();

     void cargarUsuarios();

     void insertarUsuarioEmpleado(UsuarioCliente usuario);

     void insertarCliente (Cliente cliente);
}
