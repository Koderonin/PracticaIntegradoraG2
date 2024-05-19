package da2.dva.integradoratomcat;

import da2.dva.integradoratomcat.model.auxiliar.TarjetaCredito;
import da2.dva.integradoratomcat.model.collections.Pais;
import da2.dva.integradoratomcat.model.entities.*;
import da2.dva.integradoratomcat.repositories.jpa.ClienteRepository;
import da2.dva.integradoratomcat.repositories.jpa.DireccionRepository;
import da2.dva.integradoratomcat.repositories.jpa.PaisRepository;
import da2.dva.integradoratomcat.repositories.jpa.UsuarioClienteRepository;
import da2.dva.integradoratomcat.services.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
//@RunWith(SpringRunner.class)
public class JPATests {

    @Autowired
    private ServicioCliente servicioCliente;
    @Autowired
    private ServicioUsuario servicioUsuario;
    @Autowired
    private ServicioNomina servicioNomina;
    @Autowired
    private ServicioLineaNomina servicioLineaNomina;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioClienteRepository UCRepository;
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private ServicioColecciones servicioColecciones;


    /**
     * -- NO USAR LOS TEST LA BASE DE DATOS EN PRODUCCIÓN --
     * <br>
     * Este método inicializa los datos de prueba para las pruebas JPA ANTES DE CADA PRUEBA. <br>
     * ¡¡Ojo!! Borran todos los clientes y usuarios existentes, se crean 2 nuevos
     * objetos de UsuarioCliente, Cliente y se guardan en la base de datos, con los que se harán las pruebas.
     */
    @BeforeEach
    @Transactional
    public void init() {
        servicioLineaNomina.borrarTodasLineasNomina();
        servicioNomina.borrarTodasNominas();
        servicioCliente.borrarTodosClientes();
        servicioUsuario.borrarTodosUsuarios();


        UsuarioCliente usuarioCliente = new UsuarioCliente();
        usuarioCliente.setEmail("prueba1@integradora.jpa");
        usuarioCliente.setNumAccesos(2);
        usuarioCliente.setClave("Cliente1!");
        usuarioCliente.setPreguntaRecuperacion(1L);
        usuarioCliente.setRespuestaRecuperacion("Gatito");
        usuarioCliente.setConfirmClave("Cliente1!");
        UCRepository.save(usuarioCliente);

        UsuarioCliente usuarioCliente2 = new UsuarioCliente();
        usuarioCliente2.setEmail("prueba2@integradora.jpa");
        usuarioCliente2.setNumAccesos(2);
        usuarioCliente2.setClave("Cliente2!");
        usuarioCliente2.setPreguntaRecuperacion(2L);
        usuarioCliente2.setRespuestaRecuperacion("Perrito");
        usuarioCliente2.setConfirmClave("Cliente2!");
        UCRepository.save(usuarioCliente2);


        Cliente cliente = new Cliente();
        cliente.setUsuarioCliente(usuarioCliente);
        cliente.setNombre("Pepe");
        cliente.setApellidos("Pérez");
        cliente.setGenero(servicioColecciones.getGeneroBySiglas("m"));
        cliente.setFechaNacimiento(LocalDate.of(1980, 6, 17));
        cliente.setTipoDocumento(servicioColecciones.getTipoDocumentoBySiglas("P"));
        cliente.setDocumento("ESP123456");
        cliente.setTelefonoMovil("742344842");
        cliente.setAceptacionLicencia(true);
        clienteRepository.save(cliente);

        Cliente cliente2 = new Cliente();
        cliente2.setUsuarioCliente(usuarioCliente2);
        cliente2.setNombre("Pepa");
        cliente2.setApellidos("Pérez");
        cliente2.setGenero(servicioColecciones.getGeneroBySiglas("f"));
        cliente2.setFechaNacimiento(LocalDate.of(1980, 6, 17));
        cliente2.setTipoDocumento(servicioColecciones.getTipoDocumentoBySiglas("D"));
        cliente2.setDocumento("50556538P");
        cliente2.setTelefonoMovil("742344843");
        cliente2.setAceptacionLicencia(true);
        clienteRepository.save(cliente2);
    }

    // TESTS CON USUARIOS Y CLIENTES

//    @Test
//    public void findClientByUser() {
//
//        UsuarioCliente usuarioClienteTest = new UsuarioCliente();
//        usuarioClienteTest.setEmail("clientetest@integradora.jpa");
//        usuarioClienteTest.setNumAccesos(2);
//        usuarioClienteTest.setClave("ClienteT1!");
//        usuarioClienteTest.setPreguntaRecuperacion(1L);
//        usuarioClienteTest.setRespuestaRecuperacion("Gatito");
//        usuarioClienteTest.setConfirmClave("ClienteT1!");
//        UCRepository.save(usuarioClienteTest);
//
//        Cliente clienteTest = new Cliente();
//        clienteTest.setUsuarioCliente(usuarioClienteTest);
//        clienteTest.setNombre("Pepe");
//        clienteTest.setApellidos("Test");
//        clienteTest.setGenero("M");
//        clienteTest.setFechaNacimiento(LocalDate.of(1980, 6, 17));
//        clienteTest.setTipoDocumento("P");
//        clienteTest.setDocumento("ESP123456");
//        clienteTest.setTelefonoMovil("742344842");
//        clienteTest.setAceptacionLicencia(true);
//        clienteRepository.save(clienteTest);
//
//        assertEquals(clienteTest, servicioCliente.getClienteByUsuario(usuarioClienteTest));
//    }

