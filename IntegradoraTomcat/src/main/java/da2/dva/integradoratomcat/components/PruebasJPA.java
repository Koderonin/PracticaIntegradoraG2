package da2.dva.integradoratomcat.components;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.model.auxiliar.Pais;
import da2.dva.integradoratomcat.model.entities.*;
import da2.dva.integradoratomcat.repositories.jpa.*;
import da2.dva.integradoratomcat.repositories.mongo.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
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
        usuarioCliente.setClave("Admin123!");
        usuarioCliente.setPreguntaRecuperacion("1");
        usuarioCliente.setRespuestaRecuperacion("Croquetas");
        usuarioCliente.setConfirmClave("Admin123!");
        UCRepository.save(usuarioCliente); // esto hace que quede detached, por lo que sea
        /*Dirección asignada*/
        Direccion direccion = getDireccion();
        direccionRepository.save(direccion);
        /**/
//        Cliente cliente = new Cliente();

        Pais pais = new Pais();
        pais.setNombrePais("España");
        pais.setSiglasPais("ES");
        paisRepository.save(pais);

//        if (usuarioCliente != null) {
//            cliente.setUsuarioCliente(usuarioCliente);
//            cliente.setDireccion(direccion);
//            cliente.setPais(pais);
//            cliente.setNombre("Pepe");
//            cliente.setApellidos("García Mongólez");
//            cliente.setGenero("Hombre");
//            cliente.setFechaNacimiento(LocalDate.of(1988, 1, 14));
//            cliente.setTipoDocumento("DNI");
//            cliente.setDocumento("12345678A");
//            cliente.setTelefonoMovil("687456842");
//            clienteRepository.save(cliente);
//        } else {
//            System.err.println("No lo pilló.");
//        }
/*
        cliente.setComentarios("Trololó");
        cliente.setAceptacionLicencia(true);
        cliente.setDocumento("12345678B");
        clienteRepository.save(cliente);
*/
    }

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private LineaPedidoRepository lineaPedidoRepository;
    @Bean
    public void pruebitasPedidos() {
        Producto producto = new Producto();
        producto.setCodigo("1234");
        producto.setPrecio(new BigDecimal("12.5"));
        producto.setDescripcion("Muy bonita");
        producto.setModelo("Cosa guayyyyyyyyyyy");
        productoRepository.save(producto);

        UsuarioCliente usuarioCliente = new UsuarioCliente();
        usuarioCliente.setEmail("admin2@integradora.jpa");
        usuarioCliente.setNumAccesos(1);
        usuarioCliente.setClave("Admin123!");
        usuarioCliente.setPreguntaRecuperacion("2");
        usuarioCliente.setRespuestaRecuperacion("Croquetas");
        usuarioCliente.setConfirmClave("Admin123!");
        UCRepository.save(usuarioCliente);

        Pais pais = new Pais();
        pais.setNombrePais("España");
        pais.setSiglasPais("ES");
        paisRepository.save(pais);

        Cliente cliente = new Cliente();
        cliente.setUsuarioCliente(usuarioCliente);
        cliente.setNombre("Pepo");
        cliente.setApellidos("Compro Cosas");
        cliente.setGenero("M");
        cliente.setFechaNacimiento(LocalDate.of(1978, 2, 14));
        cliente.setPais(pais);
        cliente.setTipoDocumento("D");
        cliente.setDocumento("12345678C");
        cliente.setTelefonoMovil("642344842");
        cliente.setAceptacionLicencia(true);
        clienteRepository.save(cliente);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado("Pendiente");
        pedidoRepository.save(pedido);

        LineaPedido linea = new LineaPedido();
        linea.setUnidades(2);
        linea.setPrecioUnitario(producto);
        linea.setProducto_id(producto.getId());
        linea.setPedido(pedido);
        lineaPedidoRepository.save(linea);

    }

    private static Direccion getDireccion() {
        Direccion direccion = new Direccion();
        direccion.setCp("28024");
        direccion.setLocalidad("Madrid");
        direccion.setNombre_via("Escalona");
        direccion.setPais("ES");
        direccion.setPlanta("4");
        direccion.setPuerta("1");
        direccion.setRegion("Madrid");
//        direccion.setTipo_via(1L);
        direccion.setNumero_via(1);
        return direccion;
    }
}
