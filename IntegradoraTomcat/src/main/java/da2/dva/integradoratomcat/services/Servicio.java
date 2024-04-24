package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.entities.Usuario;

import java.util.List;
import java.util.Map;

public interface Servicio {
     Map<String,String> devuelvePaises();

     Map<String,String> devuelveGeneros();

     Map<String,String> devuelveTiposDocumentos();

     Map<String,String> devuelvePreguntas();

     Map<String,String> devuelveTiposVia();

     List<Usuario> devuelveUsuarios();

}
