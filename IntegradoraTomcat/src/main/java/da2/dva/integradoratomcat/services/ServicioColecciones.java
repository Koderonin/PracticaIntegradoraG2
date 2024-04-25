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
        Map<String, String> generos = new HashMap<>();
        generos.put("M", "Masculino");
        generos.put("F", "Femenino");
        generos.put("O", "Otro");
        generos.put("N", "No binario");
        return generos;
    }

    @Override
    public Map<String, String> devuelveTiposDocumentos() {
        Map<String, String> tiposDocumentos = new HashMap<>();
        tiposDocumentos.put("D", "DNI");
        tiposDocumentos.put("N", "NIE");
        tiposDocumentos.put("P", "Pasaporte");
        return tiposDocumentos;
    }

    @Override
    public Map<String, String> devuelvePreguntas() {
        Map<String, String> preguntas = new HashMap<>();
        preguntas.put("1", "¿Cómo se llamaba tu primera mascota?");
        preguntas.put("2", "¿Qué tal tu madre?");
        preguntas.put("3", "¿Quién es tu mejor amigo?");
        preguntas.put("4", "¿Cuál es tu opinión sobre la insoportable levedad del ser?");
        return preguntas;
    }

    @Override
    public Map<String, String> devuelveTiposVia() {
        Map<String, String> tiposVia = new HashMap<>();
        tiposVia.put("CL", "Calle");
        tiposVia.put("PZ", "Plaza");
        tiposVia.put("AV", "Avenida");
        tiposVia.put("TR", "Travesia");
        tiposVia.put("PS", "Paseo");
        tiposVia.put("GL", "Glorieta");
        return tiposVia;
    }

    @Override
    public List<Usuario> devuelveUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(UUID.randomUUID(),"admin@gmail.com", "aA1111111?", "aA1111111?", "Como se llama tu perro?", "Manolo", null, null, null));
        return usuarios;
    }
}