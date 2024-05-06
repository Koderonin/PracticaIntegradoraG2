package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;

import java.util.List;
import java.util.Map;

public interface Servicio {
     Map<String,String> devuelvePaises();

     Map<String,String> devuelveGeneros();

     Map<String,String> devuelveTiposDocumentos();

     Map<String,String> devuelvePreguntas();

     Map<Long, String> devuelveTiposVia();

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
