package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repositories.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServicioColecciones implements Servicio {
    @Autowired
    private PaisRepository paisRepository;
    @Autowired
    private TipoViaRepository tipoViaRepository;
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private PreguntaRepository preguntaRepository;
    @Autowired
    private UsuarioAdministradorRepository usuarioAdministradorRepository;
    @Autowired
    private UsuarioClienteRepository usuarioClienteRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    public Map<String, String> devuelvePaises() {
        Map<String, String> paises = new HashMap<>();
        paisRepository.findAll().forEach(
                pais -> paises.put(pais.getSiglasPais(), pais.getNombrePais())
        );
        return paises;
    }

    @Override
    public Map<String, String> devuelveGeneros() {
        Map<String, String> generos = new HashMap<>();
        generoRepository.findAll().forEach(
                genero -> generos.put(genero.getSiglas(), genero.getGenero())
        );
        return generos;
    }

    @Override
    public Map<String, String> devuelveTiposDocumentos() {
        Map<String, String> tiposDocumentos = new HashMap<>();
        tipoDocumentoRepository.findAll().forEach(
                tipoDocumentos -> tiposDocumentos.put(tipoDocumentos.getSiglas(), tipoDocumentos.getTipoDocumento())
        );
        return tiposDocumentos;
    }

    @Override
    public Map<String, String> devuelvePreguntas() {
        Map<String, String> preguntas = new HashMap<>();
        preguntaRepository.findAll().forEach(
                pregunta -> preguntas.put(String.valueOf(pregunta.getId()), pregunta.getPregunta())
        );
        return preguntas;
    }

    @Override
    public Map<Long, String> devuelveTiposVia() {
        Map<Long, String> tiposVia = new HashMap<>();
        tipoViaRepository.findAll().forEach(
                tipoVia -> tiposVia.put(tipoVia.getId(), tipoVia.getTipoVia())
        );
        return tiposVia;
    }

    @Override
    public Map<String, Usuario> devuelveUsuarios() {
        Map<String, Usuario> usuarios = new HashMap<>();
        usuarios.put("admin@gmail.com", new Usuario(UUID.randomUUID(),"admin@gmail.com", "aA1111111?", "aA1111111?", "Como se llama tu perro?", "Manolo", null, null, null));
        usuarioAdministradorRepository.findAll().forEach(
                usuario -> usuarios.put(usuario.getEmail(), usuario)
        );
        usuarioClienteRepository.findAll().forEach(
                usuario -> usuarios.put(usuario.getEmail(), usuario)
        );
        return usuarios;
    }
    @Override
    public void insertarUsuarioEmpleado(UsuarioCliente usuario){
        usuarioClienteRepository.save(usuario);
    }

    @Override
    public void insertarCliente (Cliente cliente){
        direccionRepository.save(cliente.getDireccion());
        clienteRepository.save(cliente);}
}