package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.entities.Usuario;

import java.util.Map;

public interface Servicio {
     void cargarPaises();

     void cargarGeneros();

     void cargarTiposDocumentos();

     void cargarPreguntas();

     void cargarTiposVia();

     Map<String, Usuario> devuelveAdministradores();

     void insertarPais(String siglasPais, String nombrePais);

     void insertarGenero(String siglas, String genero);

     void insertarTipoDocumento(String siglas, String tipoDocumento);

     void insertarPregunta(Long pregunta, String respuesta);

     void insertarTipoVia(String siglas, String tipoVia);




//     // mover a sus respectivos servicios!
//     void insertarUsuarioEmpleado(UsuarioCliente usuario);
//
//     void insertarCliente (Cliente cliente);
}
