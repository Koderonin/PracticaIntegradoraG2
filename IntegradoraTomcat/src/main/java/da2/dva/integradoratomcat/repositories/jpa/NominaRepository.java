package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.composedkeys.NominaKey;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Nomina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NominaRepository extends JpaRepository<Nomina, NominaKey> {
    @Query("SELECT n FROM Nomina n WHERE n.id_nomina.cliente_id = :cliente ORDER BY n.id_nomina.num_nomina DESC LIMIT 1")
    Nomina findTopByCliente(@Param("cliente") Cliente cliente);
}