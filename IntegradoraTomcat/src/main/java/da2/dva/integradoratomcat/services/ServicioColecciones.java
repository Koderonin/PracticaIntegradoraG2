package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServicioColecciones implements Servicio {
    //TODO: IMPLEMENTAR JPA
    @Override
    public Map<String, String> devuelvePaises() {
        Map<String, String> paises = new HashMap<>();
        paises.put("ES", "España");
        paises.put("FR", "Francia");
        paises.put("DE", "Alemania");
        return paises;
    }

    @Override
    public Map<String, String> devuelveGeneros() {
        return null;
    }

    @Override
    public Map<String, String> devuelveTiposDocumentos() {
        return null;
    }

    @Override
    public Map<String, String> devuelvePreguntas() {
        Map<String, String> preguntas = new HashMap<>();
        preguntas.put("1", "¿Cómo se llamaba tu primera mascota?");
        preguntas.put("2", "¿?");
        return preguntas;
    }

    @Override
    public Map<String, String> devuelveTiposVia() {
        return null;
    }

    @Override
    public List<Usuario> devuelveUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(UUID.randomUUID(),"admin@gmail.com", "aA1111111?", "aA1111111?", "Como se llama tu perro?", "Manolo", null, null, null));
        return usuarios;
    }
}