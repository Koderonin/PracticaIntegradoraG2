package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.collections.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, String> {

    Pais findBySiglasPais(String siglasPais);
}
