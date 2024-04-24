package da2.dva.integradoratomcat.components;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.model.auxiliar.Pais;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioAdministrador;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

@Controller
public class PruebasJPA {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioClienteRepository UCRepository;
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private PaisRepository paisRepository;

    @Bean
    //@Transactional // esto hace que no falle la transacción porque la hace toda de una; no es lo que quiero
    public void pruebitas() {

        UsuarioCliente usuarioCliente = new UsuarioCliente();
        usuarioCliente.setEmail("admin@integradora.jpa");
        usuarioCliente.setNumAccesos(2);
        usuarioCliente.setClave("admin");
//        UCRepository.save(usuarioCliente); esto hace que quede detached, por lo que sea
        /*Dirección asignada*/
        Direccion direccion = getDireccion();
        direccionRepository.save(direccion);
        /**/
        Cliente cliente = new Cliente();

        Pais pais = new Pais();
        pais.setNombrePais("España");
        pais.setSiglasPais("ES");
        paisRepository.save(pais);

        if (usuarioCliente != null) {
            cliente.setUsuarioCliente(usuarioCliente);
            cliente.setDireccion(direccion);
            cliente.setPaisNacimiento(pais);
            cliente.setNombre("Pepe");
            cliente.setApellidos("García Mongólez");
            cliente.setGenero("Hombre");
            cliente.setFechaNacimiento(LocalDate.of(1988, 1, 14));
            cliente.setTipoDocumento("DNI");
            cliente.setDocumento("12345678A");
            cliente.setTelefonoMovil("687456842");
            clienteRepository.save(cliente);
        } else {
            System.err.println("No lo pilló.");
        }
/*
        cliente.setComentarios("Trololó");
        cliente.setAceptacionLicencia(true);
        cliente.setDocumento("12345678B");
        clienteRepository.save(cliente);
*/
    }

    private static Direccion getDireccion() {
        Direccion direccion = new Direccion();
        direccion.setCp(28024);
        direccion.setLocalidad("Madrid");
        direccion.setNombre_via("Escalona");
        direccion.setPais("ES");
        direccion.setPiso("4");
        direccion.setPuerta("1");
        direccion.setRegion("Madrid");
        direccion.setTipo_via("Calle");
        direccion.setNumero_via(1);
        return direccion;
    }

    public static void pruebas_consola() {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("1- Insertar cliente");
            System.out.println("2- Insertar usuario administrador");
            System.out.println("3- Insertar usuario cliente");
            System.out.println("4- Insertar pais");
            System.out.println("5- Insertar direccion");
            System.out.println("6- Insertar tarjeta credito");
            System.out.println("0- Salir");

            int opt = sc.nextInt();

            switch (opt) {
                case 1:
                    insertarCliente();
                    break;
                case 2:
                    insertarUsuarioAdministrador();
                    break;
                case 3:
                    insertarUsuarioCliente();
                    break;
                case 4:
                    insertarPais();
                    break;
                case 5:
                    insertarDireccion();
                    break;
                case 6:
                    insertarTarjetaCredito();
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }

    private static void insertarTarjetaCredito() {
    }

    private static void insertarDireccion() {
    }

    private static void insertarPais() {
    }

    private static void insertarUsuarioCliente() {
    }

    private static void insertarUsuarioAdministrador() {
    }

    private static void insertarCliente() {
    }
}
