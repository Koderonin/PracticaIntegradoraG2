package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioClienteRepository extends JpaRepository<UsuarioCliente, UUID> {

    UsuarioCliente findByEmail(String email);
//    UsuarioCliente findByEmail(String email);

}
