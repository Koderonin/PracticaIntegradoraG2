package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    @Query("SELECT c FROM Cliente c WHERE c.usuarioCliente = ?1")
    Cliente findByUsuarioCliente(UsuarioCliente usuarioCliente);

    Cliente findByNombreAndApellidos(String nombre, String apellidos);

    @Override
    Optional<Cliente> findById(UUID id);

}
