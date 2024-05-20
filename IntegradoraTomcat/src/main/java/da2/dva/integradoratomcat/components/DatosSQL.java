package da2.dva.integradoratomcat.components;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.model.collections.*;
import da2.dva.integradoratomcat.model.entities.*;
import da2.dva.integradoratomcat.repositories.jpa.*;
import da2.dva.integradoratomcat.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    private UsuarioAdministradorRepository UARepository;
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private TipoTarjetaRepository tipoTarjetaRepository;

    @Autowired
    private ServicioCliente servicioCliente;

    @Autowired
    private ServicioColecciones servicioColecciones;

    @Autowired
    private ServicioLineaNomina servicioLineaNomina;

    @Autowired
    private ServicioUsuario servicioUsuario;

    @Autowired
    private ServicioNomina servicioNomina;

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
    public void insertatTiposTarjeta() {
        try {
            tipoTarjetaRepository.save(new TipoTarjeta(1L, "Visa", "visa.png"));
            tipoTarjetaRepository.save(new TipoTarjeta(2L, "MasterCard", "mastercard.png"));
            tipoTarjetaRepository.save(new TipoTarjeta(3L, "American Express", "amex.png"));
            tipoTarjetaRepository.save(new TipoTarjeta(4L, "Diners Club", "diners.png"));
        } catch (Exception e) {
            System.err.println("Omitida carga de datos de Tipos de Tarjetas: Datos repetidos");
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
            idiomaRepository.save(new Idioma("es", "\uD83C\uDDEA\uD83C\uDDF8 ES"));
            idiomaRepository.save(new Idioma("en", "\uD83C\uDDEC\uD83C\uDDE7 EN"));
            idiomaRepository.save(new Idioma("it", "\uD83C\uDDEE\uD83C\uDDF9 IT"));
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
            UARepository.save(new UsuarioAdministrador("admin@integradora.jpa", "Administra1!", 1L, "Croquetas"));
            UCRepository.save(new UsuarioCliente("cliente@integradora.jpa", "Clientillo1!", 2L, "Ahí va"));
            UCRepository.save(new UsuarioCliente("cliente2@integradora.jpa", "Clientillo2!", 3L, "Paco"));
            UCRepository.save(new UsuarioCliente("cliente3@integradora.jpa", "Clientillo3!", 4L, "Juan"));
        } catch (Exception e) {
            System.err.println("Omitida carga de datos de Usuarios: Datos repetidos");
        }
    }

    @Bean
    public void insertarDireccionesYClientes() {
        try {
            Direccion d1 = new Direccion(1L, servicioColecciones.getTipoViaById(1L), "Escalona", 1, 4, "A", null, "Madrid", "28024", "Madrid", "España");
            direccionRepository.save(d1);
            Direccion d2 = new Direccion(2L, servicioColecciones.getTipoViaById(1L), "De la Mañana", 2, 3, "B", "4", "Madrid", "28024", "Madrid", "España");
            direccionRepository.save(d2);
            Direccion d3 = new Direccion(3L, servicioColecciones.getTipoViaById(2L), "De la Virgen", 3, 2, "C", "1", "Madrid", "28024", "Madrid", "España");
            direccionRepository.save(d3);

            UsuarioCliente u1 = UCRepository.findByEmail("cliente@integradora.jpa");
            UsuarioCliente u2 = UCRepository.findByEmail("cliente2@integradora.jpa");

            Pais es = paisRepository.findBySiglasPais("es");
            TipoDocumento dni = tipoDocumentoRepository.findBySiglas("D");
            TipoDocumento seg = tipoDocumentoRepository.findBySiglas("S");
            Genero masc = generoRepository.findBySiglas("m");
            Genero nbin = generoRepository.findBySiglas("n");


            clienteRepository.save(
                    new Cliente(
                            u1, "Pepe", "García Mongólez",
                            masc, es, LocalDate.of(1988, 1, 14),
                            dni, "00000000T",
                            "687456842", d1
                    )
            );

            clienteRepository.save(
                    new Cliente(
                            u2, "Pepo", "Compo Cosas",
                            nbin, es, LocalDate.of(1988, 1, 14),
                            seg, "22/12345678/39",
                            "687456842", d2
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Omitida carga de datos de Direcciones Y Clientes: Datos repetidos");
        }
    }

    @Bean
    public void insertarNominas() {
        try{
            Cliente cliente = servicioCliente.getClienteByEmail("cliente@integradora.jpa");

            Nomina nomina = servicioNomina.crearNuevaNomina(cliente, new Nomina());
            nomina.setAnio(2022L);
            nomina.setMes(1L);
            servicioNomina.save(nomina);

            // Añadimos lineas de nomina DESPUÉS de crear la nómina, claro.
            LineaNomina lineaNomina1 = servicioLineaNomina.nuevaLineaNomina(nomina);
            lineaNomina1.setImporte(new BigDecimal(1080));
            lineaNomina1.setConcepto("Base");
            servicioLineaNomina.save(lineaNomina1);
            LineaNomina lineaNomina2 = servicioLineaNomina.nuevaLineaNomina(nomina);
            lineaNomina2.setImporte(new BigDecimal(120));
            lineaNomina2.setConcepto("Bonus");
            servicioLineaNomina.save(lineaNomina2);

            // Calculo de la nómina
            servicioNomina.setSalario(nomina);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Omitida carga de datos de Nominas: datos repetidos");
        }


    }
}
