package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.model.auxiliar.TarjetaCredito;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Producto;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repositories.jpa.ClienteRepository;
import da2.dva.integradoratomcat.repositories.jpa.DireccionRepository;
import da2.dva.integradoratomcat.repositories.jpa.UsuarioAdministradorRepository;
import da2.dva.integradoratomcat.repositories.jpa.UsuarioClienteRepository;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ServicioCliente {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private UsuarioClienteRepository UCRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PersistenceContext
    private jakarta.persistence.EntityManager entityManager;

    public void insertarNuevoCliente(Cliente cliente){
    //    direccionRepository.save(cliente.getDireccion());
        clienteRepository.save(cliente);
    }

    public Cliente getClienteByUsuario(UsuarioCliente usuario){
        return clienteRepository.findByUsuarioCliente(usuario);
    }

    public Cliente getClienteByNameAndSurname(String nombre, String apellidos){
        return clienteRepository.findByNombreAndApellidos(nombre, apellidos);
    }

    public Cliente getClienteByEmail(String email){
        return clienteRepository.findByUsuarioCliente(UCRepository.findByEmail(email));
    }

    public void borrarCliente(Cliente cliente){
        clienteRepository.delete(cliente);
    }

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    // listo todos los clientes, pero sólo con los campos elegidos
    public List findClientesByAttributes(Document atributos) {

        LocalDate fechaNacimiento = atributos.get("fechaNacimiento", LocalDate.class);
        String nombre = atributos.getString("nombre");
        String apellidos = atributos.getString("apellidos");
        String email = atributos.getString("email");
        String direccion = atributos.getString("direccion");
        // Obtén una instancia de CriteriaBuilder del EntityManager
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

// Crea una consulta de Criteria
        CriteriaQuery<Producto> query = cb.createQuery(Producto.class);

// Define la raíz de la consulta (es decir, la tabla desde la que estás seleccionando)
        Root<Producto> root = query.from(Producto.class);

// Crea una lista para almacenar las condiciones de la consulta
        List<Predicate> predicates = new ArrayList<>();

// Añadimos condiciones a la consulta en función de los filtros
//        if (marca != null) {
//            predicates.add(cb.equal(root.get("marca"), marca));
//        }
//        if (precioMax != null) {
//            predicates.add(cb.le(root.get("precio"), precioMax));
//        }


// Aplica las condiciones a la consulta
        query.where(predicates.toArray(new Predicate[0]));

// Ejecuta la consulta y obtén los resultados
        return entityManager.createQuery(query).getResultList();
    }

    public void actualizarCliente(Cliente cliente){
        clienteRepository.save(cliente);
    }

    public void borrarTodosClientes(){
        clienteRepository.deleteAll();
    }

    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void agregarTarjeta(Cliente cliente, TarjetaCredito tarjeta) {
        tarjeta.setId_tarjeta(tarjeta.getTipoTarjetaCredito() + "-" + tarjeta.getNumeroTarjeta() + "-" + tarjeta.getCvv());
        cliente.getTarjetasCredito().add(tarjeta);
        clienteRepository.save(cliente);
    }

    public void agregarDireccion(Cliente cliente, Direccion direccion) {
        direccionRepository.save(direccion);
        cliente.getDireccionEntrega().add(direccion);
        clienteRepository.save(cliente);
    }

}
