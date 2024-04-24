package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ServicioColecciones implements Servicio {
    //TODO: IMPLEMENTAR JPA
    @Override
    public Map<String, String> devuelvePaises() {
        return null;

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
        return null;
    }

    @Override
    public Map<String, String> devuelveTiposVia() {
        return null;
    }

    @Override
    public List<Usuario> devuelveUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("admin@gmail.com", "aA1111111?", "aA1111111?", "Como se llama tu perro?", "Manolo", null, null, null));
        return usuarios;
    }
}
