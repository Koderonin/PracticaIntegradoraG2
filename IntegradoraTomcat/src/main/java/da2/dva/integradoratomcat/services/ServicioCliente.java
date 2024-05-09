package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repositories.jpa.ClienteRepository;
import da2.dva.integradoratomcat.repositories.jpa.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCliente {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void insertarNuevoCliente(Cliente cliente){
        direccionRepository.save(cliente.getDireccion());
        clienteRepository.save(cliente);
    }

    public Cliente getClienteByUsuario(UsuarioCliente usuario){
        return clienteRepository.findByUsuarioCliente(usuario);
    }

    public Cliente getClienteByNameAndSurname(String nombre, String apellidos){
        return clienteRepository.findByNombreAndApellidos(nombre, apellidos);
    }

    public void borrarCliente(Cliente cliente){
        clienteRepository.delete(cliente);
    }

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
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

}
