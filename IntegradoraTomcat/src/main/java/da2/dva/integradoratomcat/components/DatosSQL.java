package da2.dva.integradoratomcat.components;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.model.collections.*;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repositories.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatosSQL {
    @Autowired
    private PaisRepository paisRepository;
    @Autowired
    private PreguntaRepository preguntaRepository;
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private TipoViaRepository tipoViaRepository;
    @Autowired
    private TipoClienteRepository tipoClienteRepository;
    @Autowired
    private IdiomaRepository idiomaRepository;
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioClienteRepository UCRepository;
    @Autowired
    private DireccionRepository direccionRepository;

    @Bean
    public void insertarPaises() {
        try {
            paisRepository.save(new Pais("es", "España"));
            paisRepository.save(new Pais("fr", "Francia"));
            paisRepository.save(new Pais("it", "Italia"));
            paisRepository.save(new Pais("uk", "Reino Unido"));
            paisRepository.save(new Pais("us", "Estados Unidos"));
        } catch (Exception e) {
            System.err.println("Omitida carga de datos de Países: Datos repetidos");
        }
    }

    @Bean
    public void insertarPreguntas() {
        try {
            preguntaRepository.save(new Pregunta(1L, "¿Cómo se llamaba tu primera mascota?"));
            preguntaRepository.save(new Pregunta(2L, "¿Qué tal tu madre?"));
            preguntaRepository.save(new Pregunta(3L, "¿Quién es tu mejor amigo?"));
            preguntaRepository.save(new Pregunta(4L, "¿Cuál es tu opinión sobre la insoportable levedad del ser?"));
        } catch (Exception e) {
            System.err.println("Omitida carga de datos de Preguntas: Datos repetidos");
        }

    }

    @Bean
    public void insertarGeneros() {
        try {
            generoRepository.save(new Genero("m", "Masculino"));
            generoRepository.save(new Genero("f", "Femenino"));
            generoRepository.save(new Genero("n", "No Binario"));
            generoRepository.save(new Genero("o", "Otro"));
        } catch (Exception e) {
            System.err.println("Omitida carga de datos de Géneros: Datos repetidos");
        }
    }

    @Bean
    public void insertarTipoVias() {
        try {
            tipoViaRepository.save(new TipoVia(1L, "Calle"));
            tipoViaRepository.save(new TipoVia(2L, "Avenida"));
            tipoViaRepository.save(new TipoVia(3L, "Plaza"));
            tipoViaRepository.save(new TipoVia(4L, "Glorieta"));
            tipoViaRepository.save(new TipoVia(5L, "Paseo"));
        } catch (Exception e) {
            System.err.println("Omitida carga de datos de Tipo Vías: Datos repetidos");
        }
    }

    @Bean
    public void insertarTipoClientes() {
        try {
            tipoClienteRepository.save(new TipoCliente("B", "Bronce"));
            tipoClienteRepository.save(new TipoCliente("S", "Plata"));
            tipoClienteRepository.save(new TipoCliente("G", "Oro"));
            tipoClienteRepository.save(new TipoCliente("P", "Platino"));
        } catch (Exception e) {
            System.err.println("Omitida carga de datos de Tipo Clientes: Datos repetidos");
        }
    }

    @Bean
    public void insertarIdiomas() {
        try {
            idiomaRepository.save(new Idioma("es", "Español"));
            idiomaRepository.save(new Idioma("en", "English"));
            idiomaRepository.save(new Idioma("it", "Italiano"));
        } catch (Exception e) {
            System.err.println("Omitida carga de datos de Idiomas: Datos repetidos");
        }
    }

    @Bean
    public void insertarTipoDocumentos() {
        try {
            tipoDocumentoRepository.save(new TipoDocumento("D", "DNI"));
            tipoDocumentoRepository.save(new TipoDocumento("N", "NIE"));
            tipoDocumentoRepository.save(new TipoDocumento("P", "Nº Pasaporte"));
            tipoDocumentoRepository.save(new TipoDocumento("S", "Nº Seguridad Social"));
        } catch (Exception e) {
            System.err.println("Omitida carga de datos de Tipo Documentos: Datos repetidos");
        }
    }

    @Bean
    public void insertarUsuarios() {
        try {
            UCRepository.save(new UsuarioCliente("admin@integradora.jpa", "Clientillo1!", 1L, "Croquetas"));
            UCRepository.save(new UsuarioCliente("cliente@integradora.jpa", "Clientillo2!", 2L, "Ahí va"));
            UCRepository.save(new UsuarioCliente("cliente2@integradora.jpa", "Clientillo3!", 3L, "Paco"));
        } catch (Exception e) {
            System.err.println("Omitida carga de datos de Usuarios: Datos repetidos");
        }
    }

    @Bean
    public void insertarDireccionesYClientes() {
        try {
            Direccion d1 = new Direccion(1L, 1L, "Escalona", 1, 4, "A", null, "Madrid", "28024", "Madrid", "España");
            direccionRepository.save(d1);
            Direccion d2 = new Direccion(2L, 1L, "De la Mañana", 2, 3, "B", "4", "Madrid", "28024", "Madrid", "España");
            direccionRepository.save(d2);
            Direccion d3 = new Direccion(3L, 2L, "De la Virgen", 3, 2, "C", "1", "Madrid", "28024", "Madrid", "España");
            direccionRepository.save(d3);

            clienteRepository.save(
                    new Cliente(
                            UCRepository.findByEmail("admin@integradora.jpa"), "Pepe", "García Mongólez",
                            new Genero("m", "Masculino"), paisRepository.findBySiglasPais("es"), LocalDate.of(1988, 1, 14),
                            new TipoDocumento("S", "Nº Seguridad Social"), "22/12345678/39",
                            "687456842", d1
                    )
            );

            clienteRepository.save(
                    new Cliente(
                            UCRepository.findByEmail("cilente@integradora.jpa"), "Pepo", "Compo Cosas",
                            new Genero("m", "Masculino"), paisRepository.findBySiglasPais("es"), LocalDate.of(1988, 1, 14),
                            new TipoDocumento("D", "DNI"), "00000000T",
                            "687456842", d2
                    )
            );
        } catch (Exception e) {
        System.err.println("Omitida carga de datos de Direcciones Y Clientes: Datos repetidos");
        }
    }
}
