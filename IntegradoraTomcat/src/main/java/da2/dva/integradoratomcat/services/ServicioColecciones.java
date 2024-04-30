package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repositories.jpa.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Getter
public class ServicioColecciones implements Servicio {

    private Map<String, String> paises;
    private Map<String, String> generos;
    private Map<String, String> tiposDocumentos;
    private Map<String, String> preguntas;
    private Map<Long, String> tiposVia;
    private Map<String, Usuario> usuarios;

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
    public void cargarPaises() {
        Map<String, String> paises = new HashMap<>();
        paisRepository.findAll().forEach(
                pais -> paises.put(pais.getSiglasPais(), pais.getNombrePais())
        );
        this.paises = paises;
    }

    @Override
    public void cargarGeneros() {
        Map<String, String> generos = new HashMap<>();
        generoRepository.findAll().forEach(
                genero -> generos.put(genero.getSiglas(), genero.getGenero())
        );
        this.generos = generos;
    }

    @Override
    public void cargarTiposDocumentos() {
        Map<String, String> tiposDocumentos = new HashMap<>();
        tipoDocumentoRepository.findAll().forEach(
                tipoDocumentos -> tiposDocumentos.put(tipoDocumentos.getSiglas(), tipoDocumentos.getTipoDocumento())
        );
        this.tiposDocumentos = tiposDocumentos;
    }

    @Override
    public void cargarPreguntas() {
        Map<String, String> preguntas = new HashMap<>();
        preguntaRepository.findAll().forEach(
                pregunta -> preguntas.put(String.valueOf(pregunta.getId()), pregunta.getPregunta())
        );
        this.preguntas = preguntas;
    }

    @Override
    public void cargarTiposVia() {
        Map<Long, String> tiposVia = new HashMap<>();
        tipoViaRepository.findAll().forEach(
                tipoVia -> tiposVia.put(tipoVia.getId(), tipoVia.getTipoVia())
        );
        this.tiposVia = tiposVia;
    }

    @Override
    public void cargarUsuarios() {
        Map<String, Usuario> usuarios = new HashMap<>();
        // TODO: Eliminar la inserción en este método
        usuarios.put("admin@gmail.com", new Usuario(UUID.randomUUID(),"admin@gmail.com", "aA1111111?", "aA1111111?", "Como se llama tu perro?", "Manolo", null, null, null));
        usuarioAdministradorRepository.findAll().forEach(
                usuario -> usuarios.put(usuario.getEmail(), usuario)
        );
        usuarioClienteRepository.findAll().forEach(
                usuario -> usuarios.put(usuario.getEmail(), usuario)
        );
        this.usuarios = usuarios;
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