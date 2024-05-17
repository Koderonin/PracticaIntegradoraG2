package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.collections.*;
import da2.dva.integradoratomcat.model.entities.Usuario;
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
    private Map<Long, String> preguntas;
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
//    @Autowired
//    private UsuarioClienteRepository usuarioClienteRepository;
//    @Autowired
//    private ClienteRepository clienteRepository;
//    @Autowired
//    private DireccionRepository direccionRepository;

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
        Map<Long, String> preguntas = new HashMap<>();
        preguntaRepository.findAll().forEach(
                pregunta -> preguntas.put(pregunta.getId(), pregunta.getPregunta())
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
    public Map<String, Usuario> devuelveAdministradores() {
        Map<String, Usuario> usuarios = new HashMap<>();
        usuarioAdministradorRepository.findAll().forEach(
                usuario -> usuarios.put(usuario.getEmail(), usuario)
        );
        return usuarios;
    }

    @Override
    public void insertarPais(String siglasPais, String nombrePais) {
        paisRepository.save(new Pais(siglasPais, nombrePais));
    }

    @Override
    public void insertarGenero(String siglas, String genero) {
        generoRepository.save(new Genero(siglas, genero));
    }

    @Override
    public void insertarTipoDocumento(String siglas, String tipoDocumento) {
        tipoDocumentoRepository.save(new TipoDocumento(siglas, tipoDocumento));
    }

    @Override
    public void insertarPregunta(Long pregunta, String respuesta) {
        preguntaRepository.save(new Pregunta(pregunta, respuesta));
    }

    @Override
    public void insertarTipoVia(Long id, String tipoVia) {
        tipoViaRepository.save(new TipoVia(id, tipoVia));
    }
}