package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.collections.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoClienteRepository extends JpaRepository<TipoCliente, String> {

}
