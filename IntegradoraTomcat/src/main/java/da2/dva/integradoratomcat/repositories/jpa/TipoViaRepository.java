package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.collections.TipoVia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoViaRepository extends JpaRepository<TipoVia, Long> {

    public TipoVia findByTipoVia(String tipoVia);
}