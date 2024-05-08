package da2.dva.integradoratomcat.components;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.model.collections.Pais;
import da2.dva.integradoratomcat.model.entities.*;
import da2.dva.integradoratomcat.repositories.jpa.*;
import da2.dva.integradoratomcat.repositories.mongo.ProductoRepository;
import org.bson.BsonString;
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
        usuarioCliente.setClave("Clientillo1!");
        usuarioCliente.setPreguntaRecuperacion(1L);
        usuarioCliente.setRespuestaRecuperacion("Croquetas");
        usuarioCliente.setConfirmClave("Clientillo1!");
        UCRepository.save(usuarioCliente);
        //Dirección asignada
        Direccion direccion = getDireccion();
        direccionRepository.save(direccion);

        Cliente cliente = new Cliente();

        Pais pais = new Pais();
        pais.setNombrePais("España");
        pais.setSiglasPais("es");
        paisRepository.save(pais);

        if (usuarioCliente != null) {
            cliente.setUsuarioCliente(usuarioCliente);
            cliente.setDireccion(direccion);
            cliente.setPaisNacimiento(pais);
            cliente.setNombre("Pepe");
            cliente.setApellidos("García Mongólez");
            cliente.setGenero("Hombre");
            cliente.setFechaNacimiento(LocalDate.of(1988, 1, 14));
            cliente.setTipoDocumento("S");
            cliente.setDocumento("22/12345678/39");
            cliente.setTelefonoMovil("687456842");
            clienteRepository.save(cliente);
        } else {
            System.err.println("No lo pilló.");
        }

        cliente.setComentarios("Trololó");
        cliente.setAceptacionLicencia(true);
        cliente.setTipoDocumento("D");
        cliente.setDocumento("00000000T");
        clienteRepository.save(cliente);

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
        producto.setCodigo(new BsonString("12345"));
        producto.setPrecio(new BigDecimal("1200000.5"));
        producto.setDescripcion("Deportivo Berlinetta biplaza");
        producto.setModelo(new BsonString("Testarossa"));
        producto.setMarca(new BsonString("Ferrari"));
        producto.setUnidadesVendidas(2);
        producto.setGastoAcumulado(new BigDecimal("1200000.5")
                .multiply(new BigDecimal(producto.getUnidadesVendidas())));
        producto.setCantidadAlmacen(5);
       // producto.append("color", "rojo");
        productoRepository.save(producto);

        UsuarioCliente usuarioCliente = new UsuarioCliente();
        usuarioCliente.setEmail("admin2@integradora.jpa");
        usuarioCliente.setNumAccesos(1);
        usuarioCliente.setClave("Cliente123!");
        usuarioCliente.setPreguntaRecuperacion(2L);
        usuarioCliente.setRespuestaRecuperacion("Croquetas");
        usuarioCliente.setConfirmClave("Cliente123!");
        UCRepository.save(usuarioCliente);

        Pais pais = new Pais();
        pais.setNombrePais("España");
        pais.setSiglasPais("es");
        paisRepository.save(pais);

        Cliente cliente = new Cliente();
        cliente.setUsuarioCliente(usuarioCliente);
        cliente.setNombre("Pepo");
        cliente.setApellidos("Compro Cosas");
        cliente.setGenero("M");
        cliente.setFechaNacimiento(LocalDate.of(1978, 2, 14));
        cliente.setPaisNacimiento(pais);
        cliente.setTipoDocumento("P");
        cliente.setDocumento("ESP123456");
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
        linea.setProducto_id(producto.getId().toHexString());
        linea.setPedido(pedido);
        lineaPedidoRepository.save(linea);

    }

    private static Direccion getDireccion() {
        Direccion direccion = new Direccion();
        direccion.setTipo_via(1L);
        direccion.setCp("28024");
        direccion.setLocalidad("Madrid");
        direccion.setNombre_via("Escalona");
        direccion.setPais("es");
        direccion.setPlanta(4);
        direccion.setPuerta("1");
        direccion.setRegion("Madrid");
        direccion.setNumero_via(1);
        return direccion;
    }
}
