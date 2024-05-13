package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.composedkeys.LineaNominaKey;
import da2.dva.integradoratomcat.model.composedkeys.NominaKey;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.LineaNomina;
import da2.dva.integradoratomcat.model.entities.Nomina;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineaNominaRepository extends JpaRepository<LineaNomina, LineaNominaKey> {

    @Query("SELECT l FROM LineaNomina l WHERE l.linea_id.nomina_id = :nominaKey ORDER BY l.linea_id.nomina_id.num_nomina DESC LIMIT 1")
    LineaNomina findTopByNomina(@Param("nominaKey") NominaKey nominaKey);

    @Query("SELECT l FROM LineaNomina l WHERE l.linea_id.nomina_id = :nominaKey")
    List<LineaNomina> findByNomina(@Param("nominaKey") NominaKey nominaKey);

    // elimina por PK nómina
    @Modifying
    @Transactional
    @Query("DELETE FROM LineaNomina l WHERE l.linea_id.nomina_id = :nominaKey")
    void deleteByNomina(@Param("nominaKey") NominaKey idNominaKey);
}
