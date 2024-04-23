package da2.dva.integradoratomcat.repository;

import da2.dva.integradoratomcat.model.entities.UsuarioAdministrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioAdministradorRepository extends JpaRepository<UsuarioAdministrador, UUID> {
}