    @Test
    public void findAllClients() {
        assertIterableEquals(servicioCliente.listarClientes(), clienteRepository.findAll());
        assertEquals(2, servicioCliente.listarClientes().size());
    }

    @Test
    public void findClientByNameSurname() {

        UsuarioCliente usuarioCliente = new UsuarioCliente();
        usuarioCliente.setEmail("cliente3@integradora.jpa");
        usuarioCliente.setNumAccesos(2);
        usuarioCliente.setClave("Cliente3!");
        usuarioCliente.setPreguntaRecuperacion(1L);
        usuarioCliente.setRespuestaRecuperacion("Gatito");
        usuarioCliente.setConfirmClave("Cliente3!");
        UCRepository.save(usuarioCliente);

        Pais pais = new Pais();
        pais.setNombrePais("España");
        pais.setSiglasPais("es");
        //paisRepository.save(pais);

//        Cliente cliente = new Cliente();
//        cliente.setNombre("Trabajador");
//        cliente.setApellidos("Mc Trabajo");
//        cliente.setGenero(se);
//        cliente.setFechaNacimiento(LocalDate.of(1980, 6, 17));
//        cliente.setPaisNacimiento(pais);
//        cliente.setTipoDocumento("P");
//        cliente.setDocumento("ESP123456");
//        cliente.setTelefonoMovil("742344842");
//        cliente.setAceptacionLicencia(true);
//        cliente.setUsuarioCliente(usuarioCliente);
//        clienteRepository.save(cliente);

        // Introducimos objeto
//        assertEquals(cliente.getNombre()+cliente.getApellidos(),
//                servicioCliente.getClienteByNameAndSurname("Trabajador", "Mc Trabajo").getNombre()+
//                servicioCliente.getClienteByNameAndSurname("Trabajador", "Mc Trabajo").getApellidos());
    }

    @Test
    public void findClientByEmail() {
        assertEquals("Pepe", servicioCliente.getClienteByEmail("cliente1@integradora.jpa").getNombre());
    }

    @Test
    public void deleteClient() {
        servicioCliente.borrarCliente(servicioCliente.getClienteByEmail("cliente1@integradora.jpa"));
        assertEquals(1, clienteRepository.findAll().size());
    }

    // TESTS CON NOMINAS

//    @Test
//    public void findAllNominas() {
//        servicioNomina.borrarTodasNominas();
//
//        Nomina nomina = servicioNomina.crearNuevaNomina(servicioCliente.getClienteByEmail("cliente1@integradora.jpa"));
//        nomina.setAnio(2022L);
//        nomina.setMes(1L);
//        servicioNomina.save(nomina);
//        nomina = servicioNomina.crearNuevaNomina(servicioCliente.getClienteByEmail("cliente2@integradora.jpa"));
//        nomina.setAnio(2022L);
//        nomina.setMes(2L);
//        servicioNomina.save(nomina);
//        nomina = servicioNomina.crearNuevaNomina(servicioCliente.getClienteByEmail("cliente2@integradora.jpa"));
//        nomina.setAnio(2022L);
//        nomina.setMes(3L);
//        servicioNomina.save(nomina);
//
//        assertEquals(3, servicioNomina.findAll().size());
//    }

//    @Test
//    public void setSalarioCheckSalario() {
//
//        Cliente cliente = servicioCliente.getClienteByEmail("cliente2@integradora.jpa");
//
//        Nomina nomina = servicioNomina.crearNuevaNomina(cliente);
//        nomina.setAnio(2022L);
//        nomina.setMes(1L);
//        servicioNomina.save(nomina);
//
//        // Añadimos lineas de nomina DESPUÉS de crear la nómina, claro.
//        LineaNomina lineaNomina1 = servicioLineaNomina.nuevaLineaNomina(nomina);
//        lineaNomina1.setImporte(new BigDecimal(1080));
//        lineaNomina1.setConcepto("Base");
//        servicioLineaNomina.save(lineaNomina1);
//        LineaNomina lineaNomina2 = servicioLineaNomina.nuevaLineaNomina(nomina);
//        lineaNomina2.setImporte(new BigDecimal(120));
//        lineaNomina2.setConcepto("Bonus");
//        servicioLineaNomina.save(lineaNomina2);
//
//        // Calculo de la nómina
//        servicioNomina.setSalario(nomina);
//
//        assertEquals(new BigDecimal("1200.00"), nomina.getSalario());
//    }

    @Test
    public void probarTarjetas(){
        Cliente c = servicioCliente.getClienteByEmail("prueba1@integradora.jpa");
        TarjetaCredito tarjetaCredito = new TarjetaCredito();
        tarjetaCredito.setCvv(123);
        tarjetaCredito.setFechaVencimiento(LocalDate.of(2025, 12, 31));
        tarjetaCredito.setNumeroTarjeta(1234567890123456L);
        c.getTarjetasCredito().add(tarjetaCredito);
        servicioCliente.save(c);
        System.out.println(c.getTarjetasCredito());
    }

}